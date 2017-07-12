package mapper.tl;

import model.tl.Category;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryRowMapper<T> implements RowMapper<Category> {

    public Category mapRow(ResultSet rs, int rowNum) throws SQLException{
        return new Category()
            .addId(rs.getInt("id"))
            .addNombre(rs.getString("name"))
            .addCategoriaPadre(rs.getInt("parent_id"))
            .addIdExterno(rs.getInt("external_id"))
            .addSlug(rs.getString("slug"))
            .addActive(rs.getBoolean("active"))
            .addMenuOrder(rs.getInt("menu_order"));
    }

}
