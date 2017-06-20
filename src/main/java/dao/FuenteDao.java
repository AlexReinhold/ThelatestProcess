package dao;

import mapper.tn.FuentePorArticuloRowMapper;
import mapper.ttrss.SourceRowMapper;
import models.tl.Fuente;
import models.tl.FuentePorArticulo;
import models.ttrss.Source;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;
import resource.SQL;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by nlperez on 1/16/17.
 */
public class FuenteDao {

    private JdbcTemplate tnTemplate, a3Template, ttrssTemplate;

    public FuenteDao(DataSource tnDs, DataSource a3Ds, DataSource ttrssDs) {
        this.tnTemplate = new JdbcTemplate(tnDs);
        this.a3Template = new JdbcTemplate(a3Ds);
        this.ttrssTemplate = new JdbcTemplate(ttrssDs);
    }

    public List<Source> fuentesDesdeTtrss() {
        return ttrssTemplate.query(SQL.TTRRSS.SELECTS.FUENTES, new SourceRowMapper<Source>());
    }

    public Source fuenteDesdeTtrssPorId(int id) {
        return ttrssTemplate.queryForObject(SQL.TTRRSS.SELECTS.FUENTE_POR_ID, new Object[]{id}, new SourceRowMapper<Source>());
    }

    public List<FuentePorArticulo> fuentesDesdeTN() {
        return tnTemplate.query(SQL.TN.SELECTS.FUENTES, new FuentePorArticuloRowMapper<FuentePorArticulo>());
    }

    public FuentePorArticulo fuenteDesdeTNPorSlug(String slug) {
        return tnTemplate.queryForObject(SQL.TN.SELECTS.FUENTE_POR_SLUG, new Object[]{slug}, new FuentePorArticuloRowMapper<Fuente>());
    }

    @Transactional(rollbackFor = DuplicateKeyException.class)
    public Fuente nuevaFuenteTN(final Fuente fuente) {
        KeyHolder holder = new GeneratedKeyHolder();
        tnTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection)
                    throws SQLException {
                PreparedStatement ps = connection.prepareStatement(SQL.TN.INSERTS.NUEVA_FUENTE, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, fuente.getNombre());
                ps.setString(2, fuente.getUrl());
                ps.setInt(3, fuente.getIdExterno());
                ps.setString(4, fuente.getSlug());
                ps.setString(5, fuente.getNombreCorto());
                ps.setString(6, fuente.getFavicon());
                return ps;
            }
        }, holder);
        fuente.addId((Integer) holder.getKeys().get("id"));
        return fuente;
    }

    public int nuevaFuentePorArticuloTN(final Fuente fuente) {
        return tnTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(SQL.TN.INSERTS.NUEVA_FUENTE_POR_ARTICULO);
                ps.setInt(1, fuente.getId());
                ps.setInt(2, 1);
                return ps;
            }
        });
    }

    public int actualizarFuenteTN (final Fuente fuente) {
        return tnTemplate.update(SQL.TN.UPDATES.ACTUALIZAR_FUENTE, fuente.getNombre(), fuente.getUrl(), fuente.getIdExterno(),
                fuente.getNombreCorto(), fuente.getFavicon(), fuente.getSlug());
    }

    public List<FuentePorArticulo> fuentesPorNoticia(int idNoticia) {
        return tnTemplate.query(SQL.TN.SELECTS.FUENTES_POR_NOTICIA, new Object[]{idNoticia}, new FuentePorArticuloRowMapper<FuentePorArticulo>());
    }
}
