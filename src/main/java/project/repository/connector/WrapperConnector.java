package project.repository.connector;

import org.apache.log4j.Logger;
import project.exception.InvalidDatabaseConnectionException;


import java.sql.*;
import java.util.ResourceBundle;

public final class WrapperConnector {
    private static final Logger LOGGER = Logger.getLogger(WrapperConnector.class);

    private final String url;
    private final String user;
    private final String pass;

    public WrapperConnector(String fileConfigName) {
        ResourceBundle resource = ResourceBundle.getBundle(fileConfigName);
        this.url = resource.getString("db.url");
        this.user = resource.getString("db.user");
        this.pass = resource.getString("db.password");
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            LOGGER.error("Could not get connection from database", e);
            throw new InvalidDatabaseConnectionException("Could not get connection from database");
        }
    }
}
