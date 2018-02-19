package dao;

import mapper.elasticsearch.FeedESRowMapper;
import mapper.elasticsearch.NewsESRowMapper;
import mapper.j2.CuratedNewRowMapper;
import model.elasticsearch.FeedES;
import model.elasticsearch.NewsES;
import model.j2.CuratedNews;
import model.tl.News;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import resource.SQL;
import javax.sql.DataSource;
import java.sql.*;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class NewsDao {

    private JdbcTemplate thelatestTemplate, j2Template, ttrssTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public NewsDao(DataSource tnDs, DataSource a3Ds, DataSource ttrssDs) {
        this.thelatestTemplate = new JdbcTemplate(tnDs);
        this.j2Template = new JdbcTemplate(a3Ds);
        this.ttrssTemplate = new JdbcTemplate(ttrssDs);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(thelatestTemplate.getDataSource());
    }

    public News insertNews(News news) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        thelatestTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL.TL.INSERTS.INSERT_NEWS, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, news.getStory().getId());
            ps.setInt(2, news.getSource().getId());
            ps.setString(3, news.getUrl());
            ps.setString(4, news.getExternalId());
            ps.setString(5, news.getTitle());
            ps.setString(6, news.getSnippet());
            ps.setString(7, news.getImgUrl());
            ps.setString(8, news.getAuthor());
            ps.setInt(9, news.getScore());
            ps.setTimestamp(10, news.getPubDate());
            ps.setTimestamp(11, news.getAddedDate());
            ps.setBoolean(12, news.isStaffPicks());
            ps.setString(13, news.getContent());
            ps.setString(14, news.getTags());

            return ps;
        }, keyHolder);
        news.addId((Integer) keyHolder.getKeys().get("id"));
        return news;
    }

    public List<CuratedNews> getCuratedNewsListFromJ2(int categoryId) {
        return j2Template.query(SQL.J2.SELECTS.UNPROCESSED_NEWS, new Object[]{categoryId}, new CuratedNewRowMapper<CuratedNews>());
    }

    public int updateNewsStateJ2(int id, boolean estado) {
        return j2Template.update(SQL.J2.UPDATES.CHANGE_NEWS_STATE, estado, id);
    }

    public Optional<List<NewsES>> getNewsForES(List<Integer> listId){
        Map idsMap = Collections.singletonMap("ids", listId);
        try{
            List<NewsES> newsESList = namedParameterJdbcTemplate.query(
                    SQL.TL.SELECTS.NEWS_BY_ID_FOR_ES, idsMap, new NewsESRowMapper<NewsES>());
            return Optional.of(newsESList);
        }catch (EmptyResultDataAccessException ex){
            System.out.println("error news for elastic search empty");
            return Optional.empty();
        }
    }

    public Optional<List<FeedES>> getFeedsForES(int from, int size){
        try{
            List<FeedES> feedES = thelatestTemplate.query(SQL.TL.SELECTS.FEED_FOR_ES_BY_RANGE, new Object[]{from, size}, new FeedESRowMapper<FeedES>());
            return Optional.of(feedES);
        }catch (EmptyResultDataAccessException ex){
            System.out.println("error feeds for elastic search empty");
            return Optional.empty();
        }
    }

    public int countAllFeeds() {
        return thelatestTemplate.queryForObject(SQL.TL.SELECTS.COUNT_FEED_FOR_ES_BY_RANGE, Integer.class);
    }

}