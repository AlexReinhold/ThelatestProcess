package mapper.j2;

import model.j2.Cluster;
import model.j2.CuratedNew;
import model.j2.NewsContent;
import model.ttrss.Source;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CuratedNewRowMapper<T> implements RowMapper<CuratedNew> {

    public CuratedNew mapRow(ResultSet rs, int rowNum)  throws SQLException {
        return new CuratedNew()
        .addId(rs.getInt("cn_id"))
        .addTitle(rs.getString("cn_title"))
        .addLink(rs.getString("cn_link"))
        .addPubDate(rs.getTimestamp("cn_pub_date"))
        .addSnippet(rs.getString("cn_snippet"))
        .addDateEntered(rs.getTimestamp("cn_date_entered"))
        .addAuthor(rs.getString("cn_author"))
        .addCategoryId(rs.getInt("cn_category_id"))
        .addImage(rs.getString("imgurl"))
        .addSubCategoryId(rs.getInt("cn_sub_cat_id"))
        .addSource(new Source().addId(rs.getInt("cn_source_id")))
        .addClustered(rs.getBoolean("cn_clustered"))
        .addSynchronizeD(rs.getBoolean("cn_synchronized"))
        .addNewsContent(new NewsContent()
            .addId(rs.getInt("nc_id"))
            .addContent(rs.getString("nc_content"))
            .addSummary(rs.getString("nc_summary"))
            .addKeywords(rs.getString("nc_keywords"))
            .addRawText(rs.getString("nc_raw_text")))
//        .addTags(rs.getString("cn_tags"))
        .addCluster(new Cluster()
            .addId(rs.getInt("c_id"))
            .addSize(rs.getInt("c_size"))
            .addSlug(rs.getString("c_slug"))
            .addTittle(rs.getString("c_title"))
            .addMainCatId(rs.getInt("c_main_Cat_id"))
            .addSubCatId(rs.getInt("c_sub_cat_id"))
            .addSynchronizeD(rs.getBoolean("c_synchronized"))
            .addSound(rs.getBoolean("c_sound")));
    }

}
