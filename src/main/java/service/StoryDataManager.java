package service;

import common.ProcessCluster;
import dao.*;
import model.j2.Cluster;
import model.tl.*;
import org.apache.log4j.Logger;
import org.springframework.dao.DuplicateKeyException;
import javax.sql.DataSource;
import java.util.Optional;

public class StoryDataManager implements Runnable {

    private ProcessCluster processCluster;
    private StoryDao storyDao;
    private CategoryDao categoryDao;
    private Logger logger;

    public StoryDataManager(DataSource tnDaTaSource, DataSource a3DataSource, DataSource ttrssDateSource, ProcessCluster pc) {
        storyDao = new StoryDao(tnDaTaSource, a3DataSource, ttrssDateSource);
        categoryDao = new CategoryDao(tnDaTaSource, a3DataSource, ttrssDateSource);
        this.processCluster = pc;
    }

    public void run() {
        long start = System.currentTimeMillis();
        logger = Logger.getLogger(Thread.currentThread().getName());
        int i = 0;
        while (processCluster.isFinish()) {
            Optional<Cluster> cluster = processCluster.getCluster();
            if(!cluster.isPresent())
                continue;

            Optional<Story> story = syncStories(cluster.get());

            if(story.isPresent()){
                processCluster.addCompleted(story.get().getId());
                i++;
            }
        }

        logger.info(i + " stories sync by the Thread " + Thread.currentThread().getName());
        logger.info("--------------------------------------------------");
        long end = System.currentTimeMillis();
        logger.info("Stories sync in " + (end - start) + " ms");
        logger.info("-----------------------------------------------------");
    }

    private Optional<Story> syncStories(Cluster cluster) {

        Story story = new Story();

        Optional<Category> category = categoryDao.getByExternalId(cluster.getSubCatId()+"");
        if(!category.isPresent())
            return Optional.empty();

        story.addCategory(category.get());

        story.addPosition(0)
            .addName(cluster.getTittle())
            .addExternalId(cluster.getId())
            .addSlug(cluster.getSlug())
            .addDeadLine(null)
            .addTags("")
            .addViews(0)
            .addShares(0);

        try {
            storyDao.insertStory(story);
            if (story.getId() > 0) {
                storyDao.updateStoryStateJ2(cluster.getId());
                return Optional.of(story);
            } else {
                logger.error("Story not registered" + story.getName());
            }
        } catch (DuplicateKeyException e) {
            logger.error("slug or externalId duplicated: " + story.getSlug() + " No fue sincronizada");
            logger.info("-----------------------------------------------------");
            storyDao.updateStoryStateJ2(cluster.getId());
        }
        return Optional.empty();
    }

}




