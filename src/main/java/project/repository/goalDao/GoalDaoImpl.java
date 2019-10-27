package project.repository.goalDao;

import project.domain.backlog.Backlog;
import project.domain.goal.Goal;
import project.repository.AbstractDao;
import project.repository.connector.WrapperConnector;

import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class GoalDaoImpl extends AbstractDao<Goal> implements GoalDao {
    private static final String INSERT_GOAL = "INSERT INTO timetracking.goals(goal_name, goal_id) VALUES(?, ?)";
    private static final String FIND_BY_ID = "SELECT * FROM timetracking.goals WHERE goal_id = ?";
    private static final String FIND_ALL_GOALS = "SELECT * FROM timetracking.goals";
    private static final String UPDATE_GOAL = "UPDATE timetracking.goals SET goal_name = ?, goal_id = ? WHERE goal_id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM timetracking.goals WHERE goal_id = ?";

    public GoalDaoImpl(WrapperConnector connector) {
        super(connector);
    }

    @Override
    public boolean save(Goal goal) {
        return save(goal, INSERT_GOAL);
    }

    @Override
    public Optional<Goal> findById(Integer id) {
        return findById(id, FIND_BY_ID);
    }

    @Override
    public List<Goal> findAll() {
        return findAll(FIND_ALL_GOALS);
    }

    @Override
    public void update(Goal goal) {
        update(goal, UPDATE_GOAL);
    }

    @Override
    public boolean deleteById(Integer id) {
        return deleteById(id, DELETE_BY_ID);
    }

    @Override
    protected int statementMapper(Goal goal, PreparedStatement preparedStatement) throws SQLException {
        ParameterMetaData parameterMetaData = preparedStatement.getParameterMetaData();
        int parameterCount = parameterMetaData.getParameterCount();

        if (parameterCount == 2) {
            defaultStatementMap(goal, preparedStatement);
        } else if (parameterCount == 3) {
            defaultStatementMap(goal, preparedStatement);
            preparedStatement.setInt(3, goal.getId());
        }

        return preparedStatement.executeUpdate();
    }

    private void defaultStatementMap(Goal goal, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, goal.getName());
        preparedStatement.setInt(2, goal.getBacklog().getId());
    }

    @Override
    protected Optional<Goal> mapResultSetToEntity(ResultSet goal) throws SQLException {
        Integer id = goal.getInt(1);
        String name = goal.getString(2);
        Backlog backlog = new Backlog(goal.getInt(3));

        return Optional.of(new Goal(id, name, backlog));
    }
}
