package util;

import org.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;

public class HttpHelper {

    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<JSONObject> httpEntity;

    public int notificacionATN(JSONObject json) throws JSONException {

        String noticiasNuevasUrl = "http://localhost:9000/api/notify-news";
        httpEntity = restTemplate.postForEntity(noticiasNuevasUrl,
                json,
                JSONObject.class);
        return httpEntity.getStatusCodeValue();

    }

}
