package mapper.tl;

import models.tl.Source;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SourceRowMapper<T> implements RowMapper<Source> {

    public Source mapRow(ResultSet rs, int rowNumb) throws SQLException{
        return new Source()
        .addId(rs.getInt("id"))
        .addName(rs.getString("name"))
        .addSection(rs.getString("section"))
        .addExternalId(rs.getString("external_id"))
        .addAlias(rs.getString("alias"))
        .addSourceFeedUrl(rs.getString("source_feed_url"))
        .addUrl(rs.getString("sourceurl"))
        .addIconUrl(rs.getString("icon_url"));
    }
}
