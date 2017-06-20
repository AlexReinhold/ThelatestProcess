package common;

import dao.ArticuloDao;
import dao.CategoriaDao;
import dao.NoticiaDao;
import dao.ProcesosDao;
import datasource.A3DataSource;
import datasource.TnDataSource;
import datasource.ttrssDataSource;
import models.tn.ArticuloNoProcesado;
import resource.Procesos;
import service.*;

import javax.sql.DataSource;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class IniciarProceso {

    private DataSource a3Ds, tnDs, ttrssDs;
    private NoticiaDao noticiaDao;
    private ArticuloDao articuloDao;
    private ProcesosDao procesosDao;
    private CategoriaDao categoriaDao;

    IniciarProceso() throws Exception {
        a3Ds = new A3DataSource().getDatasource();
        tnDs = new TnDataSource().getDatasource();
        ttrssDs = new ttrssDataSource().getDatasource();
        noticiaDao = new NoticiaDao(tnDs, a3Ds, ttrssDs);
        this.categoriaDao = new CategoriaDao(tnDs, a3Ds, ttrssDs);
        articuloDao = new ArticuloDao(tnDs, a3Ds, ttrssDs);
        procesosDao = new ProcesosDao(tnDs);
    }

    void iniciar() throws Exception {

        String pais = "";
        boolean procensadoFuentes, procesandoCategorias, procesandoNoticias;


//        ExecutorService threadPool1 = Executors.newFixedThreadPool(2);
//        Runnable sinc1 = new SincronizarCategorias(tnDs, a3Ds, ttrssDs);
//        Runnable sinc2 = new SincronizarFuentes(tnDs, a3Ds, ttrssDs);
//
//        procesandoCategorias = procesosDao.estadoDeProceso(Procesos.CATEGORIAS.getId());
//        procensadoFuentes = procesosDao.estadoDeProceso(Procesos.FUENTES.getId());
//
//        if (!procesandoCategorias) {
//            Thread t1 = new Thread(sinc1);
//            procesosDao.cambiarEstadoDeProceso(Procesos.CATEGORIAS.getId(), true);
//            threadPool1.execute(t1);
//        }
//        if (!procensadoFuentes) {
//            Thread t2 = new Thread(sinc2);
//            procesosDao.cambiarEstadoDeProceso(Procesos.FUENTES.getId(), true);
//            threadPool1.execute(t2);
//        }
//
//        threadPool1.shutdown();
//        while (!threadPool1.isTerminated()) {
//        }

        procesosDao.cambiarEstadoDeProceso(Procesos.CATEGORIAS.getId(), false);
        procesosDao.cambiarEstadoDeProceso(Procesos.FUENTES.getId(), false);

        procesandoNoticias = procesosDao.estadoDeProceso(Procesos.NOTICIAS.getId());
        int threads = Runtime.getRuntime().availableProcessors();
        ExecutorService threadPool2 = Executors.newFixedThreadPool(threads);
        Runnable sn = new SincronizarNoticias(tnDs, a3Ds, ttrssDs,
                new ProcesarCluster(noticiaDao.obtenerNoticiasNuevasDeA3()),
                new ProcesarCuratedNew(articuloDao.obtenerArticulosNuevosDeA3()),
                categoriaDao.categoriaPorTituloTtrss(pais).getId());

        if (!procesandoNoticias) {
            procesosDao.cambiarEstadoDeProceso(Procesos.NOTICIAS.getId(), true);
            for (int i = 0; i < threads; i++) {
                Thread t = new Thread(sn);
                threadPool2.execute(t);
            }
        }

        threadPool2.shutdown();
        while (!threadPool2.isTerminated()) {
        }

        procesosDao.cambiarEstadoDeProceso(Procesos.NOTICIAS.getId(), false);

        ExecutorService threadPool3 = Executors.newFixedThreadPool(threads);
        if (!procesandoNoticias) {
            procesosDao.cambiarEstadoDeProceso(Procesos.NOTICIAS.getId(), true);
            List<ArticuloNoProcesado> articuloNoProcesados = articuloDao.articulosNoProcesados("fecha_sync");
            Runnable sincronizarFechas = new SincronizarFechasNoticia(tnDs, a3Ds, ttrssDs,
                    new ProcesarArticulo(articuloNoProcesados));

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
            SincronizarTagsNoticia sct = new SincronizarTagsNoticia(tnDs, a3Ds, ttrssDs);
            sct.actualizarTagsDeNoticia(articuloNoProcesados);
        }

        NotificacionesTN ntn = new NotificacionesTN(tnDs, a3Ds, ttrssDs);
        ntn.notificarATN();
        procesosDao.cambiarEstadoDeProceso(Procesos.NOTICIAS.getId(), false);

        if (noticiaDao.obtenerNoticiasNuevasDeA3().size() > 0) {
            this.iniciar();
        }
    }
}
