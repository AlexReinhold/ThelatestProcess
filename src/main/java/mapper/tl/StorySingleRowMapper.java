package mapper.tl;

import model.tl.Category;
import model.tl.Story;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StorySingleRowMapper<T> implements RowMapper<Story> {

    public Story mapRow(ResultSet rs, int rowNumb) throws SQLException {
        Story story = new Story();
        story.addId(rs.getInt("id"));
        story.addViews(rs.getInt("vistas"));
        story.addShares(rs.getInt("compartidos"));
        story.addCategory(new Category().addId(rs.getInt("categoria_id")));
        story.addPosition(rs.getInt("posicion"));
        story.addName(rs.getString("titulo"));
        story.addExternalId(rs.getInt("id_externo"));
        story.addSlug(rs.getString("slug"));
        story.addDeadLine(rs.getTimestamp("deadline"));
        story.addTags(rs.getString("tags"));
        return story;
    }
}
