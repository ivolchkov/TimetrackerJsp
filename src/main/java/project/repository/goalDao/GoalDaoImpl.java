package project.repository.goalDao;

import project.entity.backlog.BacklogEntity;
import project.entity.goal.GoalEntity;
import project.repository.AbstractDao;
import project.repository.connector.WrapperConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class GoalDaoImpl extends AbstractDao<GoalEntity> implements GoalDao {
    private static final String INSERT_GOAL = "INSERT INTO timetracking.goals(goal_name, backlog_id) VALUES(?, ?)";
    private static final String FIND_BY_ID = "SELECT * FROM timetracking.goals WHERE goal_id = ?";
    private static final String FIND_BY_BACKLOG = "SELECT * FROM timetracking.goals WHERE backlog_id = ?";
    private static final String FIND_ALL_GOALS = "SELECT * FROM timetracking.goals";
    private static final String UPDATE_GOAL = "UPDATE timetracking.goals SET goal_name = ?, backlog_id = ? WHERE goal_id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM timetracking.goals WHERE goal_id = ?";

    public GoalDaoImpl(WrapperConnector connector) {
        super(connector);
    }

    @Override
    public boolean save(GoalEntity goal) {
        return save(goal, INSERT_GOAL);
    }

    @Override
    public Optional<GoalEntity> findById(Integer id) {
        return findById(id, FIND_BY_ID);
    }

    @Override
    public List<GoalEntity> findAll() {
        return findAll(FIND_ALL_GOALS);
    }

    @Override
    public void update(GoalEntity goal) {
        update(goal, UPDATE_GOAL);
    }

    @Override
    public boolean deleteById(Integer id) {
        return deleteById(id, DELETE_BY_ID);
    }

    @Override
    protected void updateStatementMapper(GoalEntity goal, PreparedStatement preparedStatement) throws SQLException {
        createStatementMapper(goal, preparedStatement);
        preparedStatement.setInt(3, goal.getId());
    }

    @Override
    protected void createStatementMapper(GoalEntity goal, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, goal.getName());
        preparedStatement.setInt(2, goal.getBacklog().getId());
    }

    @Override
    protected Optional<GoalEntity> mapResultSetToEntity(ResultSet goal) throws SQLException {
        BacklogEntity backlog = BacklogEntity.builder()
                .withId(goal.getInt(3))
                .build();

        return Optional.of(GoalEntity.builder().withId(goal.getInt(1))
                .withName(goal.getString(2))
                .withBacklog(backlog)
                .build());
    }

    @Override
    public List<GoalEntity> findByBacklog(Integer id) {
        return findEntitiesByForeignKey(id, FIND_BY_BACKLOG);
    }
}
