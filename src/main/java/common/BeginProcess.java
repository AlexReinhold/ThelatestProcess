package common;

import dao.CategoryDao;
import dao.NewsDao;
import dao.StoryDao;
import dao.ProcessDao;
import datasource.J2DataSource;
import datasource.TlDataSource;
import datasource.ttrssDataSource;
import elasticsearch.ElasticSearchService;
import model.elasticsearch.NewsES;
import model.elasticsearch.StoryES;
import model.j2.Cluster;
import model.j2.CuratedNews;
import model.tl.Category;
import org.apache.log4j.Logger;
import resource.Process;
import service.*;
import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class BeginProcess {

    private DataSource j2Ds, tlDs, ttrssDs;
    private StoryDao storyDao;
    private NewsDao newsDao;
    private ProcessDao processDao;
    private CategoryDao categoryDao;
    private Logger logger;

    BeginProcess() throws Exception {
        j2Ds = new J2DataSource().getDatasource();
        tlDs = new TlDataSource().getDatasource();
        ttrssDs = new ttrssDataSource().getDatasource();
        storyDao = new StoryDao(tlDs, j2Ds, ttrssDs);
        newsDao = new NewsDao(tlDs, j2Ds, ttrssDs);
        processDao = new ProcessDao(tlDs);
        categoryDao = new CategoryDao(tlDs,j2Ds,ttrssDs);
        logger = Logger.getLogger(this.getClass().getName());
    }

    void start() throws Exception {

        boolean processingNews;
//        int threads = Runtime.getRuntime().availableProcessors();
        int threads = 10;
        processingNews = processDao.state(Process.NEWS.getId());

        if (!processingNews) {
            processDao.changeState(Process.NEWS.getId(), true);

            ElasticSearchService elasticSearchService = new ElasticSearchService();
            for (Category parentCat : categoryDao.getParentCategories()) {

                List<Cluster> clusterList = storyDao.getClusterListFromJ2(parentCat.getExternalId());
                logger.info(parentCat.getName()+" Stories: " + clusterList.size());

                ProcessCluster processCluster = new ProcessCluster(clusterList);
                ExecutorService threadPool1 = Executors.newFixedThreadPool(threads);
                Runnable storyDataManager = new StoryDataManager(tlDs, j2Ds, ttrssDs, processCluster);

                for (int i = 0; i < threads; i++) {
                    Thread t = new Thread(storyDataManager);
                    threadPool1.execute(t);
                }

                threadPool1.shutdown();
                while (!threadPool1.isTerminated()) {
                }

                List<CuratedNews> curatedNews = newsDao.getCuratedNewsListFromJ2(parentCat.getExternalId());
                logger.info(parentCat.getName()+" News: " + curatedNews.size());

                ProcessCuratedNews processCuratedNews = new ProcessCuratedNews(curatedNews);
                ExecutorService threadPool2 = Executors.newFixedThreadPool(threads);
                Runnable newsDataManager = new NewsDataManager(tlDs, j2Ds, ttrssDs, processCuratedNews);

                for (int i = 0; i < threads; i++) {
                    Thread t = new Thread(newsDataManager);
                    threadPool2.execute(t);
                }

                threadPool2.shutdown();
                while (!threadPool2.isTerminated()) {
                }

//                if(!processCluster.getCompleted().isEmpty()){
//                    Optional<List<StoryES>> storyES = storyDao.getStoriesForES(processCluster.getCompleted());
//                    if(storyES.isPresent()){
//                        System.out.println(storyES.get().size());
//                        elasticSearchService.insertStories(storyES.get());
//                    }
//                }

                if (!processCuratedNews.getCompleted().isEmpty()) {
                    logger.info("Sync stories in ES");
                    Optional<List<StoryES>> storyES = storyDao.getStoriesByNewsIdForES(processCuratedNews.getCompleted());
                    if (storyES.isPresent()) {
                        elasticSearchService.insertStories(storyES.get());
                    } else {
                        logger.info("NO stories to Sync");
                    }

                    logger.info("Sync news in ES");
                    Optional<List<NewsES>> newsES = newsDao.getNewsForES(processCuratedNews.getCompleted());
                    if (newsES.isPresent()) {
                        elasticSearchService.insertNews(newsES.get());
                    } else {
                        logger.info("NO news to Sync");
                    }

                }

                logger.info("Finisihed "+parentCat.getName());
                logger.info("------------------------------");

            }
            elasticSearchService.closeConnection();
            processDao.changeState(Process.NEWS.getId(), false);
            logger.info("Finisihed Process");
            logger.info("------------------------------");
        }else{
            logger.info("There is already a running process");
            logger.info("------------------------------");
        }

    }

}
