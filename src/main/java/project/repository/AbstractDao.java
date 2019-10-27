package project.repository;

import org.apache.log4j.Logger;
import project.exception.DatabaseRuntimeException;
import project.repository.connector.WrapperConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDao<E> {
    private static final Logger LOGGER = Logger.getLogger(AbstractDao.class);
    
    protected WrapperConnector connector;

    public AbstractDao(WrapperConnector connector) {
        this.connector = connector;
    }

    protected boolean save(E entity, String query) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            int insert = statementMapper(entity, preparedStatement);

            return insert != 0;
        } catch (SQLException e) {
            LOGGER.error("Invalid entity adding");
            throw new DatabaseRuntimeException("Invalid entity adding", e);
        }
    }

    protected Optional<E> findById(Integer id, String query) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);

            ResultSet entity = preparedStatement.executeQuery();

            return entity.next() ? mapResultSetToEntity(entity) : Optional.empty();
        } catch (SQLException e) {
            LOGGER.error("Invalid entity search");
            throw new DatabaseRuntimeException("Invalid entity search", e);
        }
    }

    protected List<E> findByString(String data, String query) {
        List<E> result = new ArrayList<>();

        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, data);
            ResultSet entities = statement.executeQuery();

            while(entities.next()) {
                mapResultSetToEntity(entities).ifPresent(result::add);
            }

            return result;
        } catch (SQLException e) {
            LOGGER.error("Invalid entity search by string parameter");
            throw new DatabaseRuntimeException("Invalid entity search by string parameter", e);
        }
    }

    protected List<E> findAll(String query) {
        List<E> result = new ArrayList<>();

        try (Connection connection = connector.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet entities = statement.executeQuery(query);

            while(entities.next()) {
                mapResultSetToEntity(entities).ifPresent(result::add);
            }

            return result;
        } catch (SQLException e) {
            LOGGER.error("Invalid entities search");
            throw new DatabaseRuntimeException("Invalid entities search", e);
        }
    }

    protected void update(E entity, String query) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            statementMapper(entity, preparedStatement);
        } catch (SQLException e) {
            LOGGER.error("Invalid entity updating");
            throw new DatabaseRuntimeException("Invalid entity updating", e);
        }
    }

    protected boolean deleteById(Integer id, String query) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);

            int delete = preparedStatement.executeUpdate();
            return delete != 0;
        } catch (SQLException e) {
            LOGGER.error("Invalid entity deleting");
            throw new DatabaseRuntimeException("Invalid entity deleting", e);
        }
    }

    protected abstract int statementMapper(E entity, PreparedStatement preparedStatement) throws SQLException;

    protected abstract Optional<E> mapResultSetToEntity(ResultSet entity) throws SQLException;
}
