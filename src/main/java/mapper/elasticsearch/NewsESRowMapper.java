package mapper.elasticsearch;

import model.elasticsearch.Category;
import model.elasticsearch.NewsES;
import model.elasticsearch.Source;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NewsESRowMapper<T> implements RowMapper<NewsES> {

    public NewsES mapRow(ResultSet rs, int rowNumb) throws SQLException {
        NewsES news = new NewsES();
        news.addId(rs.getInt("id"))
            .addTitle(rs.getString("titulo"))
            .addPublicationDate(rs.getTimestamp("fecha_publicacion"))
            .addSnippet(rs.getString("contenido"))
            .addImgUrl(rs.getString("tags"))
            .addSource(new Source().addId(rs.getInt("source_id"))
                .addName("sosurce_name"))
            .addStory(news.new Story().addId(rs.getInt("story_id"))
                .addName(rs.getString("story_name"))
                .addSlug(rs.getString("story_slug"))
                .addCategory(new Category().addId(rs.getInt("category_id"))
                    .addSlug(rs.getString("category_slug"))
                    .addName(rs.getString("category_name"))
                    .addParent(rs.getString("category_parent"))));
        return news;
    }

}
