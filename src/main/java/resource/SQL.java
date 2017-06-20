package resource;

public class SQL {

    public static final class TN {

        public static class SELECTS {

            public static final String NOTICIA_POR_SLUG = "" +
                    "SELECT n.id, " +
                    "       n.vistas, " +
                    "       n.compartidos, " +
                    "       n.posicion, " +
                    "       n.titulo, " +
                    "       n.id_externo AS n_id_externo, " +
                    "       n.slug, " +
                    "       n.deadline, " +
                    "       n.tags, " +
                    "       n.fecha_publicacion, " +
                    "       n.ultima_actualizacion, " +
                    "       n.numero_fuentes, " +
                    "       c.id AS c_id, " +
                    "       c.nombre AS c_nombre, " +
                    "       c.categoria_padre, " +
                    "       c.id_externo AS c_id_externo, " +
                    "       c.slug AS c_slug " +
                    "FROM noticia n " +
                    "     INNER JOIN categoria c ON c.id = n.categoria_id " +
                    "WHERE n.slug = ? ";

            public static final String NOTICIA_POR_ID = "" +
                    "SELECT n.id, " +
                    "       n.vistas, " +
                    "       n.compartidos, " +
                    "       n.posicion, " +
                    "       n.titulo, " +
                    "       n.id_externo AS n_id_externo, " +
                    "       n.slug, " +
                    "       n.deadline, " +
                    "       n.tags, " +
                    "       c.id AS c_id, " +
                    "       n.fecha_publicacion, " +
                    "       n.ultima_actualizacion, " +
                    "       n.numero_fuentes, " +
                    "       c.nombre AS c_nombre, " +
                    "       c.categoria_padre, " +
                    "       c.id_externo AS c_id_externo, " +
                    "       c.slug AS c_slug " +
                    "FROM noticia n " +
                    "     INNER JOIN categoria c ON c.id = n.categoria_id " +
                    "WHERE n.id = ? ";

            public static final String TODAS_LAS_NOTICIAS = "" +
                    "SELECT n.id, " +
                    "       n.vistas, " +
                    "       n.compartidos, " +
                    "       n.posicion, " +
                    "       n.titulo, " +
                    "       n.id_externo AS n_id_externo, " +
                    "       n.slug, " +
                    "       n.deadline, " +
                    "       n.tags, " +
                    "       n.fecha_publicacion, " +
                    "       n.ultima_actualizacion, " +
                    "       n.numero_fuentes, " +
                    "       c.id AS c_id, " +
                    "       c.nombre AS c_nombre, " +
                    "       c.categoria_padre, " +
                    "       c.id_externo AS c_id_externo, " +
                    "       c.slug AS c_slug " +
                    "FROM noticia n " +
                    "     INNER JOIN categoria c ON c.id = n.categoria_id ";

            public static final String NOTICIAS_PARA_NOTIFICACION = "" +
                    "SELECT id_noticia " +
                    "FROM noticia_nueva ";

            public static final String FUENTES = "" +
                    "SELECT fpa.id, " +
                    "       fpa.fuente_id, " +
                    "       fpa.tipo_fuente_id, " +
                    "       fn.nombre, " +
                    "       fn.url, " +
                    "       fn.id_externo, " +
                    "       fn.slug, " +
                    "       fn.nombre_corto, " +
                    "       fn.favicon, " +
                    "       tf.tipo " +
                    "FROM fuente_por_articulo fpa " +
                    "   INNER JOIN fuente fn ON fn.id = fpa.fuente_id " +
                    "   INNER JOIN tipo_fuente tf ON tf.id = fpa.tipo_fuente_id " +
                    "ORDER BY fpa.id ASC";
            
            public static final String FUENTE_POR_SLUG = "" +
                    "SELECT fpa.id, " +
                    "       fpa.fuente_id, " +
                    "       fpa.tipo_fuente_id, " +
                    "       fn.nombre, " +
                    "       fn.url,  " +
                    "       fn.id_externo, " +
                    "       fn.slug, " +
                    "       fn.nombre_corto, " +
                    "       fn.favicon, " +
                    "       tf.tipo " +
                    "FROM fuente_por_articulo fpa " +
                    "INNER JOIN fuente fn ON fn.id = fpa.fuente_id " +
                    "INNER JOIN tipo_fuente tf ON tf.id = fpa.tipo_fuente_id " +
                    "WHERE fn.slug = ? " +
                    "ORDER BY fpa.id ASC";
            
