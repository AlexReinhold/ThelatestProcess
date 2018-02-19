package elasticsearch;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.StoryDao;
import model.elasticsearch.NewsES;
import model.elasticsearch.StoryES;
import model.elasticsearch.WTM;
import org.apache.log4j.Logger;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.ReceiveTimeoutTransportException;

import java.util.*;
import java.util.stream.Collectors;

public class ElasticSearchService {

    private StoryDao storyDao;
    private Client client;
    private static final String INDEX = "global" ,
                                NEWS_TYPE = "news", WTM_TYPE = "wtm", STORY_TYPE = "story", EXAMPLE_TYPE = "example";
    private Logger logger;

    public ElasticSearchService(StoryDao storyDao) {
        this.iniciarConexion();
        this.storyDao = storyDao;
        logger = Logger.getLogger(this.getClass().getName());
    }

    private void iniciarConexion() {
        Settings settings = ImmutableSettings.settingsBuilder()
//                .put("cluster.name", "elasticsearch")
                .put("client.transport.sniff", true)
                .put("client.transport.ping_timeout", 10000).build();
        client = new TransportClient(settings).addTransportAddress(new InetSocketTransportAddress("localhost", 9300));
    }

    public void closeConnection() {
        client.close();
    }

    public void insertStories(List<StoryES> stories){

        int created = 0, error = 0;
        for (StoryES s : stories) {
            String json = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ssX").serializeNulls().create().toJson(s);
            if(insert(STORY_TYPE, s.getId()+"", json))
                created++;
            else {
                error++;
                storyDao.insertUnprocessedStory(s.getId());
            }
        }
        logger.info("Stories Indexed: "+created);
        logger.info("Stories Error: "+error);
    }

    public void insertNews(List<NewsES> news){

        List<NewsES> unique = news.stream()
                .collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparingInt(NewsES::getId))),
                        ArrayList::new));

        int created = 0, error = 0;
        for (NewsES n : unique) {
            String json = new GsonBuilder()
                        .setDateFormat("yyyy-MM-dd'T'HH:mm:ssX").serializeNulls().create().toJson(n);
            if(insert(NEWS_TYPE, n.getId()+"", json))
                created++;
            else
                error++;
        }
        logger.info("News Indexed: "+created);
        logger.info("News Error: "+error);
    }

    public void insertWTM(List<WTM> wtm){

        for (WTM w : wtm) {
            IndexResponse response = client.prepareIndex(INDEX, WTM_TYPE, w.getId() + "")
                    .setSource(new Gson().toJson(w))
                    .execute()
                    .actionGet();
            System.out.println(response);

        }

    }

    private boolean insert(String type, String id, String json){

        try{
            IndexResponse response = client.prepareIndex(INDEX, type, id)
                    .setSource(json)
                    .execute()
                    .actionGet();
            if(response.getId() != null)
                return true;

        }catch (Exception ex){
            logger.error(ex.getMessage());
        }

        return false;

    }

}
