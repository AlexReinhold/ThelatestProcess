package elasticsearch;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.StoryDao;
import model.elasticsearch.NewsES;
import model.elasticsearch.StoryES;
import model.elasticsearch.WTM;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import java.util.List;

public class ElasticSearchService {

    private StoryDao storyDao;
    private Client client;
    private static final String INDEX = "global" ,
                                NEWS_TYPE = "news", WTM_TYPE = "wtm", STORY_TYPE = "story", EXAMPLE_TYPE = "example";

    public ElasticSearchService() {
        this.iniciarConexion();
    }

    private void iniciarConexion() {

        client = new TransportClient()
                .addTransportAddress(new InetSocketTransportAddress("localhost", 9300));
    }

    public void closeConnection() {
        client.close();
    }

    public void insertStories(List<StoryES> stories){

        for (StoryES s : stories) {
            String json = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ssX")
                    .serializeNulls()
                    .create()
                    .toJson(s);
            System.out.println("Story -> "+json);
            IndexResponse response = client.prepareIndex(INDEX, STORY_TYPE, s.getId() + "")
                    .setSource(json)
                    .execute()
                    .actionGet();
            System.out.println("storyId: "+s.getId()+" / -> "+response.isCreated());
        }

    }

    public void insertNews(List<NewsES> news){

        for (NewsES n : news) {
            String json = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ssX")
                    .serializeNulls()
                    .create()
                    .toJson(n);
            System.out.println("News -> "+json);
            IndexResponse response = client.prepareIndex(INDEX, NEWS_TYPE, n.getId() + "")
                    .setSource(json)
                    .execute()
                    .actionGet();
            System.out.println("newsId: "+n.getId()+" / -> "+response.isCreated());

        }

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