            public static final String FUENTES_POR_NOTICIA = "" +
                    "SELECT fpa.id, " +
                    "       fpa.ciudad_id," +
                    "       fpa.fuente_id, " +
                    "       fpa.tipo_fuente_id, " +
                    "       fn.nombre, " +
                    "       fn.url, " +
                    "       fn.id_externo, " +
                    "       fn.slug, " +
                    "       fn.nombre_corto, " +
                    "       fn.favicon, " +
                    "       tf.tipo " +
                    "FROM fuente_por_articulo fpa " +
                    "   INNER JOIN fuente fn ON fn.id = fpa.fuente_id " +
                    "   INNER JOIN tipo_fuente tf ON tf.id = fpa.tipo_fuente_id " +
                    "WHERE fpa.id IN (SELECT ar.fuenteporarticulo_id " +
                    "                FROM articulo ar " +
                    "                WHERE ar.noticia_id = ? " +
                    "                GROUP BY ar.fuenteporarticulo_id) " +
                    "ORDER BY fpa.id ASC";

            public static String CATEGORIAS = "" +
                    "SELECT id, " +
                    "       nombre, " +
                    "       categoria_padre, " +
                    "       id_externo," +
                    "       slug " +
                    "FROM categoria " +
                    "ORDER BY id ASC";

            public static final String CATEGORIAS_PADRE = "" +
                    "SELECT id, " +
                    "       nombre, " +
                    "       categoria_padre, " +
                    "       id_externo, " +
                    "       slug " +
                    "FROM categoria " +
                    "WHERE categoria_padre ISNULL " +
                    "ORDER BY id ASC";


            public static final String SUB_CATEGORIAS = "" +
                    "SELECT id, " +
                    "       nombre, " +
                    "       categoria_padre, " +
                    "       id_externo, " +
                    "       slug " +
                    "FROM categoria " +
                    "WHERE categoria_padre > 0 " +
                    "ORDER BY id ASC";

            public static final String CATEGORIA_POR_SLUG = "" +
                    "SELECT id, " +
                    "       nombre, " +
                    "       categoria_padre, " +
                    "       id_externo, " +
                    "       slug " +
                    "FROM categoria " +
                    "WHERE slug = ? ";

            public static final String ESTADO_DE_PROCESO = "" +
                    "SELECT estado " +
                    "FROM procesos " +
                    "WHERE id = ?";

            public static final String ARTICULOS_POR_ID_NOTICIA = "" +
                    "SELECT ar.id, " +
                    "       ar.titulo, " +
                    "       ar.contenido, " +
                    "       ar.contenido_html, " +
                    "       ar.autor, " +
                    "       ar.autor_foto, " +
                    "       ar.fecha_publicacion, " +
                    "       ar.fecha_entrada, " +
                    "       ar.removido, " +
                    "       ar.snippet, " +
                    "       ar.tags, " +
                    "       ar.url, " +
                    "       ar.fuenteporarticulo_id AS fpa_id, " +
                    "       fpa.fuente_id AS fpa_fuente_id, " +
                    "       fpa.tipo_fuente_id AS fpa_tipo_fuente_id, " +
                    "       fn.nombre AS fn_nombre, " +
                    "       fn.url AS fn_url, " +
                    "       fn.id_externo AS fn_id_externo, " +
                    "       fn.slug, " +
                    "       fn.nombre_corto," +
                    "       tf.tipo AS tf_tipo " +
                    "FROM articulo ar " +
                    "       INNER JOIN fuente_por_articulo fpa ON fpa.id = ar.fuenteporarticulo_id " +
                    "       INNER JOIN fuente fn ON fn.id = fpa.fuente_id " +
                    "       INNER JOIN tipo_fuente tf ON tf.id = fpa.tipo_fuente_id " +
                    "WHERE ar.noticia_id = ? " +
                    "ORDER BY ar.fecha_publicacion ASC";

