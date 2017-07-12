package mapper.elasticsearch;

import model.elasticsearch.Category;
import model.elasticsearch.Source;
import model.elasticsearch.StoryES;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StoryESRowMapper<T> implements RowMapper<StoryES> {

    public StoryES mapRow(ResultSet rs, int rowNumb) throws SQLException {

        StoryES s = new StoryES();
        return s.addId(rs.getInt("id"))                                         //*
            .addName(rs.getString("name"))                                      //*
            .addSlug(rs.getString("slug"))                                      //*
            .addPosition(rs.getInt("position"))                                 //*
            .addDeadline(rs.getTimestamp("deadline"))                           //*
            .addCategory(new Category().addId(rs.getInt("category_id"))         //*
                .addSlug(rs.getString("category_slug"))                         //*
                .addName(rs.getString("category_name"))                         //*
                .addParent(rs.getString("category_parent")))                    //*
            .addNewsCount(rs.getInt("newscount"))                               //*
            .addSourcesCount(rs.getInt("sourcesCount"))                         //*
//            .addSourcesString(rs.getString("sourcesString"))
            .addPubDateFromMostRecentNews(rs.getTimestamp("news_pundate"))      //*
            .addScore(rs.getInt("views"), rs.getInt("wtms"))                    //*
            .addMostRecentNews(s.new RecentNews().addId(rs.getInt("news_id"))   //*
                .addTitle(rs.getString("news_title"))                           //*
                .addSnippet(rs.getString("news_imgurl"))                        //*
                .addPublicationDate(rs.getTimestamp("news_pundate"))            //*
                .addSource(new Source().addId(rs.getInt("source_id"))           //*
                    .addName(rs.getString("source_name"))));                    //*
    }

}
