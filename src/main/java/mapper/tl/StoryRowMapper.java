package mapper.tl;

import models.tl.Category;
import models.tl.Story;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StoryRowMapper<T> implements RowMapper<Story> {

    public Story mapRow(ResultSet rs, int rowNumb) throws SQLException {

        return new Story().addId(rs.getInt("id"))
                .addViews(rs.getInt("vistas"))
                .addShares(rs.getInt("compartidos"))
                .addCategory(new Category().addId(rs.getInt("c_id"))
                        .addNombre(rs.getString("c_nombre"))
                        .addCategoriaPadre(rs.getInt("categoria_padre"))
                        .addIdExterno(rs.getInt("n_id_externo")))
                .addPosition(rs.getInt("posicion"))
                .addName(rs.getString("titulo"))
                .addExternalId(rs.getInt("n_id_externo"))
                .addSlug(rs.getString("slug"))
                .addDeadLine(rs.getTimestamp("deadline"))
                .addTags(rs.getString("tags"))
                .addPubDate(rs.getTimestamp("fecha_publicacion"))
                .addLastUpdate(rs.getTimestamp("ultima_actualizacion"))
                .addSourcesNumber(rs.getInt("numero_fuentes"));
    }

}
