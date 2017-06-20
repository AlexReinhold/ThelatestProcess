package common;

import dao.NewsDao;
import dao.CategoriaDao;
import dao.StoryDao;
import dao.ProcessDao;
import datasource.J2DataSource;
import datasource.TlDataSource;
import datasource.ttrssDataSource;
import models.tl.ArticuloNoProcesado;
import resource.Process;
import service.*;

import javax.sql.DataSource;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class IniciarProceso {

    private DataSource j2Ds, tlDs, ttrssDs;
    private StoryDao noticiaDao;
    private NewsDao articuloDao;
    private ProcessDao procesosDao;
    private CategoriaDao categoriaDao;

    IniciarProceso() throws Exception {
        j2Ds = new J2DataSource().getDatasource();
        tlDs = new TlDataSource().getDatasource();
        ttrssDs = new ttrssDataSource().getDatasource();
        noticiaDao = new StoryDao(tlDs, j2Ds, ttrssDs);
        this.categoriaDao = new CategoriaDao(tlDs, j2Ds, ttrssDs);
        articuloDao = new NewsDao(tlDs, j2Ds, ttrssDs);
        procesosDao = new ProcessDao(tlDs);
    }

    void iniciar() throws Exception {

        String pais = "";
        boolean procensadoFuentes, procesandoCategorias, procesandoNoticias;

        procesandoNoticias = procesosDao.estadoDeProceso(Process.NOTICIAS.getId());
        int threads = Runtime.getRuntime().availableProcessors();
        ExecutorService threadPool2 = Executors.newFixedThreadPool(threads);
        Runnable sn = new SincronizarNoticias(tlDs, j2Ds, ttrssDs,
                new ProcessCluster(noticiaDao.obtenerNoticiasNuevasDeA3()),
                new ProcessCuratedNews(articuloDao.obtenerArticulosNuevosDeA3()),
                categoriaDao.categoriaPorTituloTtrss(pais).getId());

        if (!procesandoNoticias) {
            procesosDao.cambiarEstadoDeProceso(Process.NOTICIAS.getId(), true);
            for (int i = 0; i < threads; i++) {
                Thread t = new Thread(sn);
                threadPool2.execute(t);
            }
        }

        threadPool2.shutdown();
        while (!threadPool2.isTerminated()) {
        }

        procesosDao.cambiarEstadoDeProceso(Process.NOTICIAS.getId(), false);

        ExecutorService threadPool3 = Executors.newFixedThreadPool(threads);
        if (!procesandoNoticias) {
            procesosDao.cambiarEstadoDeProceso(Process.NOTICIAS.getId(), true);
            List<ArticuloNoProcesado> articuloNoProcesados = articuloDao.articulosNoProcesados("fecha_sync");
            Runnable sincronizarFechas = new SincronizarFechasNoticia(tlDs, j2Ds, ttrssDs,
                    new ProcessNews(articuloNoProcesados));

            for (int i = 0; i < threads; i++) {
                Thread t = new Thread(sincronizarFechas);
                threadPool3.execute(t);
            }

        }
        threadPool3.shutdown();
        while (!threadPool3.isTerminated()) {
        }
        List<ArticuloNoProcesado> articuloNoProcesados = articuloDao.articulosNoProcesados("tags_sync");
        if (!articuloNoProcesados.isEmpty()) {
            SincronizarTagsNoticia sct = new SincronizarTagsNoticia(tlDs, j2Ds, ttrssDs);
            sct.actualizarTagsDeNoticia(articuloNoProcesados);
        }

        NotificacionesTN ntn = new NotificacionesTN(tlDs, j2Ds, ttrssDs);
        ntn.notificarATN();
        procesosDao.cambiarEstadoDeProceso(Process.NOTICIAS.getId(), false);

        if (noticiaDao.obtenerNoticiasNuevasDeA3().size() > 0) {
            this.iniciar();
        }
    }
}
