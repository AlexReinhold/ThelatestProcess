package datasource;

import org.apache.commons.dbcp2.BasicDataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by nlperez on 1/11/17.
 */
public class A3DataSource {

    private BasicDataSource dataSource;

    public A3DataSource() throws IOException, SQLException, PropertyVetoException {
        dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUsername("postgres");
//        dataSource.setPassword("postgres"); // dev
        dataSource.setPassword("postKode0101$$"); // prod
        dataSource.setUrl("jdbc:postgresql://localhost:5432/a3"); //dev
//        dataSource.setUrl("jdbc:postgresql://ec2-54-162-106-189.compute-1.amazonaws.com:5432/a3"); // prod
    }

    public BasicDataSource getDatasource() {
        return dataSource;
    }
}
