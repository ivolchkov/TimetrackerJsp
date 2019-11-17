package project.repository.impl;

import project.entity.BacklogEntity;
import project.entity.GoalEntity;
import project.repository.AbstractDao;
import project.repository.GoalDao;
import project.repository.connector.WrapperConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class GoalDaoImpl extends AbstractDao<GoalEntity> implements GoalDao {
    private static final String INSERT_GOAL = "INSERT INTO timetracking.goals(goal_name, backlog_id) VALUES(?, ?)";
    private static final String FIND_BY_ID = "SELECT * FROM timetracking.goals WHERE goal_id = ?";
    private static final String FIND_ALL_ROWS = "SELECT COUNT(goal_id) FROM timetracking.goals";
    private static final String FIND_ALL_GOALS = "SELECT * FROM timetracking.goals LIMIT ?, ?";
    private static final String UPDATE_GOAL = "UPDATE timetracking.goals SET goal_name = ?, backlog_id = ? WHERE goal_id = ?";

    public GoalDaoImpl(WrapperConnector connector) {
        super(INSERT_GOAL, FIND_BY_ID, FIND_ALL_GOALS, FIND_ALL_ROWS, UPDATE_GOAL, connector);
    }

    @Override
    protected void updateStatementMapper(GoalEntity goal, PreparedStatement preparedStatement) throws SQLException {
        createStatementMapper(goal, preparedStatement);
        preparedStatement.setInt(3, goal.getId());
    }

    @Override
    protected void createStatementMapper(GoalEntity goal, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setNString(1, goal.getName());
        preparedStatement.setInt(2, goal.getBacklog().getId());
    }

    @Override
    protected Optional<GoalEntity> mapResultSetToEntity(ResultSet goal) throws SQLException {
        BacklogEntity backlog = BacklogEntity.builder()
                .withId(goal.getInt(3))
                .build();

        return Optional.of(GoalEntity.builder().withId(goal.getInt(1))
                .withName(goal.getNString(2))
                .withBacklog(backlog)
                .build());
    }
}
