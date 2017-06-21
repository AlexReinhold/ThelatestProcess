package mapper.j2;

import models.j2.NewsImg;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NewsImgRowMapper<T> implements RowMapper<NewsImg> {

    public NewsImg mapRow(ResultSet rs, int rowNumb) throws SQLException {
        NewsImg newsImg = new NewsImg();
        newsImg.addId(rs.getInt("id"))
                .addContent(rs.getString("content"))
                .addOriginalContent(rs.getString("original_content"))
                .addWidth(rs.getInt("width"))
                .addHeight(rs.getInt("height"));
        return newsImg;
    }
}
