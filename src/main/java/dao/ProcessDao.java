package dao;

import org.springframework.jdbc.core.JdbcTemplate;
import resource.SQL;

import javax.sql.DataSource;

public class ProcessDao {

    private JdbcTemplate thelatestTemplate;

    public ProcessDao(DataSource tnDS) {
        this.thelatestTemplate = new JdbcTemplate(tnDS);
    }

    public boolean state(int id) {
        return thelatestTemplate.queryForObject(SQL.TL.SELECTS.ESTADO_DE_PROCESO, new Object[]{id}, boolean.class);
    }

    public int changeState(int id, boolean state) {
        return thelatestTemplate.update(SQL.TL.UPDATES.CAMBIAR_ESTADO_PROCESO, state, id);
    }

}
