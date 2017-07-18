package dao;

import javax.sql.DataSource;

import mapper.elasticsearch.StoryESRowMapper;
import mapper.j2.ClusterRowMapper;
import mapper.tl.StoryRowMapper;
import model.elasticsearch.StoryES;
import model.j2.Cluster;
import model.tl.Story;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import resource.SQL;
import java.sql.*;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class StoryDao {

    private JdbcTemplate thelatestTemplate, j2Template, ttrssTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public StoryDao(DataSource tnDs, DataSource a3Ds, DataSource ttrssDs) {
        this.thelatestTemplate = new JdbcTemplate(tnDs);
        this.j2Template = new JdbcTemplate(a3Ds);
        this.ttrssTemplate = new JdbcTemplate(ttrssDs);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(thelatestTemplate.getDataSource());
    }

    public Story storyBySlug(String slug) {
        return thelatestTemplate.queryForObject(SQL.TL.SELECTS.STORY_BY_SLUG, new Object[]{slug}, new StoryRowMapper<Story>());
    }

    public Story insertStory(final Story story) {
        KeyHolder holder = new GeneratedKeyHolder();
        thelatestTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL.TL.INSERTS.NEW_STORY, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, story.getCategory().getId());
            ps.setString(2, story.getName());
            ps.setInt(3, story.getExternalId());
            ps.setString(4, story.getSlug());
            ps.setInt(5, story.getViews());
            ps.setTimestamp(6, story.getDeadLine());
            ps.setInt(7, story.getPosition());
            ps.setInt(8, story.getShares());
            ps.setString(9, story.getTags());

            return ps;

        }, holder);
        story.addId((Integer) holder.getKeys().get("id"));
        return story;
    }

    public int updateStoryStateJ2(int storyId) {
        return j2Template.update(SQL.J2.UPDATES.CHANGE_STORY_STATE, true, storyId);
    }

    public List<Cluster> getClusterListFromJ2(int categoryId) {
        return j2Template.query(SQL.J2.SELECTS.UNPROCESSED_STORIES, new Object[]{categoryId}, new ClusterRowMapper<Cluster>());
    }

    public Optional<List<StoryES>> getStoriesForES(List<Integer> listId){
        Map idsMap = Collections.singletonMap("ids", listId);
        try{
            List<StoryES> storyES = namedParameterJdbcTemplate.query(SQL.TL.SELECTS.STORY_BY_ID_FOR_ES, idsMap, new StoryESRowMapper<StoryES>());
            return Optional.of(storyES);
        }catch (EmptyResultDataAccessException ex){
            System.out.println("error stories for elastic search empty");
            return Optional.empty();
        }
    }

    public Optional<List<StoryES>> getStoriesByNewsIdForES(List<Integer> listId){
        Map idsMap = Collections.singletonMap("ids", listId);
        try{
            List<StoryES> storyES = namedParameterJdbcTemplate.query(SQL.TL.SELECTS.STORY_BY_NEWS_ID_FOR_ES, idsMap, new StoryESRowMapper<StoryES>());
            return Optional.of(storyES);
        }catch (EmptyResultDataAccessException ex){
            System.out.println("error stories for elastic search empty");
            return Optional.empty();
        }
    }

}
