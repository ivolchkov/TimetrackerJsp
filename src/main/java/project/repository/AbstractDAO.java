package project.repository;

import project.repository.connector.WrapperConnector;

public abstract class AbstractDAO {
    protected WrapperConnector connector;

    public void close() {
        connector.closeConnection();
    }
}
