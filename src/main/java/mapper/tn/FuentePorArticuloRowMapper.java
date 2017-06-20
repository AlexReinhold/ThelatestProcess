package mapper.tn;

import models.tl.Fuente;
import models.tl.FuentePorArticulo;
import models.tl.Tipofuente;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by nlperez on 1/16/17.
 */
public class FuentePorArticuloRowMapper<T> implements RowMapper<FuentePorArticulo> {

    public FuentePorArticulo mapRow(ResultSet rs, int rowNumb) throws SQLException{
        FuentePorArticulo fpa = new FuentePorArticulo();
            Fuente fuente = new Fuente();
            fuente.addId(rs.getInt("fuente_id"))
                    .addNombre(rs.getString("nombre"))
                    .addUrl(rs.getString("url"))
                    .addIdExterno(rs.getInt("id_externo"))
                    .addSlug(rs.getString("slug"))
                    .addNombreCorto(rs.getString("nombre_corto"))
                    .addFavicon(rs.getString("favicon"));
            Tipofuente tipofuente = new Tipofuente();
            tipofuente.addId(rs.getInt("tipo_fuente_id"))
                    .addTipo(rs.getString("tipo"));
        fpa.addId(rs.getInt("id"))
                .addFuente(fuente)
                .addTipoFuente(tipofuente);
        return fpa;
    }
}
