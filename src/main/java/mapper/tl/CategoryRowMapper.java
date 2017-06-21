package mapper.tl;

import models.tl.Category;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryRowMapper<T> implements RowMapper<Category> {

    public Category mapRow(ResultSet rs, int rowNum) throws SQLException{
        Category category = new Category();
        category.addId(rs.getInt("id"));
        category.addNombre(rs.getString("nombre"));
        category.addCategoriaPadre(rs.getInt("categoria_padre"));
        category.addIdExterno(rs.getInt("id_externo"));
        category.addSlug(rs.getString("slug"));
        return category;
    }

}
