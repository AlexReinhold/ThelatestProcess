package service;

import common.ProcessCuratedNews;
import dao.SourceDao;
import dao.NewsDao;
import dao.StoryDao;
import model.j2.CuratedNew;
import model.tl.*;
import model.tl.Source;
import org.apache.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import javax.sql.DataSource;

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
        long inicio = System.currentTimeMillis();
        logger = Logger.getLogger(Thread.currentThread().getName());
        int j = 0;
        while (processCuratedNews.isFinish()) {
            CuratedNew curatedNew = processCuratedNews.obtenerCuratedNew();
            if (syncNews(curatedNew) == 1) {
                j++;
            }
        }
        long fin = System.currentTimeMillis();
        logger.info(j + " Articulos Sincronizados por el Thread " + Thread.currentThread().getName());
        logger.info("Articulos y noticias Sincronizadas En " + (fin - inicio) + " ms");
        logger.info("-----------------------------------------------------");
    }

    private int syncNews(CuratedNew curatedNew) {

        Source source = fuenteDao.sourceByExternalId(curatedNew.getSource().getId()+"");

        Story story;
        try {
            story = storyDao.storyBySlug(curatedNew.getCluster().getSlug());
        } catch (EmptyResultDataAccessException e) {
            logger.error("story not found by slug -> " + curatedNew.getCluster().getSlug());
            return 0;
        }

        News news = new News();
        news.addStory(story)
                .addSource(source)
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
                .addTags("")
        ;
        try {
            news = newsDao.insertNews(news);
            if (news.getId() > 0) {
                newsDao.updateNewsStateJ2(curatedNew.getId(), true);
                return 1;
            } else {
                logger.error("articulo no registrado en TN " + news.getTitle());
            }
        } catch (DataIntegrityViolationException e) {
            logger.error("Slug de noticia : " + story.getSlug() + " id " + story.getId());
            System.err.println(e.getMessage());
            logger.error(e.getMessage());
            logger.error(e.getCause());
        }

        return 0;
    }

}




