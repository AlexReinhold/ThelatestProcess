package dao;

import org.springframework.jdbc.core.JdbcTemplate;
import resource.SQL;

import javax.sql.DataSource;

/**
 * Created by nlperez on 2/3/17.
 */
public class ProcesosDao {

    private JdbcTemplate tnTemplate;

    public ProcesosDao(DataSource tnDS) {
        this.tnTemplate = new JdbcTemplate(tnDS);
    }

    public boolean estadoDeProceso(int id) {
        return tnTemplate.queryForObject(SQL.TN.SELECTS.ESTADO_DE_PROCESO, new Object[]{id}, boolean.class);
    }

    public int cambiarEstadoDeProceso(int id, boolean estado) {
        return tnTemplate.update(SQL.TN.UPDATES.CAMBIAR_ESTADO_PROCESO, estado, id);
    }

}