            public static final String ARTICULOS_NO_PROCESADOS = "" +
                    "SELECT anp.id, " +
                    "  anp.articulo_id AS articulo_id, " +
                    "  anp.fecha_sync, " +
                    "  anp.tags_sync, " +
                    "  anp.numero_fuentes_sync, " +
                    "  ar.titulo, " +
                    "  ar.contenido, " +
                    "  ar.contenido_html, " +
                    "  ar.autor, " +
                    "  ar.autor_foto, " +
                    "  ar.fecha_publicacion, " +
                    "  ar.fecha_entrada, " +
                    "  ar.removido, " +
                    "  ar.snippet, " +
                    "  ar.tags, " +
                    "  ar.url, " +
                    "  ar.fuenteporarticulo_id AS fpa_id, " +
                    "  ar.noticia_id AS ar_noticia_id " +
                    "FROM articulos_no_procesados anp " +
                    "  INNER JOIN articulo ar ON anp.articulo_id = ar.id " +
                    "WHERE anp.*field* = FALSE " +
                    "ORDER BY ar.noticia_id ASC";

            public static final String ARTICULOS_NO_PROCESADOS_ALL = "" +
                    "SELECT anp.id, " +
                    "  anp.articulo_id AS articulo_id, " +
                    "  anp.fecha_sync, " +
                    "  anp.tags_sync, " +
                    "  anp.numero_fuentes_sync, " +
                    "  ar.titulo, " +
                    "  ar.contenido, " +
                    "  ar.contenido_html, " +
                    "  ar.autor, " +
                    "  ar.autor_foto, " +
                    "  ar.fecha_publicacion, " +
                    "  ar.fecha_entrada, " +
                    "  ar.removido, " +
                    "  ar.snippet, " +
                    "  ar.tags, " +
                    "  ar.url, " +
                    "  ar.fuenteporarticulo_id AS fpa_id, " +
                    "  ar.noticia_id AS ar_noticia_id " +
                    "FROM articulos_no_procesados anp " +
                    "  INNER JOIN articulo ar ON anp.articulo_id = ar.id " +
                    "ORDER BY ar.noticia_id ASC";

            public static final String FECHAS_NOTICIA = "" +
                    "SELECT MIN(fecha_entrada) AS publicacion, " +
                    "       MAX(fecha_entrada) AS ultima_fecha " +
                    "FROM articulo " +
                    "WHERE noticia_id = ?";

        }

        public static final class INSERTS {

            public static final String NUEVA_NOTICIA = "" +
                    "INSERT INTO noticia " +
                    "        (vistas, " +
                    "        compartidos, " +
                    "        categoria_id, " +
                    "        posicion, " +
                    "        titulo, " +
                    "        id_externo, " +
                    "        slug, " +
                    "        deadline, " +
                    "        tags) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            public static final String NUEVA_NOTICIA_NOTIFICACION = "" +
                    "INSERT INTO noticia_nueva " +
                    "       (id_noticia) " +
                    "VALUES (?)";

            public static final String NUEVO_ARTICULO = "" +
                    "INSERT INTO articulo " +
                    "       (titulo, " +
                    "        contenido, " +
                    "        contenido_html, " +
                    "        fuenteporarticulo_id, " +
                    "        noticia_id, " +
                    "        autor, " +
                    "        autor_foto, " +
                    "        fecha_publicacion," +
                    "        fecha_entrada, " +
                    "        removido, " +
                    "        snippet, " +
                    "        tags, " +
                    "        url) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            public static final String NUEVO_ARTICULO_NO_PROCESADO = "" +
                    "INSERT INTO articulos_no_procesados " +
                    "       (articulo_id) " +
                    "VALUES (?)";

            public static final String NUEVA_CATEGORIA = "" +
                    "INSERT INTO categoria " +
                    "       (nombre, " +
                    "        id_externo, " +
                    "        slug) " +
                    "VALUES (?, ?, ?)";

            public static final String NUEVA_SUB_CATEGORIA = "" +
                    "INSERT INTO categoria " +
                    "       (nombre, " +
                    "        categoria_padre, " +
                    "        id_externo, " +
                    "        slug) " +
                    "VALUES (?, ?, ?, ?)";

            public static final String NUEVA_FUENTE = "" +
                    "INSERT INTO fuente " +
                    "       (nombre, " +
                    "        url, " +
                    "        id_externo, " +
                    "        slug, " +
                    "        nombre_corto, " +
                    "        favicon) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

            public static final String NUEVA_FUENTE_POR_ARTICULO = "" +
                    "INSERT INTO fuente_por_articulo " +
                    "       (fuente_id, " +
                    "        tipo_fuente_id) " +
                    "VALUES (?, ?)";

