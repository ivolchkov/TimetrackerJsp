package project.repository.userDao;

import org.apache.log4j.Logger;
import project.domain.user.Role;
import project.domain.user.User;
import project.exception.DatabaseRuntimeException;
import project.repository.AbstractDao;
import project.repository.connector.WrapperConnector;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {
    private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);

    private static final String INSERT_USER = "INSERT INTO timetracking.users(user_name, user_surname, user_email, user_password, user_role) VALUES(?, ?, ?, ?, ?)";
    private static final String FIND_BY_ID = "SELECT * FROM timetracking.users WHERE user_id = ?";
    private static final String FIND_ALL_USERS = "SELECT * FROM timetracking.users";
    private static final String FIND_BY_BACKLOG = "SELECT * FROM timetracking.users WHERE backlog_id = ?";
    private static final String FIND_BY_EMAIL = "SELECT * FROM timetracking.users WHERE user_email = ?";
    private static final String UPDATE_USER = "UPDATE timetracking.users SET user_name = ?, user_surname = ?, user_email = ?, user_password = ?, user_role = ?, backlog_id = ? WHERE user_id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM timetracking.users WHERE user_id = ?";
    private static final String DELETE_BY_EMAIL = "DELETE FROM timetracking.users WHERE user_email = ?";

    public UserDaoImpl(WrapperConnector connector) {
        super(connector);
    }

    @Override
    public boolean save(User user) {
        return save(user, INSERT_USER);
    }

    @Override
    public Optional<User> findById(Integer id) {
        return findById(id, FIND_BY_ID);
    }

    @Override
    public List<User> findAll() {
        return findAll(FIND_ALL_USERS);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_EMAIL)) {
            preparedStatement.setString(1, email);

            ResultSet user = preparedStatement.executeQuery();

            return user.next() ? mapResultSetToEntity(user) : Optional.empty();
        } catch (SQLException e) {
            LOGGER.error("Invalid user search");
            throw new DatabaseRuntimeException("Invalid user search", e);
        }
    }

    @Override
    public List<User> findByBacklog(Integer id) {
        return findEntitiesByForeignKey(id, FIND_BY_BACKLOG);
    }

    @Override
    public void update(User user) {
        update(user, UPDATE_USER);
    }

    @Override
    public boolean deleteById(Integer id) {
        return deleteById(id, DELETE_BY_ID);
    }

    @Override
    public boolean deleteByEmail(String email) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_EMAIL)) {
            preparedStatement.setString(1, email);

            int delete = preparedStatement.executeUpdate();
            return delete != 0;
        } catch (SQLException e) {
            LOGGER.error("Invalid user deleting");
            throw new DatabaseRuntimeException("Invalid user deleting", e);
        }
    }


    @Override
    protected Optional<User> mapResultSetToEntity(ResultSet user) throws SQLException {
        String userRole = user.getString(6);
        Role role = userRole.equals("Admin") ? Role.ADMIN : (userRole.equals("Developer") ? Role.DEVELOPER : Role.SCRUM_MASTER);

        return Optional.ofNullable(User.builder()
                .withId(user.getInt(1))
                .withName(user.getString(2))
                .withSurname(user.getString(3))
                .withEmail(user.getString(4))
                .withPassword(user.getString(5))
                .withRole(role)
                .build());
    }

    @Override
    protected void updateStatementMapper(User user, PreparedStatement preparedStatement) throws SQLException {
        createStatementMapper(user, preparedStatement);
        preparedStatement.setInt(6, user.getBacklog().getId());
        preparedStatement.setInt(7, user.getId());
    }

    @Override
    protected void createStatementMapper(User user, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getSurname());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setString(4, user.getPassword());
        preparedStatement.setString(5, user.getRole().getDescription());
    }

}
