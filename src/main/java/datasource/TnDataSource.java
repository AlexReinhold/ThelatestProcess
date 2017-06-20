package datasource;


import org.apache.commons.dbcp2.BasicDataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;


/**
 * Created by nlperez on 1/11/17.
 */
public class TnDataSource {

    private BasicDataSource dataSource;

    public TnDataSource() throws IOException, SQLException, PropertyVetoException {
        dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUsername("postgres");
//        dataSource.setPassword("postgres"); // dev
        dataSource.setPassword("postKode0101$$"); // prod
        dataSource.setUrl("jdbc:postgresql://ec2-52-91-214-19.compute-1.amazonaws.com:5432/tn");
//        dataSource.setUrl("jdbc:postgresql://localhost:5432/tn");
    }

    public BasicDataSource getDatasource() {
        return dataSource;
    }

}
