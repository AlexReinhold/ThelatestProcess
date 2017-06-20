package mapper.tn;

import models.tl.Categoria;
import models.tl.noticia;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by nlperez on 2/17/17.
 */
public class NoticiaSingleRowMapper<T> implements RowMapper<noticia> {

    public noticia mapRow(ResultSet rs, int rowNumb) throws SQLException {
        noticia noticia = new noticia();
        noticia.addId(rs.getInt("id"));
        noticia.addVistas(rs.getInt("vistas"));
        noticia.addCompartidos(rs.getInt("compartidos"));
        noticia.addCategoria(new Categoria().addId(rs.getInt("categoria_id")));
        noticia.addPosicion(rs.getInt("posicion"));
        noticia.addTitulo(rs.getString("titulo"));
        noticia.addIdExterno(rs.getInt("id_externo"));
        noticia.addSlug(rs.getString("slug"));
        noticia.addDeadLine(rs.getTimestamp("deadline"));
        noticia.addTags(rs.getString("tags"));
        return noticia;
    }
}