            public static final String NUEVO_ARCHIVO_MULTIMEDIA = "" +
                    "INSERT INTO multimedia " +
                    "       (articulo_id, " +
                    "        url, " +
                    "        url_original, " +
                    "        tipo_archivo_id, " +
                    "        anchura, " +
                    "        altura) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
        }

        public static final class UPDATES {

            public static final String ACTUALIZAR_CATEGORIA = "" +
                    "UPDATE categoria " +
                    "SET nombre = ?, " +
                    "    id_externo = ? " +
                    "WHERE id = ?";

            public static final String ACTUALIZAR_FUENTE = "" +
                    "UPDATE fuente " +
                    "SET nombre = ?, " +
                    "    url = ?," +
                    "    id_externo = ?, " +
                    "    nombre_corto = ?, " +
                    "    favicon = ? " +
                    "WHERE slug = ?";

            public static final String ACTUALIZAR_TAGS_DE_NOTICIA = "" +
                    "UPDATE noticia SET tags = ? " +
                    "WHERE id = ?";

            public static final String ACTUALIZAR_FECHAS_NOTICIA = "" +
                    "UPDATE noticia " +
                    "SET fecha_publicacion = ?, " +
                    "ultima_actualizacion = ? " +
                    "WHERE id = ?";

            public static final String ACTUALIZAR_NUMERO_FUENTES_NOTICIA = "" +
                    "UPDATE noticia " +
                    "       SET numero_fuentes = ? " +
                    "WHERE id = ?";

            public static final String CAMBIAR_ESTADO_PROCESO = "" +
                    "UPDATE procesos " +
                    "SET estado = ? " +
                    "WHERE id = ?";

            public static final String CAMBIAR_ESTADO_ARTICULO_NO_PROCESADO = "" +
                    "UPDATE articulos_no_procesados " +
                    "       SET *field* = ? " +
                    "WHERE id = ?";

        }

        public static final class DELETE {

            public static final String ELIMINAR_ARTICULO_NO_PROCESADO = "" +
                    "DELETE FROM articulos_no_procesados " +
                    "WHERE id = ?";

            public static final String ELIMINAR_NOTICIAS_PARA_NOTIFICACION = "" +
                    "DELETE FROM noticia_nueva";
        }

    }

    public static final class A3 {

        public static final class SELECTS {

            public static final String ARTICULOS_NO_SINCRONIZADOS = "" +
                    "SELECT c.id AS c_id, " +
                    "       c.size AS c_size, " +
                    "       c.slug AS c_slug, " +
                    "       c.title AS c_title, " +
                    "       c.main_cat_id AS c_main_cat_id, " +
                    "       c.sub_cat_id AS c_sub_cat_id, " +
                    "       c.synchronized AS c_synchronized, " +
                    "       c.sound AS c_sound, " +
                    "       cn.id AS cn_id, " +
                    "       cn.title AS cn_title, " +
                    "       cn.link AS cn_link, " +
                    "       cn.pub_date AS cn_pub_date, " +
                    "       cn.snippet AS cn_snippet, " +
                    "       cn.date_entered AS cn_date_entered, " +
                    "       cn.author AS cn_author, " +
                    "       cn.category_id AS cn_category_id, " +
                    "       cn.sub_cat_id AS cn_sub_cat_id, " +
                    "       cn.source_id AS cn_source_id, " +
                    "       cn.clustered AS cn_clustered, " +
                    "       cn.synchronized AS cn_synchronized, " +
                    "       cn.tags AS cn_tags, " +
                    "       nc.id AS nc_id, " +
                    "       nc.content AS nc_content, " +
                    "       nc.summary AS nc_summary, " +
                    "       nc.keywords AS nc_keywords, " +
                    "       nc.raw_text AS nc_raw_text " +
                    "FROM fl_cluster_details cd " +
                    "     INNER JOIN fl_clusters c ON c.id = cd.cluster_id " +
                    "     INNER JOIN fl_curated_news cn ON cn.id = cd.news_id " +
                    "     INNER JOIN fl_news_content nc ON nc.id = cn.id " +
                    "WHERE cn.clustered = TRUE " +
                    "AND cn.synchronized = FALSE " +
                    "ORDER BY cn.id ASC";

