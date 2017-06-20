package service;

import common.ProcesarCuratedNew;
import common.ProcesarCluster;
import dao.*;
import models.a3.Cluster;
import models.a3.CuratedNew;
import models.a3.NewsImg;
import models.a3.NewsVideo;
import models.tn.*;
import models.ttrss.FeedCategories;
import models.ttrss.Source;
import org.apache.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import resource.TipoArchivo;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by nlperez on 1/11/17.
 */
public class SincronizarNoticias implements Runnable {

    private ProcesarCluster procesarCluster;
    private ProcesarCuratedNew procesarCuratedNew;
    private ArticuloDao articuloDao;
    private NoticiaDao noticiaDao;
    private CategoriaDao categoriaDao;
    private FuenteDao fuenteDao;
    private Logger logger;
    private Integer paisCategoriaId;

    public SincronizarNoticias(DataSource tnDaTaSource, DataSource a3DataSource, DataSource ttrssDateSource, ProcesarCluster pc, ProcesarCuratedNew pcn, Integer paisCategoriaId) {
        articuloDao = new ArticuloDao(tnDaTaSource, a3DataSource, ttrssDateSource);
        noticiaDao = new NoticiaDao(tnDaTaSource, a3DataSource, ttrssDateSource);
        categoriaDao = new CategoriaDao(tnDaTaSource, a3DataSource, ttrssDateSource);
        fuenteDao = new FuenteDao(tnDaTaSource, a3DataSource, ttrssDateSource);
        this.procesarCluster = pc;
        this.procesarCuratedNew = pcn;
        this.paisCategoriaId = paisCategoriaId;
    }


    public void run() {
        long inicio = System.currentTimeMillis();
        logger = Logger.getLogger(Thread.currentThread().getName());
        int i = 0;
        while (procesarCluster.isFinish()) {
            Cluster cluster = procesarCluster.obtenerCluster();
            if (SincronizarNoticiaTN(cluster) == 1) {
                i++;
            }
        }
        logger.info(i + " Noticias Sincronizadas por el Thread " + Thread.currentThread().getName());
        logger.info("--------------------------------------------------");
        int j = 0;
        while (procesarCuratedNew.isFinish()) {
            CuratedNew curatedNew = procesarCuratedNew.obtenerCuratedNew();
            if (SincronizarArticuloTN(curatedNew) == 1) {
                j++;
            }
        }
        long fin = System.currentTimeMillis();
        logger.info(j + " Articulos Sincronizados por el Thread " + Thread.currentThread().getName());
        logger.info("Articulos y noticias Sincronizadas En " + (fin - inicio) + " ms");
        logger.info("-----------------------------------------------------");
    }

    private int SincronizarNoticiaTN(Cluster cluster) {
        noticia noticia = new noticia();
        noticia.addVistas(0)
                .addCompartidos(0);


        /* ESTO ES PARA CUANDO SE MANEJEN NOTICIAS DE DIVERSOS PAISES
        List<FeedCategories> categoriasPaisTtRss = categoriaDao.paisesTtrss();

        if (!categoriasPaisTtRss.isEmpty()) {
            if (categoriasPaisTtRss.contains(cluster.getMainCatId())) {
                FeedCategories fc = categoriaDao.categoriaPorIdTtrss(cluster.getSubCatId());
                Categoria categoria = categoriaDao.categoriaPorSlugTN(fc.getSlug());
                noticia.addCategoria(categoria);
            } else if (!categoriasPaisTtRss.contains(cluster.getMainCatId())) {
                FeedCategories fcMain = categoriaDao.categoriaPorIdTtrss(cluster.getMainCatId());
                FeedCategories fcSub = categornoticiaiaDao.categoriaPorIdTtrss(cluster.getSubCatId());
                Categoria categoria = categoriaDao.categoriaPorSlugTN(fcSub.getSlug() + "-" + fcMain.getSlug());
                noticia.addCategoria(categoria);
            }
        } else {
            logger.error("Problema al Sincronizar las categorias");
            logger.error("Lista de paises desde la tabla de categorias en TTRSS vacía");
        }
        ////////////////////////////////////////////////////////////////////////////////*/
        try {
            if (cluster.getMainCatId() == this.paisCategoriaId) {
                FeedCategories fc = categoriaDao.categoriaPorIdTtrss(cluster.getSubCatId());
                Categoria categoria = categoriaDao.categoriaPorSlugTN(fc.getSlug());
                noticia.addCategoria(categoria);
            } else if (cluster.getMainCatId() != this.paisCategoriaId) {
                FeedCategories fcMain = categoriaDao.categoriaPorIdTtrss(cluster.getMainCatId());
                FeedCategories fcSub = categoriaDao.categoriaPorIdTtrss(cluster.getSubCatId());
                Categoria categoria = categoriaDao.categoriaPorSlugTN(fcSub.getSlug() + "-" + fcMain.getSlug());
                noticia.addCategoria(categoria);
            }
        } catch (EmptyResultDataAccessException e) {
            logger.error("Categoria para Noticia no encontrada -> ClusterId -> " + cluster.getId());
            return 0;
        }

        noticia.addPosicion(0) // 0 mientras averiguamos para q coño es esto
                .addTitulo(cluster.getTittle())
                .addIdExterno(cluster.getId())
                .addSlug(cluster.getSlug())
                .addDeadLine(new Timestamp(System.currentTimeMillis())) // por ahora currentdate
                .addTags("");

        try {
            noticia = noticiaDao.nuevaNoticiaTN(noticia);
            if (noticia.getId() > 0) {
                noticiaDao.actualizarEstadoDeNoticiaA3(cluster.getId());
                noticiaDao.nuevaNoticiaParaNotificacion(noticia.getId());
                return 1;
            } else {
                logger.error("Noticia no registrada tn TN " + noticia.getTitulo());
            }
        } catch (DuplicateKeyException e) {
            logger.error("Slug de noticia duplicado: " + noticia.getSlug() + " No fue sincronizada");
            logger.info("-----------------------------------------------------");
            noticiaDao.actualizarEstadoDeNoticiaA3(cluster.getId());
        }
        return 0;
    }

