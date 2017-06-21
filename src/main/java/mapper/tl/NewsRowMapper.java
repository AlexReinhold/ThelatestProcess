package mapper.tl;

import models.tl.*;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NewsRowMapper<T> implements RowMapper<News> {

    public News mapRow(ResultSet rs, int rowNumb) throws SQLException {
        News news = new News();
        news.addId(rs.getInt("id"))
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
        return news;
    }

}
