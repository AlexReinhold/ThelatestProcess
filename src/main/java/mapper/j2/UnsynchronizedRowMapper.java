package mapper.j2;

import model.j2.Unsynchronized;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UnsynchronizedRowMapper<T> implements RowMapper<Unsynchronized> {

    public Unsynchronized mapRow(ResultSet rs, int rowNum)  throws SQLException {
        return new Unsynchronized()
        .addId(rs.getInt("id"))
        .addDate(rs.getTimestamp("date"));
    }

}
