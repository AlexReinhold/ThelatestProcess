package mapper.ttrss;

import models.ttrss.FeedCategories;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by nlperez on 1/12/17.
 */
public class FeedCategoriesRowMapper<T> implements RowMapper<FeedCategories> {

    public FeedCategories mapRow(ResultSet rs, int rowNumb) throws SQLException{
        FeedCategories feedCategories = new FeedCategories();
        feedCategories.addId(rs.getInt("id"));
        feedCategories.addOwner_uid(rs.getInt("owner_uid"));
        feedCategories.addCollapsed(rs.getBoolean("collapsed"));
        feedCategories.addOrderId(rs.getInt("order_id"));
        feedCategories.addViewSettings(rs.getString("view_settings"));
        feedCategories.addParentCat(rs.getInt("parent_cat"));
        feedCategories.addTitle(rs.getString("title"));
        return feedCategories;
    }

}
