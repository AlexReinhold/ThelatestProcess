package dao;

import javax.sql.DataSource;

import mapper.j2.ClusterRowMapper;
import mapper.tl.StoryRowMapper;
import models.j2.Cluster;
import models.tl.Story;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import resource.SQL;

import java.sql.*;
import java.util.List;
import java.util.Map;

public class StoryDao {

    private JdbcTemplate tnTemplate, a3Template, ttrssTemplate;

    public StoryDao(DataSource tnDs, DataSource a3Ds, DataSource ttrssDs) {
        this.tnTemplate = new JdbcTemplate(tnDs);
        this.a3Template = new JdbcTemplate(a3Ds);
        this.ttrssTemplate = new JdbcTemplate(ttrssDs);

    }

    public Story noticiaPorId(int id) {
        return tnTemplate.queryForObject(SQL.TL.SELECTS.NOTICIA_POR_ID, new Object[]{id}, new StoryRowMapper<Story>());
    }

    public Story noticiaPorSlugTN(String slug) {
        return tnTemplate.queryForObject(SQL.TL.SELECTS.NOTICIA_POR_SLUG, new Object[]{slug}, new StoryRowMapper<Story>());
    }

    public List<Story> todasLasNoticias() {
        return tnTemplate.query(SQL.TL.SELECTS.TODAS_LAS_NOTICIAS, new StoryRowMapper<Story>());
    }

    public Story nuevaNoticiaTN(final Story noticia) {
        KeyHolder holder = new GeneratedKeyHolder();
        tnTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL.TL.INSERTS.NUEVA_NOTICIA, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, noticia.getVistas());
            ps.setInt(2, noticia.getCompartidos());
            ps.setInt(3, noticia.getCategoria().getId());
            ps.setInt(4, noticia.getPosicion()); /* -> revisar este campo (posicion) */
            ps.setString(5, noticia.getTitulo());
            ps.setInt(6, noticia.getIdExterno());
            ps.setString(7, noticia.getSlug());
            ps.setTimestamp(8, noticia.getDeadLine());
            ps.setString(9, noticia.getTags());
            return ps;
        }, holder);
        noticia.addId((Integer) holder.getKeys().get("id"));
        return noticia;
    }

    public int nuevaNoticiaParaNotificacion(int id) {
        return tnTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL.TL.INSERTS.NUEVA_NOTICIA_NOTIFICACION);
            ps.setInt(1, id);
            return ps;
        });
    }

    public List<Integer> noticiasParaNotificacion() {
        return tnTemplate.queryForList(SQL.TL.SELECTS.NOTICIAS_PARA_NOTIFICACION, Integer.class);
    }

    public int eliminarNoticiasDeNotificacion() {
        return tnTemplate.update(SQL.TL.DELETE.ELIMINAR_NOTICIAS_PARA_NOTIFICACION);
    }

    public int actualizarTagsDeNoticia(String tags, int idNoticia) {
        return tnTemplate.update(SQL.TL.UPDATES.ACTUALIZAR_TAGS_DE_NOTICIA, tags, idNoticia);
    }

    public int actualizarFechasDeNoticia(Story noticia) {
        return tnTemplate.update(SQL.TL.UPDATES.ACTUALIZAR_FECHAS_NOTICIA, noticia.getFechaPublicacion(),
                noticia.getUltimaActualizacion(), noticia.getId());
    }

    public int actualizarEstadoDeNoticiaA3(int idNoticia) {
        return a3Template.update(SQL.J2.UPDATES.CAMBIAR_ESTADO_NOTICIA, true, idNoticia);
    }

    public List<Cluster> obtenerNoticiasNuevasDeA3() {
        return a3Template.query(SQL.J2.SELECTS.NOTICIAS_NO_SINCRONIZADAS, new ClusterRowMapper<Cluster>());
    }

    public Map<String, Object> fechasNoticia(int idNoticia) {
        return tnTemplate.queryForMap(SQL.TL.SELECTS.FECHAS_NOTICIA, idNoticia);
    }

    public int actualizarFuentesDeNoticia(Story noticia) {
        return tnTemplate.update(SQL.TL.UPDATES.ACTUALIZAR_NUMERO_FUENTES_NOTICIA, noticia.getNumeroFuentes(), noticia.getId());
    }

}
