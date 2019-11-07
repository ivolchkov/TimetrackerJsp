package project.repository.connector;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import project.exception.InvalidDatabaseConnectionException;


import java.sql.*;
import java.util.ResourceBundle;

public final class WrapperConnector {
    private static final Logger LOGGER = Logger.getLogger(WrapperConnector.class);

    private final BasicDataSource pool;

    public WrapperConnector(String fileConfigName, BasicDataSource pool) {
        ResourceBundle resource = ResourceBundle.getBundle(fileConfigName);
        this.pool = pool;

        pool.setDriverClassName(resource.getString("db.driver"));
        pool.setUrl(resource.getString("db.url"));
        pool.setUsername(resource.getString("db.user"));
        pool.setPassword(resource.getString("db.password"));
        pool.setMinIdle(Integer.parseInt(resource.getString("db.minIdle")));
        pool.setMaxIdle(Integer.parseInt(resource.getString("db.maxIdle")));
        pool.setMaxOpenPreparedStatements(Integer.parseInt(resource.getString("db.maxPreparedStatement")));
    }

    public Connection getConnection() {
        try {
            return pool.getConnection();
        } catch (SQLException e) {
            LOGGER.error("Could not get connection from database" , e);
            throw new InvalidDatabaseConnectionException("Could not get connection from database ", e);
        }
    }
}
