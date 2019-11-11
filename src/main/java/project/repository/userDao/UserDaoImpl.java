package project.repository.userDao;

import org.apache.log4j.Logger;
import project.domain.user.User;
import project.entity.user.Role;
import project.entity.user.UserEntity;
import project.exception.DatabaseRuntimeException;
import project.repository.AbstractDao;
import project.repository.connector.WrapperConnector;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl extends AbstractDao<UserEntity> implements UserDao {
    private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);

    private static final String INSERT_USER = "INSERT INTO timetracking.users(user_name, user_surname, user_email, user_password, user_role) VALUES(?, ?, ?, ?, ?)";
    private static final String FIND_BY_ID = "SELECT * FROM timetracking.users WHERE user_id = ?";
    private static final String FIND_ALL_USERS = "SELECT * FROM timetracking.users LIMIT ?, ?";
    private static final String FIND_ALL_ROWS = "SELECT COUNT(user_id) FROM timetracking.users";
    private static final String FIND_BY_BACKLOG = "SELECT * FROM timetracking.users WHERE backlog_id = ? LIMIT ?, ?";
    private static final String FIND_BY_EMAIL = "SELECT * FROM timetracking.users WHERE user_email = ?";
    private static final String UPDATE_USER = "UPDATE timetracking.users SET user_name = ?, user_surname = ?, user_email = ?, user_password = ?, user_role = ?, backlog_id = ? WHERE user_id = ?";
    private static final String UPDATE_USER_BY_BACKLOG = "UPDATE timetracking.users SET backlog_id = ? WHERE user_id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM timetracking.users WHERE user_id = ?";

    public UserDaoImpl(WrapperConnector connector) {
        super(connector);
    }

    @Override
    public boolean save(UserEntity user) {
        return save(user, INSERT_USER);
    }

    @Override
    public Optional<UserEntity> findById(Integer id) {
        return findById(id, FIND_BY_ID);
    }

    @Override
    public List<UserEntity> findAll(Integer offset, Integer amount) {
        return findAll(FIND_ALL_USERS, offset, amount);
    }

    @Override
    public Integer findAmountOfRows() {
        return findNumberOfRows(FIND_ALL_ROWS);
    }

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_EMAIL)) {
            preparedStatement.setString(1, email);

            ResultSet user = preparedStatement.executeQuery();

            return user.next() ? mapResultSetToEntity(user) : Optional.empty();
        } catch (SQLException e) {
            LOGGER.error("Invalid user search" , e);
            throw new DatabaseRuntimeException("Invalid user search", e);
        }
    }

    @Override
    public List<UserEntity> findByBacklog(Integer id, Integer offset, Integer amount) {
        return findEntitiesByForeignKey(id, FIND_BY_BACKLOG, offset, amount);
    }

    @Override
    public void updateBacklogId(UserEntity user) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_BY_BACKLOG)) {
            preparedStatement.setInt(1, user.getBacklog().getId());
            preparedStatement.setInt(2, user.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Invalid entity updating" , e);
            throw new DatabaseRuntimeException("Invalid entity updating", e);
        }
    }

    @Override
    public void update(UserEntity user) {
        update(user, UPDATE_USER);
    }

    @Override
    public boolean deleteById(Integer id) {
        return deleteById(id, DELETE_BY_ID);
    }

    @Override
    protected Optional<UserEntity> mapResultSetToEntity(ResultSet user) throws SQLException {
        String userRole = user.getString(6);
        Role role = userRole.equals("Admin") ? Role.ADMIN : (userRole.equals("Developer") ? Role.DEVELOPER : Role.SCRUM_MASTER);

        return Optional.ofNullable(UserEntity.builder()
                .withId(user.getInt(1))
                .withName(user.getString(2))
                .withSurname(user.getString(3))
                .withEmail(user.getString(4))
                .withPassword(user.getString(5))
                .withRole(role)
                .build());
    }

    @Override
    protected void updateStatementMapper(UserEntity user, PreparedStatement preparedStatement) throws SQLException {
        createStatementMapper(user, preparedStatement);
        preparedStatement.setInt(6, user.getBacklog().getId());
        preparedStatement.setInt(7, user.getId());
    }

    @Override
    protected void createStatementMapper(UserEntity user, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getSurname());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setString(4, user.getPassword());
        preparedStatement.setString(5, user.getRole().getDescription());
    }

}
