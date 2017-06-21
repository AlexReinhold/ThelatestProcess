package mapper.ttrss;

import models.ttrss.Source;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class SourceRowMapper<T> implements RowMapper<Source> {

        public Source mapRow(ResultSet rs, int rowNumb) throws SQLException{
            Source source = new Source();
            source.addId(rs.getInt("id"));
            source.addTitle(rs.getString("title"));
            source.addFeedUrl(rs.getString("feed_url"));
            source.addIconUrl(rs.getString("icon_url"));
            source.addSiteUrl(rs.getString("site_url"));
            return source;
        }
}
