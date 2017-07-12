package elasticsearch;

import com.google.gson.Gson;
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
            IndexResponse response = client.prepareIndex(INDEX, STORY_TYPE, s.getId() + "")
                    .setSource(new Gson().toJson(s))
                    .execute()
                    .actionGet();
            System.out.println(response);

        }

    }

    public void insertNews(List<NewsES> news){

        for (NewsES s : news) {
            IndexResponse response = client.prepareIndex(INDEX, NEWS_TYPE, s.getId() + "")
                    .setSource(new Gson().toJson(s))
                    .execute()
                    .actionGet();
            System.out.println(response);

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
