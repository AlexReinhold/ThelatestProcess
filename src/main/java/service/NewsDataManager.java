package service;

import common.ProcessCuratedNews;
import dao.SourceDao;
import dao.NewsDao;
import dao.StoryDao;
import models.j2.CuratedNew;
import models.j2.NewsImg;
import models.j2.NewsVideo;
import models.tl.*;
import models.ttrss.Source;
import org.apache.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import resource.FileType;

import javax.sql.DataSource;
import java.util.List;

public class NewsDataManager implements Runnable {

    private ProcessCuratedNews processCuratedNews;
    private NewsDao newsDao;
    private StoryDao storyDao;
    private SourceDao fuenteDao;
    private Logger logger;

    public NewsDataManager(DataSource tnDaTaSource, DataSource a3DataSource, DataSource ttrssDateSource, ProcessCuratedNews pcn) {
        newsDao = new NewsDao(tnDaTaSource, a3DataSource, ttrssDateSource);
        storyDao = new StoryDao(tnDaTaSource, a3DataSource, ttrssDateSource);
        fuenteDao = new SourceDao(tnDaTaSource, a3DataSource, ttrssDateSource);
        this.processCuratedNews = pcn;
    }


    public void run() {
        long inicio = System.currentTimeMillis();
        logger = Logger.getLogger(Thread.currentThread().getName());
        int j = 0;
        while (processCuratedNews.isFinish()) {
            CuratedNew curatedNew = processCuratedNews.obtenerCuratedNew();
            if (syncNews(curatedNew) == 1) {
                j++;
            }
        }
        long fin = System.currentTimeMillis();
        logger.info(j + " Articulos Sincronizados por el Thread " + Thread.currentThread().getName());
        logger.info("Articulos y noticias Sincronizadas En " + (fin - inicio) + " ms");
        logger.info("-----------------------------------------------------");
    }

    private int syncNews(CuratedNew curatedNew) {

        Source source = fuenteDao.fuenteDesdeTtrssPorId(curatedNew.getSource().getId());
//        FuentePorArticulo fpa = fuenteDao.fuenteDesdeTNPorSlug(source.getSlug());
        Story noticia;
        try {
            noticia = storyDao.noticiaPorSlugTN(curatedNew.getCluster().getSlug());
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

        News articulo = new News();
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
                .addNoticia(noticia);
        try {
            articulo = newsDao.nuevoArticuloTN(articulo);
            if (articulo.getId() > 0) {
                newsDao.nuevoArticuloNoProcesado(new UnprocessedNews().addArticulo(articulo));
                newsDao.actualizarEstadoDeArticuloA3(curatedNew.getId(), true);
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

    private void SincronizarMultimediaTN(News articulo, CuratedNew curatedNew) {

        List<NewsVideo> videos = newsDao.videosPorArticuloA3(curatedNew.getId());
        if (!videos.isEmpty()) {
            logger.info(videos.size() + " video(s) para el articulo -> " + articulo.getId());
            for (NewsVideo newsVideo : videos) {
                Multimedia videoArticulo = new Multimedia();
                videoArticulo.addArticulo(articulo)
                        .addUrl(newsVideo.getContent())
                        .addUrlOriginal(newsVideo.getOriginalContent())
                        .addTipoArchivo(FileType.VIDEO.getId())
                        .addAnchura(0)
                        .addAltura(0);

                videoArticulo = newsDao.nuevoArchivoMultimediaTN(videoArticulo);
                if (videoArticulo.getId() < 1) {
                    logger.error("Error al registrar video para el articulo " + articulo.getId());
                }
            }
        }

        List<NewsImg> imagenes = newsDao.imagenesPorArticuloA3(curatedNew.getId());
        if (!imagenes.isEmpty()) {
            for (NewsImg img : imagenes) {
                Multimedia imagenArticulo = new Multimedia();
                imagenArticulo.addArticulo(articulo)
                        .addUrl(img.getContent().replace("{", "").replace("}", ""))
                        .addUrlOriginal(img.getOriginalContent().replace("{", "").replace("}", ""))
                        .addTipoArchivo(FileType.IMAGEN.getId())
                        .addAnchura(img.getWidth())
                        .addAltura(img.getHeight());

                imagenArticulo = newsDao.nuevoArchivoMultimediaTN(imagenArticulo);
                if (imagenArticulo.getId() < 1) {
                    logger.error("Error al registrar imgagen para el articulo " + articulo.getId());
                }
            }
        }
    }
}




