package mapper.tn;

import models.tl.Articulo;
import models.tl.ArticuloNoProcesado;
import models.tl.FuentePorArticulo;
import models.tl.noticia;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by nlperez on 5/25/17.
 */
public class ArticuloNoProcesadoRowMapper<T> implements RowMapper<ArticuloNoProcesado> {

    public ArticuloNoProcesado mapRow(ResultSet rs, int rowNumb) throws SQLException {
        ArticuloNoProcesado anp = new ArticuloNoProcesado();
            Articulo articulo = new Articulo().addId(rs.getInt("articulo_id"))
                    .addTitulo(rs.getString("titulo"))
                    .addContenidoHtml(rs.getString("contenido_html"))
                    .addAutor(rs.getString("autor"))
                    .addAutorFoto(rs.getString("autor_foto"))
                    .addFechaPublicacion(rs.getTimestamp("fecha_publicacion"))
                    .addFechaEntrada(rs.getTimestamp("fecha_entrada"))
                    .addRemovido(rs.getInt("removido"))
                    .addSnippet(rs.getString("snippet"))
                    .addTags(rs.getString("tags"))
                    .addUrl(rs.getString("url"))
                    .addFuentePorArticulo(new FuentePorArticulo().addId(rs.getInt("fpa_id")))
                    .addNoticia(new noticia().addId(rs.getInt("ar_noticia_id")));
        anp.addArticulo(articulo)
                .addId(rs.getInt("id"))
                .addFechaSync(rs.getBoolean("fecha_sync"))
                .addTagsSync(rs.getBoolean("tags_sync"))
                .addNumeroFuentesSync(rs.getBoolean("numero_fuentes_sync"));
        return anp;
    }
}
