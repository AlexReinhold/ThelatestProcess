package common;

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
import model.j2.CuratedNew;
import model.tl.Story;
import org.apache.log4j.Logger;
import resource.Process;
import service.*;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
            System.out.println("Stories: "+clusterList.size());

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
            System.out.println("News: "+curatedNews.size());

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

            System.out.println("elasticsearch");

            ElasticSearchService elasticSearchService = new ElasticSearchService();

//            if(!processCluster.getCompleted().isEmpty()){
//                Optional<List<StoryES>> storyES = storyDao.getStoriesForES(processCluster.getCompleted());
//                if(storyES.isPresent()){
//                    System.out.println(storyES.get().size());
//                    elasticSearchService.insertStories(storyES.get());
//                }
//            }

            if(!processCuratedNews.getCompleted().isEmpty()){

                System.out.printf("Sync Stories");
                Optional<List<StoryES>> storyES = storyDao.getStoriesByNewsIdForES(processCuratedNews.getCompleted());
                if(storyES.isPresent()){
                    System.out.println(storyES.get().size());
                    elasticSearchService.insertStories(storyES.get());
                }else{
                    System.out.println("NO stories");
                }
                System.out.println("Finished");
                System.out.println("Sync News");

                Optional<List<NewsES>> newsES = newsDao.getNewsForES(processCuratedNews.getCompleted());
                if(newsES.isPresent()){
                    System.out.println(newsES.get().size());
                    elasticSearchService.insertNews(newsES.get());
                }else{
                    System.out.println("NO news");
                }

                System.out.println("Finished");

            }

            elasticSearchService.closeConnection();

            System.out.println("conclosed");

            processDao.changeState(Process.NEWS.getId(), false);

//            if (storyDao.getClusterListFromJ2().size() > 0) {
//                this.start();
//            }

        }

    }

    private List<Integer> getList(){
        List<Integer> list = new ArrayList<>();
        list.add(4957602);
        list.add(4957601);
        list.add(4957600);
        list.add(4957599);
        list.add(4957598);
        list.add(4957597);
        list.add(4957596);
        list.add(4957595);
        list.add(4957594);
        list.add(4957593);
        list.add(4957592);
        list.add(4957591);
        list.add(4957590);
        list.add(4957589);
        list.add(4957588);
        list.add(4957587);
        list.add(4957586);
        list.add(4957585);
        list.add(4957584);
        list.add(4957583);
        list.add(4957582);
        list.add(4957581);
        list.add(4957580);
        list.add(4957579);
        list.add(4957578);
        list.add(4957577);
        list.add(4957576);
        list.add(4957575);
        list.add(4957574);
        list.add(4957573);
        return list;
    }

}
