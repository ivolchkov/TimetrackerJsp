package project.repository.storyDao;

import org.apache.log4j.Logger;
import project.domain.goal.Goal;
import project.domain.sprint.Sprint;
import project.domain.story.Status;
import project.domain.story.Story;
import project.domain.user.Role;
import project.domain.user.User;
import project.exception.DatabaseRuntimeException;
import project.repository.AbstractDao;
import project.repository.connector.WrapperConnector;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class StoryDaoImpl extends AbstractDao<Story> implements StoryDao {
    private static final Logger LOGGER = Logger.getLogger(StoryDaoImpl.class);

    private static final String INSERT_STORY = "INSERT INTO timetracking.stories(story_name, story_spent_time, story_description, story_status, goal_id) VALUES(?, ?, ?, ?, ?)";
    private static final String FIND_BY_ID = "SELECT * FROM timetracking.stories WHERE story_id = ?";
    private static final String FIND_ALL_STORIES = "SELECT * FROM timetracking.stories";
    private static final String UPDATE_STORY = "UPDATE timetracking.stories SET story_name = ?, story_spent_time = ?, story_description = ?, story_status = ?, goal_id = ?, user_id = ?, sprint_id = ? WHERE story_id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM timetracking.stories WHERE story_id = ?";
    private static final String FIND_BY_NAME = "SELECT * FROM timetracking.stories WHERE story_name LIKE ?";
    private static final String FIND_BY_STATUS = "SELECT * FROM timetracking.stories WHERE story_status = ?";
    private static final String UPDATE_USER_ID = "UPDATE timetracking.stories SET user_id = ? WHERE story_id = ?";

    public StoryDaoImpl(WrapperConnector connector) {
        super(connector);
    }

    @Override
    public boolean save(Story story) {
        return save(story, INSERT_STORY);
    }

    @Override
    public List<Story> findByNamePattern(String pattern) {
        String queryPattern = "%" + pattern + "%";

        return findByString(queryPattern, FIND_BY_NAME);
    }

    @Override
    public List<Story> findByStatus(Status status) {
        String queryPattern = status.getDescription();

        return findByString(queryPattern, FIND_BY_STATUS);
    }

    @Override
    public Optional<Story> findById(Integer id) {
        return findById(id, FIND_BY_ID);
    }

    @Override
    public List<Story> findAll() {
        return findAll(FIND_ALL_STORIES);
    }

    @Override
    public void update(Story story) {
        update(story, UPDATE_STORY);
    }

    @Override
    public void updateUserId(Story story) {
        updateForeignKeyId(story.getUser().getId(), story.getId());
    }

    @Override
    public void updateSprintId(Story story) {
        updateForeignKeyId(story.getSprint().getId(), story.getId());
    }

    @Override
    public boolean deleteById(Integer id) {
        return deleteById(id, DELETE_BY_ID);
    }

    @Override
    protected Optional<Story> mapResultSetToEntity(ResultSet story) throws SQLException {
        Goal goal = new Goal(story.getInt(6));
        User user = User.builder().withId(story.getInt(7)).build();
        Sprint sprint = Sprint.builder().withId(story.getInt(8)).build();
        String sts = story.getString(5);
        Status status = sts.equals("To do") ? Status.TO_DO : (sts.equals("In process") ? Status.IN_PROCESS : Status.DONE);

        return Optional.of(Story.builder().withId(story.getInt(1))
                .withName(story.getString(2))
                .withSpentTime(story.getTime(3).toLocalTime())
                .withDescription(story.getString(4))
                .withStatus(status)
                .withGoal(goal)
                .withUser(user)
                .withSprint(sprint)
                .build());
    }

    @Override
    protected int statementMapper(Story story, PreparedStatement preparedStatement) throws SQLException {
        ParameterMetaData parameterMetaData = preparedStatement.getParameterMetaData();
        int parameterCount = parameterMetaData.getParameterCount();

        if (parameterCount == 5) {
            defaultStatementMap(story, preparedStatement);
        } else if (parameterCount == 8) {
            defaultStatementMap(story, preparedStatement);

            preparedStatement.setInt(6, story.getUser().getId());
            preparedStatement.setInt(7, story.getSprint().getId());
            preparedStatement.setInt(8, story.getId());
        }

        return preparedStatement.executeUpdate();
    }

    private void defaultStatementMap(Story story, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, story.getName());
        preparedStatement.setTime(2, Time.valueOf(story.getSpentTime()));
        preparedStatement.setString(3, story.getDescription());
        preparedStatement.setString(4, story.getStatus().getDescription());
        preparedStatement.setInt(5, story.getGoal().getId());
    }

    private void updateForeignKeyId(Integer id, Integer storyId) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_ID)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, storyId);
        } catch (SQLException e) {
            LOGGER.error("Invalid entity updating");
            throw new DatabaseRuntimeException("Invalid entity updating", e);
        }
    }
}
