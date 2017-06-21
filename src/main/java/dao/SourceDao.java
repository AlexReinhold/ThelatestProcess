package dao;

import mapper.ttrss.SourceRowMapper;
import models.tl.Source;
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

public class SourceDao {

    private JdbcTemplate tnTemplate, a3Template, ttrssTemplate;

    public SourceDao(DataSource tnDs, DataSource a3Ds, DataSource ttrssDs) {
        this.tnTemplate = new JdbcTemplate(tnDs);
        this.a3Template = new JdbcTemplate(a3Ds);
        this.ttrssTemplate = new JdbcTemplate(ttrssDs);
    }

    public List<models.ttrss.Source> fuentesDesdeTtrss() {
        return ttrssTemplate.query(SQL.TTRRSS.SELECTS.FUENTES, new SourceRowMapper<models.ttrss.Source>());
    }

    public models.ttrss.Source fuenteDesdeTtrssPorId(int id) {
        return ttrssTemplate.queryForObject(SQL.TTRRSS.SELECTS.FUENTE_POR_ID, new Object[]{id}, new SourceRowMapper<models.ttrss.Source>());
    }

    @Transactional(rollbackFor = DuplicateKeyException.class)
    public Source nuevaFuenteTN(final Source fuente) {
        KeyHolder holder = new GeneratedKeyHolder();
        tnTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection)
                    throws SQLException {
                PreparedStatement ps = connection.prepareStatement(SQL.TL.INSERTS.NUEVA_FUENTE, Statement.RETURN_GENERATED_KEYS);
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

    public int nuevaFuentePorArticuloTN(final Source fuente) {
        return tnTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(SQL.TL.INSERTS.NUEVA_FUENTE_POR_ARTICULO);
                ps.setInt(1, fuente.getId());
                ps.setInt(2, 1);
                return ps;
            }
        });
    }

    public int actualizarFuenteTN (final Source fuente) {
        return tnTemplate.update(SQL.TL.UPDATES.ACTUALIZAR_FUENTE, fuente.getNombre(), fuente.getUrl(), fuente.getIdExterno(),
                fuente.getNombreCorto(), fuente.getFavicon(), fuente.getSlug());
    }

}
