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

import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

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
        Settings settings = ImmutableSettings.settingsBuilder().put("cluster.name", "elasticsearch").build();
        client = new TransportClient(settings).addTransportAddress(new InetSocketTransportAddress("localhost", 9300));
//        client = new TransportClient(settings).addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
//        client = new TransportClient().addTransportAddress(new InetSocketTransportAddress("localhost", 9300));
    }

    public void closeConnection() {
        client.close();
    }

    public void insertStories(List<StoryES> stories){

        List<StoryES> unique = stories.stream()
                .collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparingInt(StoryES::getId))),
                        ArrayList::new));

        int created = 0;
        for (StoryES s : unique) {
            String json = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ssX").serializeNulls().create().toJson(s);
            IndexResponse response = client.prepareIndex(INDEX, STORY_TYPE, s.getId() + "")
                    .setSource(json)
                    .execute()
                    .actionGet();
            if(response.getId() != null)
                created++;

        }
        logger.info("Stories Indexed: "+created);
    }

    public void insertNews(List<NewsES> news){

        List<NewsES> unique = news.stream()
                .collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparingInt(NewsES::getId))),
                        ArrayList::new));

        int created = 0;
        for (NewsES n : unique) {
            String json = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ssX").serializeNulls().create().toJson(n);
            IndexResponse response = client.prepareIndex(INDEX, NEWS_TYPE, n.getId()+"")
                    .setSource(json)
                    .execute()
                    .actionGet();
            if(response.getId() != null)
                created++;

        }
        logger.info("News Indexed: "+created);
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
