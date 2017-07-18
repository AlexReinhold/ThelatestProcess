package dao;

import mapper.tl.CategoryRowMapper;
import model.tl.Category;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import resource.SQL;
import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class CategoryDao {

    private JdbcTemplate thelatestTemplate, j2Template, ttrssTemplate;

    public CategoryDao(DataSource tnDs, DataSource a3Ds, DataSource ttrssDs) {
        this.thelatestTemplate = new JdbcTemplate(tnDs);
        this.j2Template = new JdbcTemplate(a3Ds);
        this.ttrssTemplate = new JdbcTemplate(ttrssDs);
    }

    public Optional<Category> getByExternalId(String externalId){

        try{
            Category category = thelatestTemplate.queryForObject(SQL.TL.SELECTS.CATEGORY_BY_EXTERNAL_ID, new Object[]{externalId}, new CategoryRowMapper<Category>());
            return Optional.of(category);
        }catch (EmptyResultDataAccessException e){
            System.out.println("not found category: "+externalId);
            return Optional.empty();
        }
    }

    public List<Category> getParentCategories(){

        return thelatestTemplate.query(SQL.TL.SELECTS.PARENT_CATEGORIES, new Object[]{}, new CategoryRowMapper<Category>());

    }

}
