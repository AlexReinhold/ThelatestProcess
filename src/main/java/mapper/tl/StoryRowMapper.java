package mapper.tl;

import model.tl.Category;
import model.tl.Story;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StoryRowMapper<T> implements RowMapper<Story> {

    public Story mapRow(ResultSet rs, int rowNumb) throws SQLException {

        return new Story().addId(rs.getInt("id"))
                .addName(rs.getString("name"))
                .addExternalId(rs.getInt("external_id"))
                .addSlug(rs.getString("slug"))
                .addViews(rs.getInt("views"))
                .addDeadLine(rs.getTimestamp("deadline"))
                .addPosition(rs.getInt("position"))
                .addShares(rs.getInt("share"))
                .addTags(rs.getString("tags"))
                .addCategory(new Category().addId(rs.getInt("c_id"))
                        .addParentId(rs.getInt("parent_id"))
                        .addName(rs.getString("c_name"))
                        .addActive(rs.getBoolean("c_active"))
                        .addMenuOrder(rs.getInt("menu_order"))
                        .addExternalId(rs.getInt("c_external_id"))
                        .addSlug(rs.getString("c_slug")));
    }

}
