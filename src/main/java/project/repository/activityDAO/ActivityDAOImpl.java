package project.repository.activityDAO;

import org.apache.log4j.Logger;
import project.domain.activity.Activity;
import project.exception.DatabaseRuntimeException;
import project.repository.AbstractDAO;
import project.repository.connector.WrapperConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ActivityDAOImpl extends AbstractDAO<Activity> implements ActivityDAO {
    private static final Logger LOGGER = Logger.getLogger(ActivityDAOImpl.class);

    private static final String INSERT_ACTIVITY = "INSERT INTO timetracking.activities(activity_name, activity_spent_time, activity_start, activity_end, activity_description, users_user_id) VALUES(?, ?, ?, ?, ?, ?)";
    private static final String FIND_BY_ID = "SELECT * FROM timetracking.activities WHERE activity_id = ?";
    private static final String FIND_ALL_ACTIVITIES = "SELECT * FROM timetracking.activities";
    private static final String UPDATE_ACTIVITY = "UPDATE timetracking.activities SET activity_name = ?, activity_spent_time = ?, activity_start = ?, activity_end= ?, activity_description = ?, users_user_id = ? WHERE activity_id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM timetracking.activities WHERE activity_id = ?";
    private static final String FIND_BY_NAME = "SELECT * FROM timetracking.activities WHERE activity_name LIKE ?";

    public ActivityDAOImpl(WrapperConnector connector) {
        super(connector);
    }

    @Override
    public List<Activity> findByNamePattern(String pattern) {
        String queryPattern = "'%" + pattern + "%'";
        List<Activity> result = new ArrayList<>();

        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_NAME)) {
            statement.setString(1, queryPattern);
            ResultSet entities = statement.executeQuery();

            while(entities.next()) {
                mapResultSetToEntity(entities).ifPresent(result::add);
            }

            return result;
        } catch (SQLException e) {
            LOGGER.error("Invalid activities search");
            throw new DatabaseRuntimeException("Invalid activities search", e);
        }
    }

    @Override
    public boolean save(Activity activity) {
        return save(activity, INSERT_ACTIVITY);
    }

    @Override
    public Optional<Activity> findById(Long id) {
        return findById(id, FIND_BY_ID);
    }

    @Override
    public List<Activity> findAll() {
        return findAll(FIND_ALL_ACTIVITIES);
    }

    @Override
    public void update(Activity activity) {
        update(activity, UPDATE_ACTIVITY);
    }

    @Override
    public boolean deleteById(Long id) {
        return deleteById(id, DELETE_BY_ID);
    }

    @Override
    protected Optional<Activity> mapResultSetToEntity(ResultSet activities) throws SQLException {
        return activities.next() ? Optional.ofNullable(Activity.builder().withId(activities.getLong(1))
                .withName(activities.getString(2))
                .withSpentTime(activities.getTime(3))
                .withStart(activities.getTimestamp(4))
                .withEnd(activities.getTimestamp(5))
                .withDescription(activities.getString(6))
                .withUserId(activities.getLong(7))
                .build())
                : Optional.empty();
    }

    @Override
    protected int statementMapper(Activity activity, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, activity.getName());
        preparedStatement.setTime(2, activity.getSpentTime());
        preparedStatement.setTimestamp(3, activity.getStart());
        preparedStatement.setTimestamp(4, activity.getEnd());
        preparedStatement.setString(5, activity.getDescription());
        preparedStatement.setLong(6, activity.getUserId());

        return preparedStatement.executeUpdate();
    }
}
