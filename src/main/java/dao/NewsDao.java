package dao;

import mapper.j2.CuratedNewRowMapper;
import mapper.j2.NewsImgRowMapper;
import mapper.j2.NewsVideoRowMapper;
import mapper.tl.NewsNotProcessedRowMapper;
import mapper.tl.NewsRowMapper;
import models.j2.CuratedNew;
import models.j2.NewsImg;
import models.j2.NewsVideo;
import models.tl.News;
import models.tl.UnprocessedNews;
import models.tl.Multimedia;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import resource.SQL;
import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class NewsDao {

    private JdbcTemplate tnTemplate, a3Template, ttrssTemplate;

    public NewsDao(DataSource tnDs, DataSource a3Ds, DataSource ttrssDs) {
        this.tnTemplate = new JdbcTemplate(tnDs);
        this.a3Template = new JdbcTemplate(a3Ds);
        this.ttrssTemplate = new JdbcTemplate(ttrssDs);

    }

    public List<News> articulosPorIdNoticia(int idNoticia) {
        return tnTemplate.query(SQL.TL.SELECTS.ARTICULOS_POR_ID_NOTICIA, new Object[]{idNoticia}, new NewsRowMapper<News>());
    }

    public News nuevoArticuloTN(final News articulo) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        tnTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL.TL.INSERTS.NUEVO_ARTICULO, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, articulo.getTitulo());
            ps.setString(2, articulo.getContenido());
            ps.setString(3, articulo.getContenidoHtml());
            ps.setInt(4, articulo.getFuentePorArticulo().getId());
            ps.setInt(5, articulo.getNoticia().getId());
            ps.setString(6, articulo.getAutor());
            ps.setString(7, articulo.getAutorFoto());
            ps.setTimestamp(8, articulo.getFechaPublicacion());
            ps.setTimestamp(9, articulo.getFechaEntrada());
            ps.setInt(10, articulo.getRemovido());
            ps.setString(11, articulo.getSnippet());
            ps.setString(12, articulo.getTags());
            ps.setString(13, articulo.getUrl());
            return ps;
        }, keyHolder);
        articulo.addId((Integer) keyHolder.getKeys().get("id"));
        return articulo;
    }

    public List<CuratedNew> obtenerArticulosNuevosDeA3() {
        return a3Template.query(SQL.J2.SELECTS.ARTICULOS_NO_SINCRONIZADOS, new CuratedNewRowMapper<CuratedNew>());
    }

    public int actualizarEstadoDeArticuloA3(int id, boolean estado) {
        return a3Template.update(SQL.J2.UPDATES.CAMBIAR_ESTADO_ARTICULO, estado, id);
    }

    public List<NewsVideo> videosPorArticuloA3(int curatedNewId) {
        return a3Template.query(SQL.J2.SELECTS.VIDEOS_POR_ARTICULO, new Object[]{curatedNewId}, new NewsVideoRowMapper<NewsVideo>());
    }

    public List<NewsImg> imagenesPorArticuloA3(int curatedNewId) {
        return a3Template.query(SQL.J2.SELECTS.IMAGENES_POR_ARTICULO, new Object[]{curatedNewId}, new NewsImgRowMapper<NewsImg>());
    }

    public Multimedia nuevoArchivoMultimediaTN(final Multimedia multimedia) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        tnTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL.TL.INSERTS.NUEVO_ARCHIVO_MULTIMEDIA, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, multimedia.getArticulo().getId());
            ps.setString(2, multimedia.getUrl());
            ps.setString(3, multimedia.getUrlOriginal());
            ps.setInt(4, multimedia.getTipoArchivo());
            ps.setInt(5, multimedia.getAnchura());
            ps.setInt(6, multimedia.getAltura());
            return ps;
        }, keyHolder);
        multimedia.addId((Integer) keyHolder.getKeys().get("id"));
        return multimedia;
    }



    public List<UnprocessedNews> articulosNoProcesados(String field) {
        return tnTemplate.query(SQL.TL.SELECTS.ARTICULOS_NO_PROCESADOS.replace("*field*", field), new NewsNotProcessedRowMapper<UnprocessedNews>());
    }

    public List<UnprocessedNews> articulosNoProcesadosAll() {
        return tnTemplate.query(SQL.TL.SELECTS.ARTICULOS_NO_PROCESADOS_ALL, new NewsNotProcessedRowMapper<UnprocessedNews>());
    }

    public int nuevoArticuloNoProcesado(final UnprocessedNews articuloNoProcesado) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        tnTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL.TL.INSERTS.NUEVO_ARTICULO_NO_PROCESADO, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, articuloNoProcesado.getArticulo().getId());
            return ps;
        }, keyHolder);
        return ((Integer) keyHolder.getKeys().get("id"));
    }


    public int actualizarEstadoArticuloNoProcesado(UnprocessedNews articuloNoProcesado, String field, boolean estado) {
        return tnTemplate.update(SQL.TL.UPDATES.CAMBIAR_ESTADO_ARTICULO_NO_PROCESADO.replace("*field*", field), estado, articuloNoProcesado.getId());
    }

    public int eliminarArticuloNoProcesado(UnprocessedNews articuloNoProcesado) {
        return tnTemplate.update(SQL.TL.DELETE.ELIMINAR_ARTICULO_NO_PROCESADO, articuloNoProcesado.getId());
    }

}
