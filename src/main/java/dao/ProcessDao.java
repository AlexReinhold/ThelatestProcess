package dao;

import org.springframework.jdbc.core.JdbcTemplate;
import resource.SQL;

import javax.sql.DataSource;

public class ProcessDao {

    private JdbcTemplate tnTemplate;

    public ProcessDao(DataSource tnDS) {
        this.tnTemplate = new JdbcTemplate(tnDS);
    }

    public boolean estadoDeProceso(int id) {
        return tnTemplate.queryForObject(SQL.TN.SELECTS.ESTADO_DE_PROCESO, new Object[]{id}, boolean.class);
    }

    public int cambiarEstadoDeProceso(int id, boolean estado) {
        return tnTemplate.update(SQL.TN.UPDATES.CAMBIAR_ESTADO_PROCESO, estado, id);
    }

}
