package lk.ijse.gdse.db;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DBConnection {
    private static final BasicDataSource ds = new BasicDataSource();

    static {
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/complaint_management_system");
        ds.setUsername("root");
        ds.setPassword("Ijse@1234");
        ds.setInitialSize(5);
        ds.setMaxIdle(5);
        ds.setMaxOpenPreparedStatements(100);
    }
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
