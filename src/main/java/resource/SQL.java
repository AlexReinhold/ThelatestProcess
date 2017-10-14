package resource;

public class SQL {

    public static final class TL {

        public static class SELECTS {

            public static final String STORY_BY_SLUG = "" +
                    "SELECT s.id, " +
                    "       s.category_id, " +
                    "       s.name, " +
                    "       s.external_id, " +
                    "       s.slug, " +
                    "       s.views, " +
                    "       s.deadline, " +
                    "       s.position, " +
                    "       s.share, " +
                    "       s.tags, " +
                    "       c.id AS c_id, " +
                    "       c.parent_id, " +
                    "       c.name AS c_name, " +
                    "       c.active AS c_active, "+
                    "       c.menu_order, "+
                    "       c.external_id AS c_external_id, " +
                    "       c.slug AS c_slug " +
                    "FROM tl_story s " +
                    "     INNER JOIN tl_category c ON c.id = s.category_id " +
                    "WHERE s.slug = ? ";

            public static final String PROCESS_STATE = "" +
                    "SELECT state " +
                    "FROM tl_process " +
                    "WHERE id = ?";

            public static final String CATEGORY_BY_EXTERNAL_ID = "" +
                    "SELECT id, " +
                    "       parent_id, " +
                    "       name, " +
                    "       active, " +
                    "       menu_order, " +
                    "       external_id, " +
                    "       slug " +
                    "FROM tl_category " +
                    "WHERE external_id = ? ";

            public static final String PARENT_CATEGORIES = "" +
                    "SELECT id, " +
                    "       parent_id, " +
                    "       name, " +
                    "       active, " +
                    "       menu_order, " +
                    "       external_id, " +
                    "       slug " +
                    "FROM tl_category " +
                    "WHERE parent_id is null ";

            public static final String SOURCE_BY_EXTERNAL_ID = "" +
                    "SELECT id, " +
                    "       name, " +
                    "       section, " +
                    "       external_id, " +
                    "       alias, " +
                    "       source_feed_url, " +
                    "       sourceurl, " +
                    "       icon_url " +
                    "FROM tl_source " +
                    "WHERE external_id = ? ";

            public static final String STORY_BY_ID_FOR_ES = "" +
                    "SELECT s.id, " +
                    "       s.name, " +
                    "       s.slug, " +
                    "       s.position, " +
                    "       s.deadline, " +
                    "       s.views, " +
                    "       c.id as category_id, " +
                    "       c.slug as category_slug, " +
                    "       c.name as category_name, " +
                    "       pc.slug as category_parent, " +
                    "       news.id as news_id, " +
                    "       news.title as news_title, " +
                    "       news.imgurl as news_imgurl, " +
                    "       news.publication_date as news_pubdate, " +
                    "       news.snippet as news_snippet, " +
                    "       source.id as source_id, " +
                    "       source.name as source_name, " +
                    "       (select count(*) from tl_news where story_id = s.id) as newsCount, " +
                    "       (select count(*) from tl_story_wtm where story_id = s.id) as wtms, " +
                    "       (select count(distinct(src.id)) from tl_source src inner join tl_news n on n.source_id = src.id where n.story_id=s.id ) as sourcesCount " +
                    "FROM tl_story s " +
                    "INNER JOIN tl_category c on c.id = s.category_id " +
                    "LEFT JOIN tl_category pc on pc.id = c.parent_id " +
                    "INNER JOIN tl_news news on news.id = ( SELECT id FROM tl_news WHERE story_id = s.id order by publication_date limit 1 ) " +
                    "INNER JOIN tl_source source on source.id = news.source_id " +
                    "WHERE s.id in (:ids)";

            public static final String STORY_BY_NEWS_ID_FOR_ES = "" +
                    "SELECT s.id, " +
                    "       s.name, " +
                    "       s.slug, " +
                    "       s.position, " +
                    "       s.deadline, " +
                    "       s.views, " +
                    "       c.id as category_id, " +
                    "       c.slug as category_slug, " +
                    "       c.name as category_name, " +
                    "       pc.slug as category_parent, " +
                    "       news.id as news_id, " +
                    "       news.title as news_title, " +
                    "       news.imgurl as news_imgurl, " +
                    "       news.publication_date as news_pubdate, " +
                    "       news.snippet as news_snippet, " +
                    "       source.id as source_id, " +
                    "       source.name as source_name, " +
                    "       (select count(*) from tl_news where story_id = s.id) as newsCount, " +
                    "       (select count(*) from tl_story_wtm where story_id = s.id) as wtms, " +
                    "       (select count(distinct(src.id)) from tl_source src inner join tl_news n on n.source_id = src.id where n.story_id=s.id ) as sourcesCount " +
                    "FROM tl_story s " +
                    "INNER JOIN tl_category c on c.id = s.category_id " +
                    "LEFT JOIN tl_category pc on pc.id = c.parent_id " +
                    "INNER JOIN tl_news news on news.id = ( SELECT nn.id FROM tl_news nn WHERE nn.story_id = s.id order by nn.publication_date desc limit 1 ) " +
                    "INNER JOIN tl_source source on source.id = news.source_id " +
                    "WHERE news.id in (:ids)";

