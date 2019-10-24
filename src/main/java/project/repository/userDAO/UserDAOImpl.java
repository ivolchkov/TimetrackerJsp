package project.repository.userDAO;

import org.apache.log4j.Logger;
import project.domain.activity.Activity;
import project.domain.user.Role;
import project.domain.user.User;
import project.exception.DatabaseRuntimeException;
import project.repository.AbstractDAO;
import project.repository.connector.WrapperConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAOImpl extends AbstractDAO<User> implements UserDAO {
    private static final Logger LOGGER = Logger.getLogger(UserDAOImpl.class);

    private static final String INSERT_USER = "INSERT INTO timetracking.users(user_name, user_surname, user_email, user_password, user_role) VALUES(?, ?, ?, ?, ?)";
    private static final String FIND_BY_ID = "SELECT * FROM timetracking.users WHERE user_id = ?";
    private static final String FIND_ALL_USERS = "SELECT * FROM timetracking.users";
    private static final String FIND_BY_EMAIL = "SELECT * FROM timetracking.users WHERE user_email = ?";
    private static final String FIND_ACTIVITIES = "SELECT * FROM timetracking.activities AS act " +
            "INNER JOIN timetracking.users as usr ON act.users_user_id = usr.user_id " +
            "WHERE usr.user_id = ?";
    private static final String UPDATE_USER = "UPDATE timetracking.users SET user_name = ?, user_surname = ?, user_email = ?, user_password = ?, user_role = ? WHERE user_id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM timetracking.users WHERE user_id = ?";
    private static final String DELETE_BY_EMAIL = "DELETE FROM timetracking.users WHERE user_email = ?";

    public UserDAOImpl(WrapperConnector connector) {
        super(connector);
    }

    @Override
    public boolean save(User user) {
        return save(user, INSERT_USER);
    }

    @Override
    public Optional<User> findById(Long id) {
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

            return mapResultSetToEntity(user);
        } catch (SQLException e) {
            LOGGER.error("Invalid user search");
            throw new DatabaseRuntimeException("Invalid user search", e);
        }
    }

    @Override
    public void update(User user) {
        update(user, UPDATE_USER);
    }

    @Override
    public boolean deleteById(Long id) {
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
    protected Optional<User> mapResultSetToEntity(ResultSet entity) throws SQLException {
        if ( entity.next() ) {
            long id = entity.getLong(1);
            List<Activity> activities = findActivities(id);

            Role role = entity.getString(6) != null && entity.getString(6).equals("ADMIN")
                    ? Role.ADMIN : Role.CLIENT;

            return Optional.ofNullable(User.builder().withId(id)
                    .withName(entity.getString(2))
                    .withSurname(entity.getString(3))
                    .withEmail(entity.getString(4))
                    .withPassword(entity.getString(5))
                    .withRole(role)
                    .withActivities(activities).build());
        }

        return Optional.empty();
    }

    private Optional<Activity> mapResultSetToActivity(ResultSet activities) throws SQLException {
        return activities.next() ? Optional.ofNullable(Activity.builder().withId(activities.getLong(1))
                .withName(activities.getString(2))
                .withSpentTime(activities.getTime(3))
                .withStart(activities.getTimestamp(4))
                .withEnd(activities.getTimestamp(5))
                .withDescription(activities.getString(6)).build())
                : Optional.empty();
    }

    protected int statementMapper(User user, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getSurname());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setString(4, user.getPassword());
        preparedStatement.setString(5, user.getRole().name());

        return preparedStatement.executeUpdate();
    }

    private List<Activity> findActivities(Long id) {
        List<Activity> result = new ArrayList<>();

        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ACTIVITIES)) {
            preparedStatement.setInt(1, Math.toIntExact(id));

            ResultSet activities = preparedStatement.executeQuery();

            while (activities.next()) {
                mapResultSetToActivity(activities).ifPresent(result::add);
            }

            return result;
        } catch (SQLException e) {
            LOGGER.error("Invalid activities search");
            throw new DatabaseRuntimeException("Invalid activities search", e);
        }
    }
}
