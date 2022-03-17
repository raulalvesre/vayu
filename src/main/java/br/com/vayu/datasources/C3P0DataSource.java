package br.com.vayu.datasources;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class C3P0DataSource {

    private static final ComboPooledDataSource comboPooledDataSource= new ComboPooledDataSource();

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/vayu";
    private static final String USER = "root";
    private static final String PASSWORD = "rar432";

    static {
        comboPooledDataSource.setJdbcUrl(DATABASE_URL);
        comboPooledDataSource.setUser(USER);
        comboPooledDataSource.setPassword(PASSWORD);
        comboPooledDataSource.setMaxPoolSize(10);
    }

    public static Connection getConnection() throws SQLException {
        try {
            return comboPooledDataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private C3P0DataSource() {}

}
