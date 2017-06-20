package service;

import dao.NewsDao;
import dao.StoryDao;
import models.tl.Articulo;
import models.tl.ArticuloNoProcesado;
import models.tl.noticia;
import org.apache.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import util.*;

import javax.sql.DataSource;
import java.util.*;

public class SincronizarTagsNoticia {

    private StoryDao noticiaDao;
    private NewsDao articuloDao;
    private Logger logger;
    private Utils utils;

    public SincronizarTagsNoticia(DataSource tnDs, DataSource a3Ds, DataSource ttrssDs) {
        this.noticiaDao = new StoryDao(tnDs, a3Ds, ttrssDs);
        this.articuloDao = new NewsDao(tnDs, a3Ds, ttrssDs);
        utils = new Utils();
    }

    public void actualizarTagsDeNoticia(List<ArticuloNoProcesado> articulos) {

        long inicio = System.currentTimeMillis();
        logger = Logger.getLogger(Thread.currentThread().getName());
        logger.info("Generando Tags para nuevas noticias y actualizando los de las Actuales");
        int cantidad = 0;

        for (ArticuloNoProcesado anp : articulos) {
            try {
                noticia noticia = noticiaDao.noticiaPorId(anp.getArticulo().getNoticia().getId());
                List<Articulo> articulosNoticia = articuloDao.articulosPorIdNoticia(noticia.getId());
                if (!articulosNoticia.isEmpty()) {
                    StringBuilder sb = new StringBuilder();
                    if (!noticia.getTags().equals("")) {
                        sb.append(noticia.getTags()).append(",");
                    }
                    for (Articulo articulo : articulosNoticia) {
                        if (!articulo.getTags().equals("")) {
                            sb.append(articulo.getTags()).append(",");
                        }
                    }
                    String tags = sb.toString();
                    if (!tags.equals("")) {
                        tags = utils.eliminarTagsDuplicados(tags.split(","));
                        if (noticiaDao.actualizarTagsDeNoticia(tags, noticia.getId()) > 0) {
                            cantidad++;
                        } else {
                            logger.error("Error actualizando tags de la noticia " + noticia.getId());
                        }
                    }
                }
            } catch (EmptyResultDataAccessException e) {
                logger.error("noticia no encontrada de acuerdo al id -> " + anp.getArticulo().getNoticia().getId());
                logger.error(e.getMessage());
            } finally {
                anp.addTagsSync(true);
                articuloDao.actualizarEstadoArticuloNoProcesado(anp, "tags_sync", anp.isTagsSync());
            }
        }
        List<ArticuloNoProcesado> articuloNoProcesadosAll = articuloDao.articulosNoProcesadosAll();
        if (!articuloNoProcesadosAll.isEmpty()) {
            for (ArticuloNoProcesado anp : articuloNoProcesadosAll) {
                if (anp.isFechaSync() && anp.isTagsSync() && anp.isNumeroFuentesSync()) {
                    articuloDao.eliminarArticuloNoProcesado(anp);
                }
            }
        }
        long fin = System.currentTimeMillis();
        logger.info("Tags generados para " + cantidad + " nuevos articulos");
        logger.info("Proceso finalizado en " + (fin - inicio) + " ms");
        logger.info("------------------------------------------------------------------------");

    }
}
