package datasource;

import org.apache.commons.dbcp2.BasicDataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;

public class J2DataSource {

    private BasicDataSource dataSource;

    public J2DataSource() throws IOException, SQLException, PropertyVetoException {
        dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUsername("postgres"); // dev
        dataSource.setPassword("123456"); // dev
//        dataSource.setUsername("dev_thelatest"); // prod
//        dataSource.setPassword("!dev_thelatest!2014"); // prod
        dataSource.setUrl("jdbc:postgresql://localhost:5432/j2"); //dev
    }

    public BasicDataSource getDatasource() {
        return dataSource;
    }
}