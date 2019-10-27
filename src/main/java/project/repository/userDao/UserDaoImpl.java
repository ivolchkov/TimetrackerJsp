package project.repository.userDao;

import org.apache.log4j.Logger;
import project.domain.story.Status;
import project.domain.story.Story;
import project.domain.user.Role;
import project.domain.user.User;
import project.exception.DatabaseRuntimeException;
import project.repository.AbstractDao;
import project.repository.connector.WrapperConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {
    private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);

    private static final String INSERT_USER = "INSERT INTO timetracking.users(user_name, user_surname, user_email, user_password, user_role) VALUES(?, ?, ?, ?, ?)";
    private static final String FIND_BY_ID = "SELECT * FROM timetracking.users WHERE user_id = ?";
    private static final String FIND_ALL_USERS = "SELECT * FROM timetracking.users";
    private static final String FIND_BY_EMAIL = "SELECT * FROM timetracking.users WHERE user_email = ?";
    private static final String FIND_STORIES = "SELECT * FROM timetracking.stories AS str " +
            "INNER JOIN timetracking.users as usr ON str.user_id = usr.user_id " +
            "WHERE usr.user_id = ?";
    private static final String UPDATE_USER = "UPDATE timetracking.users SET user_name = ?, user_surname = ?, user_email = ?, user_password = ?, user_role = ? WHERE user_id = ?";
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
        Integer id = user.getInt(1);
        List<Story> stories = findStories(id);
        String userRole = user.getString(6);
        Role role = userRole.equals("Admin") ? Role.ADMIN : (userRole.equals("Developer") ? Role.DEVELOPER : Role.SCRUM_MASTER);

        return Optional.ofNullable(User.builder().withId(id)
                .withName(user.getString(2))
                .withSurname(user.getString(3))
                .withEmail(user.getString(4))
                .withPassword(user.getString(5))
                .withRole(role)
                .withStories(stories).build());
    }

    private Optional<Story> mapResultSetToStory(ResultSet story) throws SQLException {
        String sts = story.getString(5);
        Status status = sts.equals("To do") ? Status.TO_DO : (sts.equals("In process") ? Status.IN_PROCESS : Status.DONE);

        return Optional.of(Story.builder().withId(story.getInt(1))
                .withName(story.getString(2))
                .withSpentTime(story.getTime(3).toLocalTime())
                .withDescription(story.getString(4))
                .withStatus(status)
                .build());
    }

    @Override
    protected int statementMapper(User user, PreparedStatement preparedStatement) throws SQLException {
        ParameterMetaData parameterMetaData = preparedStatement.getParameterMetaData();
        int parameterCount = parameterMetaData.getParameterCount();

        if (parameterCount == 5) {
            defaultStatementMap(user, preparedStatement);
        } else if (parameterCount == 6) {
            defaultStatementMap(user, preparedStatement);
            preparedStatement.setInt(6, user.getId());
        }

        return preparedStatement.executeUpdate();
    }

    private void defaultStatementMap(User user, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getSurname());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setString(4, user.getPassword());
        preparedStatement.setString(5, user.getRole().getDescription());
    }

    private List<Story> findStories(Integer id) {
        List<Story> result = new ArrayList<>();

        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_STORIES)) {
            preparedStatement.setInt(1, id);

            ResultSet stories = preparedStatement.executeQuery();

            while (stories.next()) {
                mapResultSetToStory(stories).ifPresent(result::add);
            }

            return result;
        } catch (SQLException e) {
            LOGGER.error("Invalid activities search");
            throw new DatabaseRuntimeException("Invalid activities search", e);
        }
    }
}
