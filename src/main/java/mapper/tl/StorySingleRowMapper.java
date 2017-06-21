package mapper.tl;

import models.tl.Category;
import models.tl.Story;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StorySingleRowMapper<T> implements RowMapper<Story> {

    public Story mapRow(ResultSet rs, int rowNumb) throws SQLException {
        Story story = new Story();
        story.addId(rs.getInt("id"));
        story.addVistas(rs.getInt("vistas"));
        story.addCompartidos(rs.getInt("compartidos"));
        story.addCategoria(new Category().addId(rs.getInt("categoria_id")));
        story.addPosicion(rs.getInt("posicion"));
        story.addTitulo(rs.getString("titulo"));
        story.addIdExterno(rs.getInt("id_externo"));
        story.addSlug(rs.getString("slug"));
        story.addDeadLine(rs.getTimestamp("deadline"));
        story.addTags(rs.getString("tags"));
        return story;
    }
}
