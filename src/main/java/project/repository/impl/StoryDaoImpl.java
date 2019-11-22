package project.repository.impl;

import org.apache.log4j.Logger;
import project.entity.GoalEntity;
import project.entity.SprintEntity;
import project.entity.Status;
import project.entity.StoryEntity;
import project.entity.UserEntity;
import project.exception.DatabaseRuntimeException;
import project.repository.AbstractDao;
import project.repository.StoryDao;
import project.repository.connector.WrapperConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StoryDaoImpl extends AbstractDao<StoryEntity> implements StoryDao {
    private static final Logger LOGGER = Logger.getLogger(StoryDaoImpl.class);

    private static final String INSERT_STORY = "INSERT INTO timetracking.stories(story_name, story_spent_time, story_description, story_status, goal_id) VALUES(?, ?, ?, ?, ?)";
    private static final String FIND_BY_ID = "SELECT * FROM timetracking.stories WHERE story_id = ?";
    private static final String FIND_BY_GOAL = "SELECT * FROM timetracking.stories WHERE goal_id = ? LIMIT ?, ?";
    private static final String FIND_BY_USER = "SELECT * FROM timetracking.stories WHERE user_id = ? LIMIT ?, ?";
    private static final String FIND_BY_SPRINT = "SELECT * FROM timetracking.stories WHERE sprint_id = ? LIMIT ?, ?";
    private static final String FIND_BY_STATUS = "SELECT * FROM timetracking.stories WHERE story_status = ? LIMIT ?, ?";
    private static final String FIND_ALL_ROWS = "SELECT COUNT(story_id) FROM timetracking.stories";
    private static final String FIND_ALL_ROWS_WITHOUT_USER = "SELECT COUNT(story_id) FROM timetracking.stories WHERE user_id IS NULL";
    private static final String FIND_ALL_ROWS_BY_USER = "SELECT COUNT(story_id) FROM timetracking.stories WHERE user_id = ?";
    private static final String FIND_ALL_STORIES = "SELECT * FROM timetracking.stories LIMIT ?, ?";
    private static final String FIND_WITHOUT_USER = "SELECT * FROM timetracking.stories WHERE user_id IS NULL LIMIT ?, ?";
    private static final String UPDATE_STORY = "UPDATE timetracking.stories SET story_name = ?, story_spent_time = ?, story_description = ?, story_status = ?, goal_id = ?, user_id = ?, sprint_id = ? WHERE story_id = ?";
    private static final String UPDATE_USER_ID = "UPDATE timetracking.stories SET user_id = ? WHERE story_id = ?";

    public StoryDaoImpl(WrapperConnector connector) {
        super(INSERT_STORY, FIND_BY_ID, FIND_ALL_STORIES, FIND_ALL_ROWS, UPDATE_STORY, connector);
    }

    @Override
    public List<StoryEntity> findByStatus(Status status, Integer offset, Integer amount) {
        List<StoryEntity> result = new ArrayList<>();

        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_STATUS)) {
            statement.setString(1, status.getDescription());
            statement.setInt(2, offset);
            statement.setInt(3, amount);
            ResultSet entities = statement.executeQuery();

            while(entities.next()) {
                mapResultSetToEntity(entities).ifPresent(result::add);
            }

            return result;
        } catch (SQLException e) {
            LOGGER.error("Invalid entity search by status" , e);
            throw new DatabaseRuntimeException("Invalid entity search by status", e);
        }
    }

    @Override
    public List<StoryEntity> findByGoal(Integer id, Integer offset, Integer amount) {
        return findEntitiesByForeignKey(id, FIND_BY_GOAL, offset, amount);
    }

    @Override
    public List<StoryEntity> findByUser(Integer id, Integer offset, Integer amount) {
        return findEntitiesByForeignKey(id, FIND_BY_USER, offset, amount);
    }

    @Override
    public List<StoryEntity> findBySprint(Integer id, Integer offset, Integer amount) {
        return findEntitiesByForeignKey(id, FIND_BY_SPRINT, offset, amount);
    }

    @Override
    public List<StoryEntity> findWithoutUser(Integer offset, Integer amount) {
        return findAll(offset, amount, FIND_WITHOUT_USER);
    }

    @Override
    public Integer findAmountOfRowsWithoutUser() {
        return count(FIND_ALL_ROWS_WITHOUT_USER);
    }

    @Override
    public Integer findAmountOfRowsByUser(Integer id) {
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_ROWS_BY_USER)) {
            statement.setInt(1, id);

            ResultSet count = statement.executeQuery();

            return count.next() ? count.getInt(1) : 0;
        } catch (SQLException e) {
            LOGGER.error("Invalid entities count by id" , e);
            throw new DatabaseRuntimeException("Invalid entities count by id", e);
        }
    }

    @Override
    public void updateUserId(StoryEntity story) {
        updateForeignKeyId(story.getUser().getId(), story.getId());
    }

    @Override
    public void updateSprintId(StoryEntity story) {
        updateForeignKeyId(story.getSprint().getId(), story.getId());
    }

    @Override
    protected Optional<StoryEntity> mapResultSetToEntity(ResultSet story) throws SQLException {
        GoalEntity goal = GoalEntity.builder()
                .withId(story.getInt(6))
                .build();
        UserEntity user = UserEntity.builder()
                .withId(story.getInt(7))
                .build();
        SprintEntity sprint = SprintEntity.builder()
                .withId(story.getInt(8))
                .build();
        String sts = story.getString(5);
        Status status = sts.equals("To do") ? Status.TO_DO : (sts.equals("In process") ? Status.IN_PROCESS : Status.DONE);

        return Optional.of(StoryEntity.builder().withId(story.getInt(1))
                .withName(story.getNString(2))
                .withSpentTime(story.getTime(3).toLocalTime())
                .withDescription(story.getNString(4))
                .withStatus(status)
                .withGoal(goal)
                .withUser(user)
                .withSprint(sprint)
                .build());
    }

    @Override
    protected void updateStatementMapper(StoryEntity story, PreparedStatement preparedStatement) throws SQLException {
        createStatementMapper(story, preparedStatement);
        preparedStatement.setInt(6, story.getUser().getId());
        preparedStatement.setInt(7, story.getSprint().getId());
        preparedStatement.setInt(8, story.getId());
    }

    @Override
    protected void createStatementMapper(StoryEntity story, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setNString(1, story.getName());
        preparedStatement.setTime(2, Time.valueOf(story.getSpentTime()));
        preparedStatement.setNString(3, story.getDescription());
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
            LOGGER.error("Invalid entity updating", e);
            throw new DatabaseRuntimeException("Invalid entity updating", e);
        }
    }
}
