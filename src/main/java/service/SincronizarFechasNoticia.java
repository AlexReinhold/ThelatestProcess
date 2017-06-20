package service;

import common.ProcessNews;
import dao.NewsDao;
import dao.FuenteDao;
import dao.StoryDao;
import models.tl.FuentePorArticulo;
import models.tl.ArticuloNoProcesado;
import models.tl.noticia;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public class SincronizarFechasNoticia implements Runnable {

    private StoryDao noticiaDao;
    private NewsDao articuloDao;
    private FuenteDao fuenteDao;
    private Logger logger;
    private ProcessNews procesarArticulo;

    public SincronizarFechasNoticia(DataSource tnDs, DataSource a3Ds, DataSource ttrssDs, ProcessNews procesarArticulo) {
        this.noticiaDao = new StoryDao(tnDs, a3Ds, ttrssDs);
        this.articuloDao = new NewsDao(tnDs, a3Ds, ttrssDs);
        this.fuenteDao = new FuenteDao(tnDs, a3Ds, ttrssDs);
        this.procesarArticulo = procesarArticulo;
    }

    @Override
    public void run() {
        long inicio = System.currentTimeMillis();
        logger = Logger.getLogger(Thread.currentThread().getName());
        logger.info("Sincronizando Fechas y numero de fuentes para noticias");
        int success = 0, failed = 0;
        while (procesarArticulo.isFinish()) {
            ArticuloNoProcesado articuloNoProcesado = procesarArticulo.obtenerArticulo();
            if (SincronizarFechasNoticiaTN(articuloNoProcesado) > 0) {
                success++;
            } else {
                failed++;
                logger.warn("No se actualizaron fechas de la noticia ID -> " + articuloNoProcesado.getArticulo().getNoticia().getId());
                logger.warn("Posiblemente no tenga ningun articulo registrado");
            }
        }
        logger.info(success + " fechas de noticias actualizadas por el thread " + Thread.currentThread().getName());
        logger.info(failed + " fechas de noticias que no pudieron ser actualizadas por el thread " + Thread.currentThread().getName());
        logger.info("-----------------------------------------------------------------");
        long fin = System.currentTimeMillis();
        logger.info("Proceso finalizado en " + (fin - inicio) + " ms");
    }

    private int SincronizarFechasNoticiaTN(ArticuloNoProcesado articuloNoProcesado) {

        noticia noticia = articuloNoProcesado.getArticulo().getNoticia();
        Map<String, Object> fechas = noticiaDao.fechasNoticia(noticia.getId());
        noticia.addFechaPublicacion((Timestamp) fechas.get("publicacion"));
        noticia.addUltimaActualizacion((Timestamp) fechas.get("ultima_fecha"));
        this.SincronizarNumeroDeFuentesNoticiaTN(articuloNoProcesado);
        if (fechas.get("publicacion") == null || fechas.get("ultima_fecha") == null) {
            logger.error("Noticia sin articulos -> " + noticia.getId());
            return 0;
        } else {
            if (noticiaDao.actualizarFechasDeNoticia(noticia) > 0) {
                articuloNoProcesado.addFechaSync(true);
                articuloNoProcesado.getArticulo().addNoticia(noticia);
                articuloDao.actualizarEstadoArticuloNoProcesado(articuloNoProcesado,
                        "fecha_sync",
                        articuloNoProcesado.isFechaSync());
                return 1;
            }
        }
        return 0;
    }

    private int SincronizarNumeroDeFuentesNoticiaTN(ArticuloNoProcesado articuloNoProcesado) {
        noticia noticia = articuloNoProcesado.getArticulo().getNoticia();
        List<FuentePorArticulo> fuentesPorNoticia = fuenteDao.fuentesPorNoticia(noticia.getId());
        if (!fuentesPorNoticia.isEmpty()) {
            noticia.addNumeroFuentes(fuentesPorNoticia.size());
            if (noticiaDao.actualizarFuentesDeNoticia(noticia) > 0) {
                articuloNoProcesado.addNumeroFuentesSync(true);
                articuloDao.actualizarEstadoArticuloNoProcesado(articuloNoProcesado, "numero_fuentes_sync", articuloNoProcesado.isNumeroFuentesSync());
                return 1;
            } else {
                return 0;
            }
        }
        return 1;
    }
}
