package mapper.elasticsearch;

import model.elasticsearch.News;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NewsRowMapper<T> implements RowMapper<News> {

    public News mapRow(ResultSet rs, int rowNumb) throws SQLException {
        News news = new News();
        news.addId(rs.getInt("id"))
                .addTitle(rs.getString("titulo"))
                .addContent(rs.getString("contenido"))
                .addPubDate(rs.getTimestamp("fecha_publicacion"))
                .addAddedDate(rs.getTimestamp("fecha_entrada"))
                .addSnippet(rs.getString("snippet"))
                .addTags(rs.getString("tags"))
                .addUrl(rs.getString("url"));
        return news;
    }

}
