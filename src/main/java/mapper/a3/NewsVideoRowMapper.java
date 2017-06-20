package mapper.a3;

import models.a3.NewsVideo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by nlperez on 4/21/17.
 */
public class NewsVideoRowMapper<T> implements RowMapper<NewsVideo> {

    public NewsVideo mapRow(ResultSet rs, int rowNumb) throws SQLException {
        NewsVideo nwv = new NewsVideo();
        nwv.addId(rs.getInt("id"))
            .addContent(rs.getString("content"))
            .addOriginalContent(rs.getString("original_content"));
        return nwv;
    }

}
