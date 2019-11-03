package project.repository.connector;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import project.exception.InvalidDatabaseConnectionException;


import java.sql.*;
import java.util.ResourceBundle;

public final class WrapperConnector {
    private static final Logger LOGGER = Logger.getLogger(WrapperConnector.class);

    private static final BasicDataSource ds = new BasicDataSource();

    public WrapperConnector(String fileConfigName) {
        ResourceBundle resource = ResourceBundle.getBundle(fileConfigName);

        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl(resource.getString("db.url"));
        ds.setUsername(resource.getString("db.user"));
        ds.setPassword(resource.getString("db.password"));
        ds.setMinIdle(5);
        ds.setMaxIdle(10);
        ds.setMaxOpenPreparedStatements(100);
    }

    public Connection getConnection() {
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            LOGGER.error("Could not get connection from database" + e.getMessage());
            throw new InvalidDatabaseConnectionException("Could not get connection from database " + e.getMessage());
        }
    }
}
