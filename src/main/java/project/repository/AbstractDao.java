package project.repository;

import org.apache.log4j.Logger;
import project.exception.DatabaseRuntimeException;
import project.repository.connector.WrapperConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDao<E> implements CrudRepository<Integer, E> {
    private static final Logger LOGGER = Logger.getLogger(AbstractDao.class);

    private final String saveQuery;
    private final String findByIdQuery;
    private final String findAllQuery;
    private final String countQuery;
    private final String updateQuery;

    protected WrapperConnector connector;

    public AbstractDao(String saveQuery, String findByIdQuery, String findAllQuery, String countQuery,
                       String updateQuery, WrapperConnector connector) {
        this.saveQuery = saveQuery;
        this.findByIdQuery = findByIdQuery;
        this.findAllQuery = findAllQuery;
        this.countQuery = countQuery;
        this.updateQuery = updateQuery;
        this.connector = connector;
    }

    public boolean save(E entity) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(saveQuery)) {
            createStatementMapper(entity, preparedStatement);
            int insert =  preparedStatement.executeUpdate();

            return insert != 0;
        } catch (SQLException e) {
            LOGGER.error("Invalid entity adding" , e);
            throw new DatabaseRuntimeException("Invalid entity adding", e);
        }
    }

    public Optional<E> findById(Integer id) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(findByIdQuery)) {
            preparedStatement.setInt(1, id);

            ResultSet entity = preparedStatement.executeQuery();

            return entity.next() ? mapResultSetToEntity(entity) : Optional.empty();
        } catch (SQLException e) {
            LOGGER.error("Invalid entity search" , e);
            throw new DatabaseRuntimeException("Invalid entity search", e);
        }
    }

    public Integer findAmountOfRows() {
        return count(countQuery);
    }

    public List<E> findAll(Integer offset, Integer amount) {
        return findAll(offset, amount, findAllQuery);
    }

    public void update(E entity) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            updateStatementMapper(entity, preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Invalid entity updating" , e);
            throw new DatabaseRuntimeException("Invalid entity updating", e);
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

    protected List<E> findAll(Integer offset, Integer amount, String query) {
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

    protected Integer count(String query) {
        try (Connection connection = connector.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet count = statement.executeQuery(query);

            return count.next() ? count.getInt(1) : 0;
        } catch (SQLException e) {
            LOGGER.error("Invalid entities count" , e);
            throw new DatabaseRuntimeException("Invalid entities count", e);
        }
    }

    protected abstract void updateStatementMapper(E entity, PreparedStatement preparedStatement) throws SQLException;
    protected abstract void createStatementMapper(E entity, PreparedStatement preparedStatement) throws SQLException;
    protected abstract Optional<E> mapResultSetToEntity(ResultSet entity) throws SQLException;
}
