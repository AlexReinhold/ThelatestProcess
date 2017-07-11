package dao;

import mapper.j2.CuratedNewRowMapper;
import model.j2.CuratedNew;
import model.tl.News;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import resource.SQL;
import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class NewsDao {

    private JdbcTemplate tnTemplate, a3Template, ttrssTemplate;

    public NewsDao(DataSource tnDs, DataSource a3Ds, DataSource ttrssDs) {
        this.tnTemplate = new JdbcTemplate(tnDs);
        this.a3Template = new JdbcTemplate(a3Ds);
        this.ttrssTemplate = new JdbcTemplate(ttrssDs);

    }

    public News insertNews(News news) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        tnTemplate.update(connection -> {
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

    public List<CuratedNew> getCuratedNewsListFromJ2() {
        return a3Template.query(SQL.J2.SELECTS.UNPROCESSED_NEWS, new CuratedNewRowMapper<CuratedNew>());
    }

    public int updateNewsStateJ2(int id, boolean estado) {
        return a3Template.update(SQL.J2.UPDATES.CHANGE_NEWS_STATE, estado, id);
    }

}