package dao;

import javax.sql.DataSource;

import mapper.j2.ClusterRowMapper;
import mapper.tl.StoryRowMapper;
import models.j2.Cluster;
import models.tl.Story;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import resource.SQL;
import java.sql.*;
import java.util.List;

public class StoryDao {

    private JdbcTemplate thelatestTemplate, j2Template, ttrssTemplate;

    public StoryDao(DataSource tnDs, DataSource a3Ds, DataSource ttrssDs) {
        this.thelatestTemplate = new JdbcTemplate(tnDs);
        this.j2Template = new JdbcTemplate(a3Ds);
        this.ttrssTemplate = new JdbcTemplate(ttrssDs);
    }

    public Story storyBySlug(String slug) {
        return thelatestTemplate.queryForObject(SQL.TL.SELECTS.NOTICIA_POR_SLUG, new Object[]{slug}, new StoryRowMapper<Story>());
    }

    public Story insertStory(final Story story) {
        KeyHolder holder = new GeneratedKeyHolder();
        thelatestTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL.TL.INSERTS.NEW_STORY, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, story.getViews());
            ps.setInt(2, story.getShares());
            ps.setInt(3, story.getCategory().getId());
            ps.setInt(4, story.getPosition());
            ps.setString(5, story.getName());
            ps.setInt(6, story.getExternalId());
            ps.setString(7, story.getSlug());
            ps.setTimestamp(8, story.getDeadLine());
            ps.setString(9, story.getTags());
            return ps;
        }, holder);
        story.addId((Integer) holder.getKeys().get("id"));
        return story;
    }

    public int updateStoryStateJ2(int storyId) {
        return j2Template.update(SQL.J2.UPDATES.CHANGE_STORY_STATE, true, storyId);
    }

    public List<Cluster> getClusterListFromJ2() {
        return j2Template.query(SQL.J2.SELECTS.UNPROCESSED_STORIES, new ClusterRowMapper<Cluster>());
    }

}
