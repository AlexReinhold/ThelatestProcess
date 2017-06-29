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
        long inicio = System.currentTimeMillis();
        logger = Logger.getLogger(Thread.currentThread().getName());
        int i = 0;
        while (processCluster.isFinish()) {
            Cluster cluster = processCluster.obtenerCluster();
            Optional<Story> story = syncStories(cluster);
            if (story.isPresent()) {
                processCluster.addCompleted(story.get().getId());
                i++;
            }
        }
        logger.info(i + " Noticias Sincronizadas por el Thread " + Thread.currentThread().getName());
        logger.info("--------------------------------------------------");
        long fin = System.currentTimeMillis();
        logger.info("Articulos y noticias Sincronizadas En " + (fin - inicio) + " ms");
        logger.info("-----------------------------------------------------");
    }

    private Optional<Story> syncStories(Cluster cluster) {
        Story story = new Story();
        story.addViews(0)
                .addShares(0);

        Category categoria = categoryDao.getByExternalId(cluster.getSubCatId()+"");
        story.addCategory(categoria);

        story.addPosition(0)
            .addName(cluster.getTittle())
            .addExternalId(cluster.getId())
            .addSlug(cluster.getSlug())
            .addDeadLine(null)
            .addTags("");

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