    private int SincronizarArticuloTN(CuratedNew curatedNew) {

        Source source = fuenteDao.fuenteDesdeTtrssPorId(curatedNew.getSource().getId());
        FuentePorArticulo fpa = fuenteDao.fuenteDesdeTNPorSlug(source.getSlug());
        noticia noticia;
        try {
            noticia = noticiaDao.noticiaPorSlugTN(curatedNew.getCluster().getSlug());
        } catch (EmptyResultDataAccessException e) {
            logger.error("Noticia no encontrada de acuerdo al slug -> " + curatedNew.getCluster().getSlug());
            return 0;
        }

        String contenidoHtml = "";
        if (!curatedNew.getNewsContent().getRawText().equals("")) {
            contenidoHtml = curatedNew.getNewsContent().getRawText();
            StringBuilder sb = new StringBuilder();
            for (String parrafo : contenidoHtml.split("\n")) {
                sb.append(parrafo).append("<br/>");
            }
            contenidoHtml = sb.toString();
        }

        Articulo articulo = new Articulo();
        articulo.addTitulo(curatedNew.getTitle())
                .addContenido(curatedNew.getNewsContent().getRawText())
                .addContenidoHtml(contenidoHtml)
                .addAutor(curatedNew.getAuthor())
                .addAutorFoto("")
                .addFechaPublicacion(curatedNew.getPubDate())
                .addFechaEntrada(curatedNew.getDateEntered())
                .addRemovido(0)
                .addSnippet(curatedNew.getSnippet())
                .addTags(curatedNew.getTags())
                .addUrl(curatedNew.getLink())
                .addFuentePorArticulo(fpa)
                .addNoticia(noticia);
        try {
            articulo = articuloDao.nuevoArticuloTN(articulo);
            if (articulo.getId() > 0) {
                articuloDao.nuevoArticuloNoProcesado(new ArticuloNoProcesado().addArticulo(articulo));
                articuloDao.actualizarEstadoDeArticuloA3(curatedNew.getId(), true);
                this.SincronizarMultimediaTN(articulo, curatedNew);
                return 1;
            } else {
                logger.error("articulo no registrado en TN " + articulo.getTitulo());
            }
        } catch (DataIntegrityViolationException e) {
            logger.error("Slug de noticia : " + noticia.getSlug() + " id " + noticia.getId());
            System.err.println(e.getMessage());
            logger.error(e.getMessage());
            logger.error(e.getCause());
        }

        return 0;
    }

    private void SincronizarMultimediaTN(Articulo articulo, CuratedNew curatedNew) {

        List<NewsVideo> videos = articuloDao.videosPorArticuloA3(curatedNew.getId());
        if (!videos.isEmpty()) {
            logger.info(videos.size() + " video(s) para el articulo -> " + articulo.getId());
            for (NewsVideo newsVideo : videos) {
                Multimedia videoArticulo = new Multimedia();
                videoArticulo.addArticulo(articulo)
                        .addUrl(newsVideo.getContent())
                        .addUrlOriginal(newsVideo.getOriginalContent())
                        .addTipoArchivo(TipoArchivo.VIDEO.getId())
                        .addAnchura(0)
                        .addAltura(0);

                videoArticulo = articuloDao.nuevoArchivoMultimediaTN(videoArticulo);
                if (videoArticulo.getId() < 1) {
                    logger.error("Error al registrar video para el articulo " + articulo.getId());
                }
            }
        }

        List<NewsImg> imagenes = articuloDao.imagenesPorArticuloA3(curatedNew.getId());
        if (!imagenes.isEmpty()) {
            for (NewsImg img : imagenes) {
                Multimedia imagenArticulo = new Multimedia();
                imagenArticulo.addArticulo(articulo)
                        .addUrl(img.getContent().replace("{", "").replace("}", ""))
                        .addUrlOriginal(img.getOriginalContent().replace("{", "").replace("}", ""))
                        .addTipoArchivo(TipoArchivo.IMAGEN.getId())
                        .addAnchura(img.getWidth())
                        .addAltura(img.getHeight());

                imagenArticulo = articuloDao.nuevoArchivoMultimediaTN(imagenArticulo);
                if (imagenArticulo.getId() < 1) {
                    logger.error("Error al registrar imgagen para el articulo " + articulo.getId());
                }
            }
        }
    }
}