            public static final String NOTICIAS_NO_SINCRONIZADAS = "" +
                    "SELECT id, " +
                    "       size, " +
                    "       slug, " +
                    "       title, " +
                    "       main_cat_id, " +
                    "       sub_cat_id, " +
                    "       synchronized, " +
                    "       sound " +
                    "FROM fl_clusters " +
                    "WHERE synchronized = FALSE " +
                    "ORDER BY id ASC";

            public static final String VIDEOS_POR_ARTICULO = "" +
                    "SELECT id, " +
                    "       content, " +
                    "       original_content " +
                    "FROM fl_news_video " +
                    "WHERE id = ?";

            public static final String IMAGENES_POR_ARTICULO = "" +
                    "SELECT id, " +
                    "       content, " +
                    "       original_content, " +
                    "       width, " +
                    "       height " +
                    "FROM fl_news_img " +
                    "WHERE id = ?";
        }

        public static final class UPDATES {

            public static final String CAMBIAR_ESTADO_NOTICIA = "" +
                    "UPDATE fl_clusters " +
                    "SET synchronized = ? " +
                    "WHERE id = ?";

            public static final String CAMBIAR_ESTADO_ARTICULO = "" +
                    "UPDATE fl_curated_news " +
                    "SET synchronized = ? " +
                    "WHERE id = ?";
        }

    }

    public static final class TTRRSS {

        public static final class SELECTS {

            public static final String CATEGORIAS_PADRE = "" +
                    "SELECT id," +
                    "       owner_uid, " +
                    "       collapsed, " +
                    "       order_id, " +
                    "       view_settings, " +
                    "       parent_cat, " +
                    "       title " +
                    "FROM ttrss_feed_categories " +
                    "WHERE parent_cat = (SELECT id " +
                    "                    FROM ttrss_feed_categories " +
                    "                    WHERE title = ?) " +
                    "ORDER BY id ASC";

            public static final String SUB_CATEGORIAS = "" +
                    "SELECT id, " +
                    "       owner_uid, " +
                    "       collapsed, " +
                    "       order_id, " +
                    "       view_settings, " +
                    "       parent_cat, " +
                    "       title " +
                    "FROM ttrss_feed_categories " +
                    "WHERE parent_cat NOTNULL " +
                    "AND ttrss_feed_categories.parent_cat NOT IN (SELECT id " +
                    "                                         FROM  ttrss_feed_categories " +
                    "                                         WHERE parent_cat ISNULL) " +
                    "ORDER BY id ASC";

            public static final String CATEGORIAS = "" +
                    "SELECT id," +
                    "       owner_uid, " +
                    "       collapsed, " +
                    "       order_id, " +
                    "       view_settings, " +
                    "       parent_cat, " +
                    "       title " +
                    "FROM ttrss_feed_categories " +
                    "ORDER BY id ASC";
            
            public static final String PAISES = "" +
                    "SELECT id, " +
                    "       owner_uid, " +
                    "       collapsed, " +
                    "       order_id, " +
                    "       view_settings, " +
                    "       parent_cat, " +
                    "       title " +
                    "FROM ttrss_feed_categories " +
                    "WHERE parent_cat ISNULL " +
                    "ORDER BY id ASC";

            public static final String CATEGORIA_POR_ID = "" +
                    "SELECT id," +
                    "       owner_uid, " +
                    "       collapsed, " +
                    "       order_id, " +
                    "       view_settings, " +
                    "       parent_cat, " +
                    "       title " +
                    "FROM ttrss_feed_categories " +
                    "WHERE id = ? ";

            public static final String CATEGORIA_POR_TITULO = "" +
                    "SELECT id," +
                    "       owner_uid, " +
                    "       collapsed, " +
                    "       order_id, " +
                    "       view_settings, " +
                    "       parent_cat, " +
                    "       title " +
                    "FROM ttrss_feed_categories " +
                    "WHERE title = ? ";


            public static final String FUENTES = "" +
                    "SELECT id, " +
                    "       title, " +
                    "       feed_url, " +
                    "       icon_url, " +
                    "       site_url " +
                    "FROM ttrss_feeds " +
                    "ORDER BY id ASC";

            public static final String FUENTE_POR_ID = "" +
                    "SELECT id, " +
                    "       title, " +
                    "       feed_url, " +
                    "       icon_url, " +
                    "       site_url " +
                    "FROM ttrss_feeds " +
                    "WHERE id = ? ";

        }

    }
}
