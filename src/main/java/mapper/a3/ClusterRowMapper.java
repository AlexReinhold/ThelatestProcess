package mapper.a3;

import models.a3.Cluster;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by nlperez on 1/25/17.
 */
public class ClusterRowMapper<T> implements RowMapper<Cluster> {

    public Cluster mapRow(ResultSet rs, int rowNum) throws SQLException {
        Cluster cluster = new Cluster();
        cluster.addId(rs.getInt("id"));
        cluster.addSize(rs.getInt("size"));
        cluster.addSlug(rs.getString("slug"));
        cluster.addTittle(rs.getString("title"));
        cluster.addMainCatId(rs.getInt("main_Cat_id"));
        cluster.addSubCatId(rs.getInt("sub_cat_id"));
        cluster.addSynchronizeD(rs.getBoolean("synchronized"));
        cluster.addSound(rs.getBoolean("sound"));
        return cluster;
    }
}
