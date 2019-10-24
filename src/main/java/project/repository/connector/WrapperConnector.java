package project.repository.connector;

import org.apache.log4j.Logger;
import project.exception.InvalidDatabaseConnectionException;


import java.sql.*;
import java.util.ResourceBundle;

public final class WrapperConnector {
    private static final Logger LOGGER = Logger.getLogger("file");

    private final Connection connection;

    public WrapperConnector() {
        try {
            ResourceBundle resource = ResourceBundle.getBundle("database");
            String url = resource.getString("db.url");
            String user = resource.getString("db.user");
            String pass = resource.getString("db.password");
            this.connection = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            LOGGER.error("Could not get connection from database", e);
            throw new InvalidDatabaseConnectionException("Could not get connection from database");
        }
    }

    public Statement getStatement() throws SQLException {
        if ( connection != null ) {
            Statement statement = connection.createStatement();

            if ( statement != null ) {
                return statement;
            }
        }

        LOGGER.warn("Connection or statement is null");
        throw new SQLException("Connection or statement is null");
    }

    public PreparedStatement getPreparedStatement(String query) throws SQLException {
        if ( connection != null ) {
            PreparedStatement statement = connection.prepareStatement(query);

            if ( statement != null ) {
                return statement;
            }
        }

        LOGGER.warn("Connection or prepared statement is null");
        throw new SQLException("Connection or prepared statement is null");
    }

}
