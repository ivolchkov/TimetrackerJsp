package project.repository;

import org.apache.log4j.Logger;
import project.domain.activity.Activity;
import project.domain.user.Role;
import project.domain.user.User;
import project.repository.connector.WrapperConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class UserDAOImpl extends AbstractDAO implements UserDAO {
    private static final Logger LOGGER = Logger.getLogger("file");

    private static final String INSERT_USER = "INSERT INTO timetracking.users(user_name, user_surname, user_email, user_password, user_role) VALUES(?, ?, ?, ?, ?)";
    private static final String FIND_BY_ID = "SELECT * FROM timetracking.users WHERE user_id = ?";
    private static final String FIND_ALL_USERS = "SELECT * FROM timetracking.users";
    private static final String FIND_BY_EMAIL = "SELECT * FROM timetracking.users WHERE user_email = ?";
    private static final String FIND_ACTIVITIES = "SELECT * FROM timetracking.activities AS act " +
            "INNER JOIN (SELECT user_id FROM timetracking.users AS usr " +
                        "INNER JOIN timetracking.users_has_activities AS usrAct ON usr.user_id = usrAct.users_user_id " +
                        "WHERE usr.user_id = ?) AS user " +
            "ON act.activity_id = user.user_id";
    private static final String UPDATE_USER = "UPDATE timetracking.users SET user_name = ?, user_surname = ?, user_email = ?, user_password = ?, user_role = ? WHERE user_id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM timetracking.users WHERE user_id = ?";
    private static final String DELETE_BY_EMAIL = "DELETE FROM timetracking.users WHERE user_email = ?";

    public UserDAOImpl() {
        this.connector = new WrapperConnector();
    }

    @Override
    public boolean save(User object) {
        try (PreparedStatement preparedStatement = this.connector.getPreparedStatement(INSERT_USER)) {
            int insert = statementMapper(object, preparedStatement);

            return insert != 0;
        } catch (SQLException e) {
            LOGGER.warn("Invalid user adding");
            return false;
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        try (PreparedStatement preparedStatement = this.connector.getPreparedStatement(FIND_BY_ID)) {
            preparedStatement.setInt(1, Math.toIntExact(id));

            try (ResultSet user = preparedStatement.executeQuery()) {
                return Optional.ofNullable(userMapper(user));
            }
        } catch (SQLException e) {
            LOGGER.warn("Invalid user search");
            return Optional.empty();
        }
    }

    @Override
    public List<User> findAll() {
        List<User> result = new ArrayList<>();

        try (Statement statement = this.connector.getStatement();
             ResultSet users = statement.executeQuery(FIND_ALL_USERS)) {
            while(users.next()) {
                result.add(userMapper(users));
            }

            return result;
        } catch (SQLException e) {
            LOGGER.warn("Invalid users search");
            return Collections.emptyList();
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try (PreparedStatement preparedStatement = this.connector.getPreparedStatement(FIND_BY_EMAIL)) {
            preparedStatement.setString(1, email);

            try (ResultSet user = preparedStatement.executeQuery()) {
                return Optional.ofNullable(userMapper(user));
            }
        } catch (SQLException e) {
            LOGGER.warn("Invalid user search");
            return Optional.empty();
        }
    }

    @Override
    public List<Activity> findActivities(Long id) {
        List<Activity> result = new ArrayList<>();

        try (PreparedStatement preparedStatement = this.connector.getPreparedStatement(FIND_ACTIVITIES)) {
            preparedStatement.setInt(1, Math.toIntExact(id));

            try (ResultSet activities = preparedStatement.executeQuery()) {

                while (activities.next()) {
                    result.add(activityMapper(activities));
                }
            }

            return result;
        } catch (SQLException e) {
            LOGGER.warn("Invalid users search");
            return Collections.emptyList();
        }
    }

    @Override
    public void update(User object) {
        try (PreparedStatement preparedStatement = this.connector.getPreparedStatement(UPDATE_USER)) {
            preparedStatement.setInt(1, Math.toIntExact(object.getId()));

            statementMapper(object, preparedStatement);
        } catch (SQLException e) {
            LOGGER.warn("Invalid user updating");
        }
    }

    @Override
    public boolean deleteById(Long id) {
        try (PreparedStatement preparedStatement = this.connector.getPreparedStatement(DELETE_BY_ID)) {
            preparedStatement.setInt(1, Math.toIntExact(id));

            int delete = preparedStatement.executeUpdate();
            return delete != 0;
        } catch (SQLException e) {
            LOGGER.warn("Invalid user deleting");
            return false;
        }
    }

    @Override
    public boolean deleteByEmail(String email) {
        try (PreparedStatement preparedStatement = this.connector.getPreparedStatement(DELETE_BY_EMAIL)) {
            preparedStatement.setString(1, email);

            int delete = preparedStatement.executeUpdate();
            return delete != 0;
        } catch (SQLException e) {
            LOGGER.warn("Invalid user deleting");
            return false;
        }
    }

    private User userMapper(ResultSet user) throws SQLException {
        Role role = user.getString(6) != null && user.getString(6).equals("ADMIN")
                ? Role.ADMIN : Role.CLIENT;

        return User.builder().withId(user.getLong(1))
                .withName(user.getString(2))
                .withSurname(user.getString(3))
                .withEmail(user.getString(4))
                .withPassword(user.getString(5))
                .withRole(role).build();
    }

    private Activity activityMapper(ResultSet activities) throws SQLException {
        return Activity.builder().withId(activities.getLong(1))
                .withName(activities.getString(2))
                .withSpentTime(activities.getTime(3))
                .withStart(activities.getTimestamp(4))
                .withEnd(activities.getTimestamp(5))
                .withDescription(activities.getString(6)).build();
    }

    private int statementMapper(User object, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, object.getName());
        preparedStatement.setString(2, object.getSurname());
        preparedStatement.setString(3, object.getEmail());
        preparedStatement.setString(4, object.getPassword());
        preparedStatement.setString(5, object.getRole().name());

        return preparedStatement.executeUpdate();
    }
}
