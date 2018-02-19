package mapper.elasticsearch;

import model.elasticsearch.Category;
import model.elasticsearch.FeedES;
import model.elasticsearch.Source;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FeedESRowMapper<T> implements RowMapper<FeedES> {

    public FeedES mapRow(ResultSet rs, int rowNumb) throws SQLException {
        FeedES feed = new FeedES();
        feed.addId(rs.getInt("id"))
            .addTitle(rs.getString("title"))
            .addPublicationDate(rs.getTimestamp("publication_date"))
            .addSnippet(rs.getString("snippet"))
            .addImgUrl(rs.getString("imgurl"))
            .addUrl(rs.getString("url"))
            .addSource(new Source().addId(rs.getInt("source_id"))
                .addName(rs.getString("source_name")))
            .addStory(rs.getInt("story_id"))
            .addCategory(new Category().addId(rs.getInt("category_id"))
                .addSlug(rs.getString("category_slug"))
                .addName(rs.getString("category_name"))
                .addParent(rs.getString("category_parent")));
        return feed;
    }

}