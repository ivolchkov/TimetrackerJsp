package project.repository.sprintDao;

import project.entity.sprint.SprintEntity;
import project.repository.AbstractDao;
import project.repository.connector.WrapperConnector;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class SprintDaoImpl extends AbstractDao<SprintEntity> implements SprintDao {
    private static final String INSERT_SPRINT = "INSERT INTO timetracking.sprints(sprint_name, sprint_start, sprint_end, sprint_description) VALUES(?, ?, ?, ?)";
    private static final String FIND_BY_ID = "SELECT * FROM timetracking.sprints WHERE sprint_id = ?";
    private static final String FIND_ALL_SPRINTS = "SELECT * FROM timetracking.sprints";
    private static final String UPDATE_SPRINT = "UPDATE timetracking.sprints SET sprint_name = ?, sprint_start = ?, sprint_end = ?, sprint_description = ? WHERE sprint_id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM timetracking.sprints WHERE sprint_id = ?";


    public SprintDaoImpl(WrapperConnector connector) {
        super(connector);
    }

    @Override
    public boolean save(SprintEntity entity) {
        return save(entity, INSERT_SPRINT);
    }

    @Override
    public Optional<SprintEntity> findById(Integer id) {
        return findById(id, FIND_BY_ID);
    }

    @Override
    public List<SprintEntity> findAll() {
        return findAll(FIND_ALL_SPRINTS);
    }

    @Override
    public void update(SprintEntity entity) {
        update(entity, UPDATE_SPRINT);
    }

    @Override
    public boolean deleteById(Integer id) {
        return deleteById(id, DELETE_BY_ID);
    }

    @Override
    protected void updateStatementMapper(SprintEntity sprint, PreparedStatement preparedStatement) throws SQLException {
        createStatementMapper(sprint, preparedStatement);
        preparedStatement.setInt(5, sprint.getId());
    }

    @Override
    protected void createStatementMapper(SprintEntity sprint, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, sprint.getName());
        preparedStatement.setDate(2, Date.valueOf(sprint.getStart()));
        preparedStatement.setDate(3, Date.valueOf(sprint.getEnd()));
        preparedStatement.setString(4, sprint.getDescription());
    }

    @Override
    protected Optional<SprintEntity> mapResultSetToEntity(ResultSet sprint) throws SQLException {
        return Optional.of(SprintEntity.builder().withId(sprint.getInt(1))
                .withName(sprint.getString(2))
                .withStart(sprint.getDate(3).toLocalDate())
                .withEnd(sprint.getDate(4).toLocalDate())
                .withDescription(sprint.getString(5))
                .build());
    }
}
