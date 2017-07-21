package service;

import common.ProcessCuratedNews;
import dao.SourceDao;
import dao.NewsDao;
import dao.StoryDao;
import model.j2.CuratedNews;
import model.tl.*;
import model.tl.Source;
import org.apache.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import javax.sql.DataSource;
import java.util.Optional;

public class NewsDataManager implements Runnable {

    private ProcessCuratedNews processCuratedNews;
    private NewsDao newsDao;
    private StoryDao storyDao;
    private SourceDao fuenteDao;
    private Logger logger;

    public NewsDataManager(DataSource tnDaTaSource, DataSource a3DataSource, DataSource ttrssDateSource, ProcessCuratedNews pcn) {
        newsDao = new NewsDao(tnDaTaSource, a3DataSource, ttrssDateSource);
        storyDao = new StoryDao(tnDaTaSource, a3DataSource, ttrssDateSource);
        fuenteDao = new SourceDao(tnDaTaSource, a3DataSource, ttrssDateSource);
        this.processCuratedNews = pcn;
    }


    public void run() {
        long start = System.currentTimeMillis();
        logger = Logger.getLogger(Thread.currentThread().getName());
        int j = 0;
        while (processCuratedNews.isFinish()) {
            Optional<CuratedNews> curatedNew = processCuratedNews.getCuratedNews();
            if(!curatedNew.isPresent())
                continue;

            Optional<News> news = syncNews(curatedNew.get());

            if (news.isPresent())
            {
                processCuratedNews.addCompleted(news.get().getId());
                j++;
            }
        }
        long end = System.currentTimeMillis();
        logger.info(j + " news sync by the Thread " + Thread.currentThread().getName());
        logger.info("News sync in " + (end - start) + " ms");
        logger.info("-----------------------------------------------------");
    }

    private Optional<News> syncNews(CuratedNews curatedNew) {

        Optional<Source> source = fuenteDao.sourceByExternalId(curatedNew.getSource().getId()+"");

        if(!source.isPresent())
            return Optional.empty();

        Story story;
        try {
            story = storyDao.storyBySlug(curatedNew.getCluster().getSlug());
        } catch (EmptyResultDataAccessException e) {
            logger.error("story not found by slug -> " + curatedNew.getCluster().getSlug());
            return Optional.empty();
        }

        News news = new News();
        news.addStory(story)
                .addSource(source.get())
                .addUrl(curatedNew.getLink())
                .addExternalId(curatedNew.getId()+"")
                .addTitle(curatedNew.getTitle())
                .addSnippet(curatedNew.getSnippet())
                .addImgUrl(curatedNew.getImage())
                .addAuthor(curatedNew.getAuthor())
                .addScore(0)
                .addPubDate(curatedNew.getPubDate())
                .addAddedDate(curatedNew.getDateEntered())
                .addStaffPicks(false)
                .addContent(curatedNew.getNewsContent().getRawText())
                .addTags("");

        try {
            newsDao.insertNews(news);
            if (news.getId() > 0) {
                newsDao.updateNewsStateJ2(curatedNew.getId(), true);
                return Optional.of(news);
            } else {
                logger.error("articulo no registrado en TN " + news.getTitle());
            }
        } catch (DataIntegrityViolationException e) {
            logger.error("Slug de noticia : " + story.getSlug() + " id " + story.getId());
            System.err.println(e.getMessage());
            logger.error(e.getMessage());
            logger.error(e.getCause());
        }

        return Optional.empty();
    }

}




