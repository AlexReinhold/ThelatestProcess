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
        CuratedNew cn = new CuratedNew();
        cn.addId(rs.getInt("cn_id"));
        cn.addTitle(rs.getString("cn_title"));
        cn.addLink(rs.getString("cn_link"));
        cn.addPubDate(rs.getTimestamp("cn_pub_date"));
        cn.addSnippet(rs.getString("cn_snippet"));
        cn.addDateEntered(rs.getTimestamp("cn_date_entered"));
        cn.addAuthor(rs.getString("cn_author"));
        cn.addCategoryId(rs.getInt("cn_category_id"));
        cn.addSubCategoryId(rs.getInt("cn_sub_cat_id"));
            Source source = new Source();
            source.addId(rs.getInt("cn_source_id"));
        cn.addSource(source);
        cn.addClustered(rs.getBoolean("cn_clustered"));
        cn.addSynchronizeD(rs.getBoolean("cn_synchronized"));
            NewsContent nc = new NewsContent();
            nc.addId(rs.getInt("nc_id"));
            nc.addContent(rs.getString("nc_content"));
            nc.addSummary(rs.getString("nc_summary"));
            nc.addKeywords(rs.getString("nc_keywords"));
            nc.addRawText(rs.getString("nc_raw_text"));
        cn.addNewsContent(nc);
//        cn.addTags(rs.getString("cn_tags"));
            Cluster c = new Cluster();
            c.addId(rs.getInt("c_id"));
            c.addSize(rs.getInt("c_size"));
            c.addSlug(rs.getString("c_slug"));
            c.addTittle(rs.getString("c_title"));
            c.addMainCatId(rs.getInt("c_main_Cat_id"));
            c.addSubCatId(rs.getInt("c_sub_cat_id"));
            c.addSynchronizeD(rs.getBoolean("c_synchronized"));
            c.addSound(rs.getBoolean("c_sound"));
        cn.addCluster(c);
        return cn;
    }

}
