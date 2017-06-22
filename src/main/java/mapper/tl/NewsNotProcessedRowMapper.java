package mapper.tl;

import models.tl.News;
import models.tl.UnprocessedNews;
import models.tl.Story;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NewsNotProcessedRowMapper<T> implements RowMapper<UnprocessedNews> {

    public UnprocessedNews mapRow(ResultSet rs, int rowNumb) throws SQLException {
        UnprocessedNews unprocessedNews = new UnprocessedNews();
            News news = new News().addId(rs.getInt("articulo_id"))
                    .addTitle(rs.getString("titulo"))
                    .addContenidoHtml(rs.getString("contenido_html"))
                    .addAutor(rs.getString("autor"))
                    .addAutorFoto(rs.getString("autor_foto"))
                    .addFechaPublicacion(rs.getTimestamp("fecha_publicacion"))
                    .addFechaEntrada(rs.getTimestamp("fecha_entrada"))
                    .addRemovido(rs.getInt("removido"))
                    .addSnippet(rs.getString("snippet"))
                    .addTags(rs.getString("tags"))
                    .addUrl(rs.getString("url"))
                    .addNoticia(new Story().addId(rs.getInt("ar_noticia_id")));
        unprocessedNews.addArticulo(news)
                .addId(rs.getInt("id"))
                .addFechaSync(rs.getBoolean("fecha_sync"))
                .addTagsSync(rs.getBoolean("tags_sync"))
                .addNumeroFuentesSync(rs.getBoolean("numero_fuentes_sync"));
        return unprocessedNews;
    }
}
