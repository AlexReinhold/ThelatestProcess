package dao;

import mapper.tl.SourceRowMapper;
import models.tl.Source;
import org.springframework.jdbc.core.JdbcTemplate;
import resource.SQL;
import javax.sql.DataSource;

public class SourceDao {

    private JdbcTemplate thelatestTemplate, a3Template, ttrssTemplate;

    public SourceDao(DataSource tnDs, DataSource a3Ds, DataSource ttrssDs) {
        this.thelatestTemplate = new JdbcTemplate(tnDs);
        this.a3Template = new JdbcTemplate(a3Ds);
        this.ttrssTemplate = new JdbcTemplate(ttrssDs);
    }

    public Source sourceByExternalId(String id) {
        return thelatestTemplate.queryForObject(SQL.TL.SELECTS.SOURCE_BY_EXTERNAL_ID, new Object[]{id}, new SourceRowMapper<Source>());
    }

}
