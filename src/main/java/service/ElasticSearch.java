package service;

import com.google.gson.Gson;
import dao.StoryDao;
import models.tl.Story;
import org.apache.log4j.Logger;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import javax.sql.DataSource;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class ElasticSearch {

    private StoryDao noticiaDao;
    private Logger logger;

    public ElasticSearch(DataSource tnDs, DataSource a3Ds, DataSource ttrssDs) {
        noticiaDao = new StoryDao(tnDs, a3Ds, ttrssDs);
        logger = Logger.getLogger("log");
    }

    public void test () throws UnknownHostException {
        // on startup
        logger.info("iniciando test");
        Settings settings = Settings.builder()
                .put("cluster.name", "elasticsearch").build();
        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(
                        new InetSocketTransportAddress(
                                InetAddress.getByName("localhost"), 9300));
        // on shutdown
        Story story = new Story();
        story.addId(80);
        story.addName("jose");

        IndexRequest indexRequest = new IndexRequest("twitter","tweet", story.getId()+"");
        indexRequest.source(new Gson().toJson(story));
        IndexResponse response = client.index(indexRequest).actionGet();
        logger.info(response);

        client.close();
    }

}
