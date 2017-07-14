package common;

import dao.NewsDao;
import dao.StoryDao;
import dao.ProcessDao;
import datasource.J2DataSource;
import datasource.TlDataSource;
import datasource.ttrssDataSource;
import elasticsearch.ElasticSearchService;
import model.elasticsearch.StoryES;
import model.j2.Cluster;
import model.j2.CuratedNew;
import model.tl.Story;
import org.apache.log4j.Logger;
import resource.Process;
import service.*;
import javax.sql.DataSource;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class BeginProcess {

    private DataSource j2Ds, tlDs, ttrssDs;
    private StoryDao storyDao;
    private NewsDao newsDao;
    private ProcessDao processDao;

    BeginProcess() throws Exception {
        j2Ds = new J2DataSource().getDatasource();
        tlDs = new TlDataSource().getDatasource();
        ttrssDs = new ttrssDataSource().getDatasource();
        storyDao = new StoryDao(tlDs, j2Ds, ttrssDs);
        newsDao = new NewsDao(tlDs, j2Ds, ttrssDs);
        processDao = new ProcessDao(tlDs);
    }

    void start() throws Exception {

        boolean processingNews;
        int threads = Runtime.getRuntime().availableProcessors();

        processingNews = processDao.state(Process.NEWS.getId());
        if (!processingNews) {

//            processDao.changeState(Process.NEWS.getId(), true);

            List<Cluster> clusterList = storyDao.getClusterListFromJ2();
            System.out.println(clusterList.size());

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

            List<CuratedNew> curatedNews = newsDao.getCuratedNewsListFromJ2();
            System.out.println(curatedNews.size());

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

            for ( int id : processCluster.getCompleted()){
                System.out.println("storyId : "+id);
            }

            for ( int id : processCuratedNews.getCompleted()){
                System.out.println("newsId : "+id);
            }

            processDao.changeState(Process.NEWS.getId(), false);

            if (storyDao.getClusterListFromJ2().size() > 0) {
                this.start();
            }

        }

    }

}
