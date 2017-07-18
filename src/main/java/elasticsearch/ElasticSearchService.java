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

import java.net.InetAddress;
import java.util.List;

public class ElasticSearchService {

    private StoryDao storyDao;
    private Client client;
    private static final String INDEX = "global" ,
                                NEWS_TYPE = "news", WTM_TYPE = "wtm", STORY_TYPE = "story", EXAMPLE_TYPE = "example";
    private Logger logger;

    public ElasticSearchService() {
        this.iniciarConexion();
        logger = Logger.getLogger(this.getClass().getName());
    }

    private void iniciarConexion() {
        Settings settings = ImmutableSettings.settingsBuilder()
                .put("cluster.name", "elasticsearch").build();
        client = new TransportClient(settings).addTransportAddress(new InetSocketTransportAddress("localhost", 9300));
//        client = new TransportClient(settings).addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
//        client = new TransportClient(settings).addTransportAddress(new InetSocketTransportAddress("localhost", 9300));
    }

    public void closeConnection() {
        client.close();
    }

    public void insertStories(List<StoryES> stories){

        int created = 0;
        for (StoryES s : stories) {
            String json = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ssX").serializeNulls().create().toJson(s);
            IndexResponse response = client.prepareIndex(INDEX, STORY_TYPE, s.getId() + "")
                    .setSource(json)
                    .execute()
                    .actionGet();
            if(response.isCreated())
                created++;

        }
        logger.info("Total indexed stories: "+ created);
    }

    public void insertNews(List<NewsES> news){

        int created = 0;
        for (NewsES n : news) {
            String json = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ssX").serializeNulls().create().toJson(n);
            IndexResponse response = client.prepareIndex(INDEX, NEWS_TYPE, n.getId() + "")
                    .setSource(json)
                    .execute()
                    .actionGet();
            if(response.isCreated())
                created++;

        }
        logger.info("Total indexed news: "+ created);
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

}
