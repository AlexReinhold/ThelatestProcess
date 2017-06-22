package dao;

import mapper.tl.CategoryRowMapper;
import models.tl.Category;
import org.springframework.jdbc.core.JdbcTemplate;
import resource.SQL;
import javax.sql.DataSource;

public class CategoryDao {

    private JdbcTemplate thelatestTemplate, j2Template, ttrssTemplate;

    public CategoryDao(DataSource tnDs, DataSource a3Ds, DataSource ttrssDs) {
        this.thelatestTemplate = new JdbcTemplate(tnDs);
        this.j2Template = new JdbcTemplate(a3Ds);
        this.ttrssTemplate = new JdbcTemplate(ttrssDs);
    }

    public Category getByExternalId(String externalId){
        return thelatestTemplate.queryForObject(SQL.TL.SELECTS.CATEGORY_BY_EXTERNAL_ID, new Object[]{externalId}, new CategoryRowMapper<Category>());
    }

}
