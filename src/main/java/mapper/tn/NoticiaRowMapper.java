package mapper.tn;

import models.tn.Categoria;
import models.tn.noticia;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by nlperez on 1/20/17.
 */
public class NoticiaRowMapper<T> implements RowMapper<noticia> {

    public noticia mapRow(ResultSet rs, int rowNumb) throws SQLException {

        return new noticia().addId(rs.getInt("id"))
                .addVistas(rs.getInt("vistas"))
                .addCompartidos(rs.getInt("compartidos"))
                .addCategoria(new Categoria().addId(rs.getInt("c_id"))
                        .addNombre(rs.getString("c_nombre"))
                        .addCategoriaPadre(rs.getInt("categoria_padre"))
                        .addIdExterno(rs.getInt("n_id_externo")))
                .addPosicion(rs.getInt("posicion"))
                .addTitulo(rs.getString("titulo"))
                .addIdExterno(rs.getInt("n_id_externo"))
                .addSlug(rs.getString("slug"))
                .addDeadLine(rs.getTimestamp("deadline"))
                .addTags(rs.getString("tags"))
                .addFechaPublicacion(rs.getTimestamp("fecha_publicacion"))
                .addUltimaActualizacion(rs.getTimestamp("ultima_actualizacion"))
                .addNumeroFuentes(rs.getInt("numero_fuentes"));
    }

}