            public static final String NEWS_BY_ID_FOR_ES = "" +
                    "SELECT n.id, " +
                    "       n.title, " +
                    "       n.publication_date, " +
                    "       n.snippet, " +
                    "       n.imgurl, " +
                    "       n.url, " +
                    "       src.id as source_id, " +
                    "       src.name as source_name, " +
                    "       s.id as story_id, " +
                    "       s.name as story_name, " +
                    "       s.slug as story_slug, " +
                    "       c.id as category_id, " +
                    "       c.slug as category_slug, " +
                    "       c.name as category_name, " +
                    "       pc.slug as category_parent " +
                    "FROM tl_news n " +
                    "INNER JOIN tl_source src on src.id=n.source_id " +
                    "INNER JOIN tl_story s on s.id=n.story_id " +
                    "INNER JOIN tl_category c on c.id=s.category_id " +
                    "LEFT JOIN tl_category pc on pc.id=c.parent_id " +
                    "WHERE n.id in (:ids)";

            public static final String GET_UNSYNCCHRONIZED_NEWS = ""+
                    "SELECT * FROM unsynchronized_news ";

            public static final String GET_UNSYNCCHRONIZED_STORIES = ""+
                    "SELECT * FROM unsynchronized_stories ";

        }

        public static final class INSERTS {

            public static final String NEW_STORY = "" +
                    "INSERT INTO tl_story " +
                    "        (id, " +
                    "        category_id, " +
                    "        name, " +
                    "        external_id, " +
                    "        slug, " +
                    "        views, " +
                    "        deadline, " +
                    "        position, " +
                    "        share, " +
                    "        tags) " +
                    "VALUES (nextval('tl_story_id_seq'), ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            public static final String INSERT_NEWS = "" +
                    "INSERT INTO tl_news " +
                    "       (id," +
                    "        story_id, " +
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
                    "VALUES (nextval('tl_news_id_seq'),?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            public static final String INSERT_UNSYNCCHRONIZED_NEWS = ""+
                    "INSERT INTO unsynchronized_news "+
                    "       (news_id, "+
                    "        date) "+
                    "VALUES (?, now() )";

            public static final String INSERT_UNSYNCCHRONIZED_STORIES = ""+
                    "INSERT INTO unsynchronized_stories "+
                    "       (story_id, "+
                    "        date) "+
                    "VALUES (?, now() )";

        }

        public static final class UPDATES {

            public static final String CHANGE_PROCESS_STATE = "" +
                    "UPDATE tl_process " +
                    "SET state = ? " +
                    "WHERE id = ?";

            public static final String CHANGE_STORY_ES_STATE = "" +
                    "UPDATE fl_clusters " +
                    "SET synchronized = ? " +
                    "WHERE id = ?";

            public static final String CHANGE_NEWS_ES_STATE = "" +
                    "UPDATE fl_curated_news " +
                    "SET synchronized = ? " +
                    "WHERE id = ?";

        }

        public static final class DELETE {

            public static final String DELETE_UNSYNCCHRONIZED_NEWS_BY_ID = ""+
                    "DELETE FROM unsynchronized_news "+
                    "WHERE news_id = ? ";

            public static final String DELETE_UNSYNCCHRONIZED_STORIES_BY_ID = ""+
                    "DELETE FROM unsynchronized_stories "+
                    "WHERE story_id = ? ";

            public static final String DELETE_ALL_UNSYNCCHRONIZED_NEWS = ""+
                    "DELETE FROM unsynchronized_news ";

            public static final String DELETE_ALL_UNSYNCCHRONIZED_STORIES = ""+
                    "DELETE FROM unsynchronized_stories ";

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
//                    "       cn.tags AS cn_tags, " +
                    "       nc.id AS nc_id, " +
                    "       nc.content AS nc_content, " +
                    "       nc.summary AS nc_summary, " +
                    "       nc.keywords AS nc_keywords, " +
                    "       nc.raw_text AS nc_raw_text, " +
                    "       ni.content AS imgurl " +
                    "FROM fl_cluster_details cd " +
                    "     INNER JOIN fl_clusters c ON c.id = cd.cluster_id " +
                    "     INNER JOIN fl_curated_news cn ON cn.id = cd.news_id " +
                    "     INNER JOIN fl_news_content nc ON nc.id = cn.id " +
                    "     INNER JOIN fl_news_img ni ON ni.id = cn.id " +
                    "WHERE cn.clustered = TRUE " +
                    "AND cn.synchronized = FALSE AND cn.category_id = ? " +
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
                    "WHERE synchronized = FALSE AND main_cat_id = ?" +
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
