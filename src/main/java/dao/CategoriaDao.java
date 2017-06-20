package dao;

import mapper.ttrss.FeedCategoriesRowMapper;
import mapper.tn.CategoriaRowMapper;
import models.tn.Categoria;
import models.ttrss.FeedCategories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import resource.SQL;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by nlperez on 1/12/17.
 */
public class CategoriaDao {

    private JdbcTemplate tnTemplate, a3Template, ttrssTemplate;

    public CategoriaDao(DataSource tnDs, DataSource a3Ds, DataSource ttrssDs) {
        this.tnTemplate = new JdbcTemplate(tnDs);
        this.a3Template = new JdbcTemplate(a3Ds);
        this.ttrssTemplate = new JdbcTemplate(ttrssDs);
    }

    public List<FeedCategories> categoriasPadreTtrss(String pais) {
        return ttrssTemplate.query(SQL.TTRRSS.SELECTS.CATEGORIAS_PADRE , new Object[]{pais}, new FeedCategoriesRowMapper<FeedCategories>());
    }

    public List<FeedCategories> subCategoriasTtrss() {
        return ttrssTemplate.query(SQL.TTRRSS.SELECTS.SUB_CATEGORIAS, new FeedCategoriesRowMapper<FeedCategories>());
    }

    public List<FeedCategories> categoriasDeTtrss() {
        return ttrssTemplate.query(SQL.TTRRSS.SELECTS.CATEGORIAS, new FeedCategoriesRowMapper<FeedCategories>());
    }

    public List<FeedCategories> paisesTtrss() {
        return ttrssTemplate.query(SQL.TTRRSS.SELECTS.PAISES, new FeedCategoriesRowMapper<FeedCategories>());
    }

    public FeedCategories categoriaPorTituloTtrss(String title){
        return ttrssTemplate.queryForObject(SQL.TTRRSS.SELECTS.CATEGORIA_POR_TITULO, new Object[]{title}, new FeedCategoriesRowMapper<FeedCategories>());
    }

    public FeedCategories categoriaPorIdTtrss(int id) {
        return ttrssTemplate.queryForObject(SQL.TTRRSS.SELECTS.CATEGORIA_POR_ID, new Object[]{id}, new FeedCategoriesRowMapper<FeedCategories>());
    }

    public List<Categoria> todasLasCategoriasDesdeTN() {
        return tnTemplate.query(SQL.TN.SELECTS.CATEGORIAS, new CategoriaRowMapper<Categoria>());
    }

    public List<Categoria> categoriasPadreTN() {
        return tnTemplate.query(SQL.TN.SELECTS.CATEGORIAS_PADRE, new CategoriaRowMapper<Categoria>());
    }

    public List<Categoria> subCategoriasTN() {
        return tnTemplate.query(SQL.TN.SELECTS.SUB_CATEGORIAS, new CategoriaRowMapper<Categoria>());
    }

    public Categoria categoriaPorSlugTN(String slug){
        return tnTemplate.queryForObject(SQL.TN.SELECTS.CATEGORIA_POR_SLUG, new Object[]{slug}, new CategoriaRowMapper<Categoria>());
    }

    public int nuevaCategoriaPadreTN(final Categoria categoria) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        tnTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection)
                    throws SQLException {
                PreparedStatement ps = connection.prepareStatement(SQL.TN.INSERTS.NUEVA_CATEGORIA, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, categoria.getNombre());
                ps.setInt(2, categoria.getIdExterno());
                ps.setString(3, categoria.getSlug());
                return ps;
            }
        }, keyHolder);
        return ((Integer) keyHolder.getKeys().get("id"));
    }

    public int nuevaSubCategoriaTN(final Categoria categoria) {
       return tnTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection)
                    throws SQLException {
                PreparedStatement ps = connection.prepareStatement(SQL.TN.INSERTS.NUEVA_SUB_CATEGORIA);
                ps.setString(1, categoria.getNombre());
                ps.setInt(2, categoria.getCategoriaPadre());
                ps.setInt(3, categoria.getIdExterno());
                ps.setString(4, categoria.getSlug());
                return ps;
            }
        });
    }

    public int actualizarCategoriaTN (Categoria categoria) {
        return tnTemplate.update(SQL.TN.UPDATES.ACTUALIZAR_CATEGORIA, categoria.getNombre(), categoria.getIdExterno(), categoria.getId());
    }
}
