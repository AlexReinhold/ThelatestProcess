package resource;

public class SQL {

    public static final class TL {

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

            public static final String PROCESS_STATE = "" +
                    "SELECT state " +
                    "FROM tl_process " +
                    "WHERE id = ?";

            public static final String CATEGORY_BY_EXTERNAL_ID = "" +
                    "SELECT id, " +
                    "       parent_id, " +
                    "       name, " +
                    "       active, " +
                    "       menu_order " +
                    "       external_id " +
                    "       slug " +
                    "FROM tl_category " +
                    "WHERE external_id = ? ";

            public static final String SOURCE_BY_EXTERNAL_ID = "" +
                    "SELECT id, " +
                    "       name, " +
                    "       section, " +
                    "       external_id, " +
                    "       alias " +
                    "       source_feed_url " +
                    "       sourceurl " +
                    "       icon_url " +
                    "FROM tl_source " +
                    "WHERE external_id = ? ";

            public static final String STORY_BY_ID_FOR_ES = "" +
                    "SELECT s.id, " +
                    "       s.name, " +
                    "       s.slug, " +
                    "       s.position, " +
                    "       s.deadline, " +
                    "       c.id as category_id, " +
                    "       c.slug as category_slug, " +
                    "       c.name as category_name, " +
                    "       pc.slug as category_parent, " +
                    "       (select count(*) from tl_news where story_id = s.id group by id) newsCount, " +
                    "       (select count(*) from tl_news where story_id = s.id group by id) newsCount, " +
                    "       (select count(*) from tl_news where story_id = s.id group by id) newsCount, " +
                    "FROM tl_story s" +
                    "INNER JOIN tl_category c on c.id = s.category_id " +
                    "LEFT JOIN tl_category pc on pc.id = c.parent_id " +
                    "WHERE id = ? ";



        }

        public static final class INSERTS {

            public static final String NEW_STORY = "" +
                    "INSERT INTO tl_story " +
                    "        (category_id, " +
                    "        name, " +
                    "        external_id, " +
                    "        slug, " +
                    "        views, " +
                    "        deadline, " +
                    "        position, " +
                    "        share, " +
                    "        tags) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            public static final String INSERT_NEWS = "" +
                    "INSERT INTO tl_news " +
                    "       (story_id, " +
                    "        source_id, " +
                    "        url, " +
                    "        externalid, " +
                    "        title, " +
                    "        snippet, " +
                    "        imgurl, " +
                    "        author," +
                    "        score, " +
                    "        publication_date, " +
                    "        aggregator_date, " +
                    "        staff_picks, " +
                    "        content, " +
                    "        tags) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        }

        public static final class UPDATES {

            public static final String CHANGE_PROCESS_STATE = "" +
                    "UPDATE procesos " +
                    "SET estado = ? " +
                    "WHERE id = ?";

        }

    }

    public static final class J2 {

        public static final class SELECTS {

            public static final String UNPROCESSED_NEWS = "" +
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

            public static final String UNPROCESSED_STORIES = "" +
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
        }

        public static final class UPDATES {

            public static final String CHANGE_STORY_STATE = "" +
                    "UPDATE fl_clusters " +
                    "SET synchronized = ? " +
                    "WHERE id = ?";

            public static final String CHANGE_NEWS_STATE = "" +
                    "UPDATE fl_curated_news " +
                    "SET synchronized = ? " +
                    "WHERE id = ?";
        }

    }

}
