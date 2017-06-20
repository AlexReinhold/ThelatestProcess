package mapper.tn;

import models.tn.*;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by nlperez on 12/22/16.
 */
public class ArticuloRowMapper<T> implements RowMapper<Articulo> {

    public Articulo mapRow(ResultSet rs, int rowNumb) throws SQLException {
        Articulo articulo = new Articulo();
        articulo.addId(rs.getInt("id"))
                .addTitulo(rs.getString("titulo"))
                .addContenido(rs.getString("contenido"))
                .addContenidoHtml(rs.getString("contenido_html"))
                .addAutor(rs.getString("autor"))
                .addAutorFoto(rs.getString("autor_foto"))
                .addFechaPublicacion(rs.getTimestamp("fecha_publicacion"))
                .addFechaEntrada(rs.getTimestamp("fecha_entrada"))
                .addRemovido(rs.getInt("removido"))
                .addSnippet(rs.getString("snippet"))
                .addTags(rs.getString("tags"))
                .addUrl(rs.getString("url"));
        FuentePorArticulo fpa = new FuentePorArticulo();
        fpa.addId(rs.getInt("fpa_id"));
        Fuente fuente = new Fuente();
        fuente.addId(rs.getInt("fpa_fuente_id"))
                .addNombre(rs.getString("fn_nombre"))
                .addUrl(rs.getString("fn_url"))
                .addIdExterno(rs.getInt("fn_id_externo"))
                .addSlug(rs.getString("slug"))
                .addNombreCorto(rs.getString("nombre_corto"));
        fpa.addFuente(fuente);
        Tipofuente tf = new Tipofuente();
        tf.addId(rs.getInt("fpa_tipo_fuente_id"))
                .addTipo(rs.getString("tf_tipo"));
        fpa.addTipoFuente(tf);
        articulo.addFuentePorArticulo(fpa);
        // Ojo en este mapper falta meter los datos de ciudades y
        // paises para cuando se decida implementar
        return articulo;
    }

}
