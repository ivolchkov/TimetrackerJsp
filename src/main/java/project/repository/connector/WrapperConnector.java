package project.repository.connector;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import project.exception.InvalidDatabaseConnectionException;

import java.sql.Connection;
import java.sql.SQLException;
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
        pool.setMinIdle(parseInteger(resource, "db.minIdle"));
        pool.setMaxIdle(parseInteger(resource, "db.maxIdle"));
        pool.setMaxOpenPreparedStatements(parseInteger(resource, "db.maxPreparedStatement"));
    }

    public Connection getConnection() {
        try {
            return pool.getConnection();
        } catch (SQLException e) {
            LOGGER.error("Could not get connection from database" , e);
            throw new InvalidDatabaseConnectionException("Could not get connection from database ", e);
        }
    }

    private int parseInteger(ResourceBundle resource, String s) {
        return Integer.parseInt(resource.getString(s));
    }
}
