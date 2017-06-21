package service;

import dao.StoryDao;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import util.HttpHelper;
import javax.sql.DataSource;
import java.util.List;

public class Notifications {

    private Logger logger;
    private StoryDao noticiaDao;
    private HttpHelper httpHelper;

    public Notifications(DataSource tnDataSource, DataSource a3DataSource, DataSource ttrssDataSource) {
        noticiaDao = new StoryDao(tnDataSource, a3DataSource, ttrssDataSource);
        httpHelper = new HttpHelper();
    }

    public void notificarATN() throws JSONException {
        logger = Logger.getLogger(Notifications.class);
        List<Integer> noticiasNuevas = noticiaDao.noticiasParaNotificacion();
        if (!noticiasNuevas.isEmpty()) {
            int[] ids = noticiasNuevas.stream().mapToInt(i -> i).toArray();
            JSONObject json = new JSONObject().put("noticias", ids);
            if (httpHelper.notificacionATN(json) == 200) {
                logger.info("Datos de noticias enviados Correctamente");
                noticiaDao.eliminarNoticiasDeNotificacion();
            } else {
                logger.error("Error enviando notificacion");
            }
        } else {
            logger.info("No hay noticias nuevas, no se enviara notificacion");
        }
    }

}
