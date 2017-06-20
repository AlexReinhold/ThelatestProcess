package dao;

import mapper.a3.CuratedNewRowMapper;
import mapper.a3.NewsImgRowMapper;
import mapper.a3.NewsVideoRowMapper;
import mapper.tn.ArticuloNoProcesadoRowMapper;
import mapper.tn.ArticuloRowMapper;
import models.a3.CuratedNew;
import models.a3.NewsImg;
import models.a3.NewsVideo;
import models.tn.Articulo;
import models.tn.ArticuloNoProcesado;
import models.tn.Multimedia;
import models.tn.noticia;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import resource.SQL;
import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

/**
 * Created by nlperez on 1/11/17.
 */
public class ArticuloDao {

    private JdbcTemplate tnTemplate, a3Template, ttrssTemplate;

    public ArticuloDao(DataSource tnDs, DataSource a3Ds, DataSource ttrssDs) {
        this.tnTemplate = new JdbcTemplate(tnDs);
        this.a3Template = new JdbcTemplate(a3Ds);
        this.ttrssTemplate = new JdbcTemplate(ttrssDs);

    }

    public List<Articulo> articulosPorIdNoticia(int idNoticia) {
        return tnTemplate.query(SQL.TN.SELECTS.ARTICULOS_POR_ID_NOTICIA, new Object[]{idNoticia}, new ArticuloRowMapper<Articulo>());
    }

    public Articulo nuevoArticuloTN(final Articulo articulo) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        tnTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL.TN.INSERTS.NUEVO_ARTICULO, Statement.RETURN_GENERATED_KEYS);
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
        return a3Template.query(SQL.A3.SELECTS.ARTICULOS_NO_SINCRONIZADOS, new CuratedNewRowMapper<CuratedNew>());
    }

    public int actualizarEstadoDeArticuloA3(int id, boolean estado) {
        return a3Template.update(SQL.A3.UPDATES.CAMBIAR_ESTADO_ARTICULO, estado, id);
    }

    public List<NewsVideo> videosPorArticuloA3(int curatedNewId) {
        return a3Template.query(SQL.A3.SELECTS.VIDEOS_POR_ARTICULO, new Object[]{curatedNewId}, new NewsVideoRowMapper<NewsVideo>());
    }

    public List<NewsImg> imagenesPorArticuloA3(int curatedNewId) {
        return a3Template.query(SQL.A3.SELECTS.IMAGENES_POR_ARTICULO, new Object[]{curatedNewId}, new NewsImgRowMapper<NewsImg>());
    }

    public Multimedia nuevoArchivoMultimediaTN(final Multimedia multimedia) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        tnTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL.TN.INSERTS.NUEVO_ARCHIVO_MULTIMEDIA, Statement.RETURN_GENERATED_KEYS);
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



    public List<ArticuloNoProcesado> articulosNoProcesados(String field) {
        return tnTemplate.query(SQL.TN.SELECTS.ARTICULOS_NO_PROCESADOS.replace("*field*", field), new ArticuloNoProcesadoRowMapper<ArticuloNoProcesado>());
    }

    public List<ArticuloNoProcesado> articulosNoProcesadosAll() {
        return tnTemplate.query(SQL.TN.SELECTS.ARTICULOS_NO_PROCESADOS_ALL, new ArticuloNoProcesadoRowMapper<ArticuloNoProcesado>());
    }

    public int nuevoArticuloNoProcesado(final ArticuloNoProcesado articuloNoProcesado) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        tnTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL.TN.INSERTS.NUEVO_ARTICULO_NO_PROCESADO, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, articuloNoProcesado.getArticulo().getId());
            return ps;
        }, keyHolder);
        return ((Integer) keyHolder.getKeys().get("id"));
    }


    public int actualizarEstadoArticuloNoProcesado(ArticuloNoProcesado articuloNoProcesado, String field, boolean estado) {
        return tnTemplate.update(SQL.TN.UPDATES.CAMBIAR_ESTADO_ARTICULO_NO_PROCESADO.replace("*field*", field), estado, articuloNoProcesado.getId());
    }

    public int eliminarArticuloNoProcesado(ArticuloNoProcesado articuloNoProcesado) {
        return tnTemplate.update(SQL.TN.DELETE.ELIMINAR_ARTICULO_NO_PROCESADO, articuloNoProcesado.getId());
    }

}
