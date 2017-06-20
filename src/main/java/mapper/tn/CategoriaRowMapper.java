package mapper.tn;

import models.tn.Categoria;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by nlperez on 12/29/16.
 */
public class CategoriaRowMapper<T> implements RowMapper<Categoria> {

    public Categoria mapRow(ResultSet rs, int rowNum) throws SQLException{
        Categoria categoria = new Categoria();
        categoria.addId(rs.getInt("id"));
        categoria.addNombre(rs.getString("nombre"));
        categoria.addCategoriaPadre(rs.getInt("categoria_padre"));
        categoria.addIdExterno(rs.getInt("id_externo"));
        categoria.addSlug(rs.getString("slug"));
        return categoria;
    }

}
