package dao;

import mapper.tl.SourceRowMapper;
import model.tl.Source;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import resource.SQL;
import javax.sql.DataSource;
import java.util.Optional;

public class SourceDao {

    private JdbcTemplate thelatestTemplate, a3Template, ttrssTemplate;

    public SourceDao(DataSource tnDs, DataSource a3Ds, DataSource ttrssDs) {
        this.thelatestTemplate = new JdbcTemplate(tnDs);
        this.a3Template = new JdbcTemplate(a3Ds);
        this.ttrssTemplate = new JdbcTemplate(ttrssDs);
    }

    public Optional<Source> sourceByExternalId(String id) {
        try{
            Source source = thelatestTemplate.queryForObject(SQL.TL.SELECTS.SOURCE_BY_EXTERNAL_ID, new Object[]{id}, new SourceRowMapper<Source>());
            return Optional.of(source);
        }catch (EmptyResultDataAccessException ex){
            System.out.println("error source not found: "+id);
            return Optional.empty();
        }
    }

}
