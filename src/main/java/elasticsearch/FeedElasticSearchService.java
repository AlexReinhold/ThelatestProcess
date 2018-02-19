package elasticsearch;

//import com.google.gson.GsonBuilder;
//import model.elasticsearch.FeedES;
////import org.apache.log4j.Logger;
//import org.apache.logging.log4j.Logger;
//import org.elasticsearch.action.bulk.BulkRequestBuilder;
//import org.elasticsearch.action.bulk.BulkResponse;
//import org.elasticsearch.client.Client;
////import org.elasticsearch.common.settings.ImmutableSettings;
//import org.elasticsearch.common.settings.Settings;
//import org.elasticsearch.common.transport.InetSocketTransportAddress;
//import org.elasticsearch.transport.ReceiveTimeoutTransportException;
//import org.elasticsearch.transport.client.PreBuiltTransportClient;
//
//import java.net.UnknownHostException;
//import java.util.List;
//
//import static java.net.InetAddress.getByName;

public class FeedElasticSearchService {

//    private Client client;
//    public static final String INDEX = "feed" ,
//                                NEWS_TYPE = "news", WTM_TYPE = "wtm", STORY_TYPE = "story";
//    private Logger logger;
//
//    public FeedElasticSearchService() throws UnknownHostException{
////        logger = Logger.getLogger(this.getClass().getName());
//        Settings settings = Settings.builder()
//                .put("cluster.name", "thelatest-elastic")
//                .put("client.transport.sniff", true)
////                .put("client.transport.ping_timeout", 10000)
//                .build();
//        client = new PreBuiltTransportClient(settings)
//                .addTransportAddress(new InetSocketTransportAddress(getByName("192.168.1.129"), 9300));
//    }
//
//    public void closeConnection() {
//        client.close();
//    }
//
//    public boolean insert(String type, List<FeedES> feedES){
//
//        try{
//
//            BulkRequestBuilder bulk = client.prepareBulk();
//
//            for (FeedES es : feedES) {
//                bulk.add(client.prepareIndex(INDEX, type, es.getId()+"")
//                        .setSource(new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssX").serializeNulls().create().toJson(es)));
//            }
//
//            BulkResponse response = bulk.get();
//
//            if(!response.hasFailures())
//                return true;
//
//        }catch (ReceiveTimeoutTransportException ex){
//            System.err.println(ex.getMessage());
////            System.out.println(ex.getMessage());
//        }
//
//        return false;
//
//    }


}
