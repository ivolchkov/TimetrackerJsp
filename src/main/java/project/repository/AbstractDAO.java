package project.repository;

import org.apache.log4j.Logger;
import project.domain.Entity;
import project.exception.DatabaseRuntimeException;
import project.repository.connector.WrapperConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDAO<E extends Entity> implements CRUDRepository<Long, E> {
    private static final Logger LOGGER = Logger.getLogger(AbstractDAO.class);
    
    protected WrapperConnector connector;

    public AbstractDAO(WrapperConnector connector) {
        this.connector = connector;
    }

    protected boolean save(E entity, String query) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            int insert = statementMapper(entity, preparedStatement);

            return insert != 0;
        } catch (SQLException e) {
            LOGGER.error("Invalid user adding");
            throw new DatabaseRuntimeException("Invalid user adding", e);
        }
    }

    protected Optional<E> findById(Long id, String query) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, Math.toIntExact(id));

            ResultSet entity = preparedStatement.executeQuery();

            return mapResultSetToEntity(entity);
        } catch (SQLException e) {
            LOGGER.error("Invalid user search");
            throw new DatabaseRuntimeException("Invalid user search", e);
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
            LOGGER.error("Invalid users search");
            throw new DatabaseRuntimeException("Invalid users search", e);
        }
    }

    protected void update(E entity, String query) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, Math.toIntExact(entity.getId()));

            statementMapper(entity, preparedStatement);
        } catch (SQLException e) {
            LOGGER.error("Invalid user updating");
            throw new DatabaseRuntimeException("Invalid user updating", e);
        }
    }

    protected boolean deleteById(Long id, String query) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, Math.toIntExact(id));

            int delete = preparedStatement.executeUpdate();
            return delete != 0;
        } catch (SQLException e) {
            LOGGER.error("Invalid user deleting");
            throw new DatabaseRuntimeException("Invalid user deleting", e);
        }
    }

    protected abstract int statementMapper(E entity, PreparedStatement preparedStatement) throws SQLException;

    protected abstract Optional<E> mapResultSetToEntity(ResultSet entity) throws SQLException;
}
