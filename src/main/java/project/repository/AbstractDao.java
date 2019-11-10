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
            createStatementMapper(entity, preparedStatement);
            int insert =  preparedStatement.executeUpdate();

            return insert != 0;
        } catch (SQLException e) {
            LOGGER.error("Invalid entity adding" , e);
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
            LOGGER.error("Invalid entity search" , e);
            throw new DatabaseRuntimeException("Invalid entity search", e);
        }
    }

    protected List<E> findByStringParam(String data, String query, Integer offset, Integer amount) {
        List<E> result = new ArrayList<>();

        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, data);
            statement.setInt(2, offset);
            statement.setInt(3, amount);
            ResultSet entities = statement.executeQuery();

            while(entities.next()) {
                mapResultSetToEntity(entities).ifPresent(result::add);
            }

            return result;
        } catch (SQLException e) {
            LOGGER.error("Invalid entity search by string parameter" , e);
            throw new DatabaseRuntimeException("Invalid entity search by string parameter", e);
        }
    }

    protected List<E> findEntitiesByForeignKey(Integer id, String query, Integer offset, Integer amount) {
        List<E> result = new ArrayList<>();

        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, offset);
            preparedStatement.setInt(3, amount);

            ResultSet stories = preparedStatement.executeQuery();

            while (stories.next()) {
                mapResultSetToEntity(stories).ifPresent(result::add);
            }

            return result;
        } catch (SQLException e) {
            LOGGER.error("Invalid entities search by foreign key" , e);
            throw new DatabaseRuntimeException("Invalid entities search by foreign key", e);
        }
    }

    protected Integer findNumberOfRows(String query) {
        try (Connection connection = connector.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet count = statement.executeQuery(query);

            return count.next() ? count.getInt(1) : 0;
        } catch (SQLException e) {
            LOGGER.error("Invalid entities count" , e);
            throw new DatabaseRuntimeException("Invalid entities count", e);
        }
    }

    protected Integer findNumberOfRowsById(String query, Integer id) {
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);

            ResultSet count = statement.executeQuery();

            return count.next() ? count.getInt(1) : 0;
        } catch (SQLException e) {
            LOGGER.error("Invalid entities count by id" , e);
            throw new DatabaseRuntimeException("Invalid entities count by id", e);
        }
    }

    protected List<E> findAll(String query, Integer offset, Integer amount) {
        List<E> result = new ArrayList<>();

        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, offset);
            statement.setInt(2, amount);

            ResultSet entities = statement.executeQuery();

            while(entities.next()) {
                mapResultSetToEntity(entities).ifPresent(result::add);
            }

            return result;
        } catch (SQLException e) {
            LOGGER.error("Invalid entities search" , e);
            throw new DatabaseRuntimeException("Invalid entities search", e);
        }
    }

    protected void update(E entity, String query) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            updateStatementMapper(entity, preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Invalid entity updating" , e);
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
            LOGGER.error("Invalid entity deleting" , e);
            throw new DatabaseRuntimeException("Invalid entity deleting", e);
        }
    }

    protected abstract void updateStatementMapper(E entity, PreparedStatement preparedStatement) throws SQLException;
    protected abstract void createStatementMapper(E entity, PreparedStatement preparedStatement) throws SQLException;
    protected abstract Optional<E> mapResultSetToEntity(ResultSet entity) throws SQLException;
}
