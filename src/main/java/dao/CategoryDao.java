package dao;

import mapper.ttrss.FeedCategoriesRowMapper;
import mapper.tl.CategoryRowMapper;
import models.tl.Category;
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

public class CategoryDao {

    private JdbcTemplate thelatestTemplate, j2Template, ttrssTemplate;

    public CategoryDao(DataSource tnDs, DataSource a3Ds, DataSource ttrssDs) {
        this.thelatestTemplate = new JdbcTemplate(tnDs);
        this.j2Template = new JdbcTemplate(a3Ds);
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

    public List<Category> todasLasCategoriasDesdeTN() {
        return thelatestTemplate.query(SQL.TL.SELECTS.CATEGORIAS, new CategoryRowMapper<Category>());
    }

    public List<Category> categoriasPadreTN() {
        return thelatestTemplate.query(SQL.TL.SELECTS.CATEGORIAS_PADRE, new CategoryRowMapper<Category>());
    }

    public List<Category> subCategoriasTN() {
        return thelatestTemplate.query(SQL.TL.SELECTS.SUB_CATEGORIAS, new CategoryRowMapper<Category>());
    }

    public Category categoriaPorSlugTN(String slug){
        return thelatestTemplate.queryForObject(SQL.TL.SELECTS.CATEGORIA_POR_SLUG, new Object[]{slug}, new CategoryRowMapper<Category>());
    }

    public Category getByExternalId(String externalId){
        return thelatestTemplate.queryForObject(SQL.TL.SELECTS.CATEGORY_BY_EXTERNAL_ID, new Object[]{externalId}, new CategoryRowMapper<Category>());
    }

    public int nuevaCategoriaPadreTN(final Category categoria) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        thelatestTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection)
                    throws SQLException {
                PreparedStatement ps = connection.prepareStatement(SQL.TL.INSERTS.NUEVA_CATEGORIA, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, categoria.getNombre());
                ps.setInt(2, categoria.getIdExterno());
                ps.setString(3, categoria.getSlug());
                return ps;
            }
        }, keyHolder);
        return ((Integer) keyHolder.getKeys().get("id"));
    }

    public int nuevaSubCategoriaTN(final Category categoria) {
       return thelatestTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection)
                    throws SQLException {
                PreparedStatement ps = connection.prepareStatement(SQL.TL.INSERTS.NUEVA_SUB_CATEGORIA);
                ps.setString(1, categoria.getNombre());
                ps.setInt(2, categoria.getCategoriaPadre());
                ps.setInt(3, categoria.getIdExterno());
                ps.setString(4, categoria.getSlug());
                return ps;
            }
        });
    }

    public int actualizarCategoriaTN (Category categoria) {
        return thelatestTemplate.update(SQL.TL.UPDATES.ACTUALIZAR_CATEGORIA, categoria.getNombre(), categoria.getIdExterno(), categoria.getId());
    }
}
