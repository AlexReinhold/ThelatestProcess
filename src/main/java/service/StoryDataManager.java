package service;

import common.ProcessCluster;
import dao.*;
import models.j2.Cluster;
import models.tl.*;
import org.apache.log4j.Logger;
import org.springframework.dao.DuplicateKeyException;

import javax.sql.DataSource;
import java.sql.Timestamp;

public class StoryDataManager implements Runnable {

    private ProcessCluster processCluster;
    private StoryDao storyDao;
    private CategoryDao categoryDao;
    private Logger logger;

    public StoryDataManager(DataSource tnDaTaSource, DataSource a3DataSource, DataSource ttrssDateSource, ProcessCluster pc) {
        storyDao = new StoryDao(tnDaTaSource, a3DataSource, ttrssDateSource);
        categoryDao = new CategoryDao(tnDaTaSource, a3DataSource, ttrssDateSource);
        this.processCluster = pc;
    }


    public void run() {
        long inicio = System.currentTimeMillis();
        logger = Logger.getLogger(Thread.currentThread().getName());
        int i = 0;
        while (processCluster.isFinish()) {
            Cluster cluster = processCluster.obtenerCluster();
            if (syncStories(cluster) == 1) {
                i++;
            }
        }
        logger.info(i + " Noticias Sincronizadas por el Thread " + Thread.currentThread().getName());
        logger.info("--------------------------------------------------");
        long fin = System.currentTimeMillis();
        logger.info("Articulos y noticias Sincronizadas En " + (fin - inicio) + " ms");
        logger.info("-----------------------------------------------------");
    }

    private int syncStories(Cluster cluster) {
        Story noticia = new Story();
        noticia.addVistas(0)
                .addCompartidos(0);

        Category categoria = categoryDao.getByExternalId(cluster.getMainCatId()+"");
        noticia.addCategoria(categoria);

        noticia.addPosicion(0) // 0 mientras averiguamos para q coño es esto
                .addTitulo(cluster.getTittle())
                .addIdExterno(cluster.getId())
                .addSlug(cluster.getSlug())
                .addDeadLine(new Timestamp(System.currentTimeMillis())) // por ahora currentdate
                .addTags("");

        try {
            noticia = storyDao.nuevaNoticiaTN(noticia);
            if (noticia.getId() > 0) {
                storyDao.actualizarEstadoDeNoticiaA3(cluster.getId());
                storyDao.nuevaNoticiaParaNotificacion(noticia.getId());
                return 1;
            } else {
                logger.error("Noticia no registrada tn TN " + noticia.getTitulo());
            }
        } catch (DuplicateKeyException e) {
            logger.error("Slug de noticia duplicado: " + noticia.getSlug() + " No fue sincronizada");
            logger.info("-----------------------------------------------------");
            storyDao.actualizarEstadoDeNoticiaA3(cluster.getId());
        }
        return 0;
    }

}




