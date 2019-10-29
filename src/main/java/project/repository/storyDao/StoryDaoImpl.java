package project.repository.storyDao;

import org.apache.log4j.Logger;
import project.domain.goal.Goal;
import project.domain.sprint.Sprint;
import project.domain.story.Status;
import project.domain.story.Story;
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
    private static final String FIND_BY_GOAL = "SELECT * FROM timetracking.stories WHERE goal_id = ?";
    private static final String FIND_BY_USER = "SELECT * FROM timetracking.stories WHERE user_id = ?";
    private static final String FIND_BY_SPRINT = "SELECT * FROM timetracking.stories WHERE sprint_id = ?";
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

        return findByStringParam(queryPattern, FIND_BY_NAME);
    }

    @Override
    public List<Story> findByStatus(Status status) {
        String queryPattern = status.getDescription();

        return findByStringParam(queryPattern, FIND_BY_STATUS);
    }

    @Override
    public List<Story> findByGoal(Integer id) {
        return findEntitiesByForeignKey(id, FIND_BY_GOAL);
    }

    @Override
    public List<Story> findByUser(Integer id) {
        return findEntitiesByForeignKey(id, FIND_BY_USER);
    }

    @Override
    public List<Story> findBySprint(Integer id) {
        return findEntitiesByForeignKey(id, FIND_BY_SPRINT);
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
        Goal goal = Goal.builder()
                .withId(story.getInt(6))
                .build();
        User user = User.builder()
                .withId(story.getInt(7))
                .build();
        Sprint sprint = Sprint.builder()
                .withId(story.getInt(8))
                .build();
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
    protected void updateStatementMapper(Story story, PreparedStatement preparedStatement) throws SQLException {
        createStatementMapper(story, preparedStatement);
        preparedStatement.setInt(6, story.getUser().getId());
        preparedStatement.setInt(7, story.getSprint().getId());
        preparedStatement.setInt(8, story.getId());
    }

    @Override
    protected void createStatementMapper(Story story, PreparedStatement preparedStatement) throws SQLException {
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

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Invalid entity updating");
            throw new DatabaseRuntimeException("Invalid entity updating", e);
        }
    }
}
