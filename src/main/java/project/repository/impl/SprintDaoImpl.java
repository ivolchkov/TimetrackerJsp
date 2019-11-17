package project.repository.impl;

import project.entity.SprintEntity;
import project.repository.AbstractDao;
import project.repository.SprintDao;
import project.repository.connector.WrapperConnector;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class SprintDaoImpl extends AbstractDao<SprintEntity> implements SprintDao {
    private static final String INSERT_SPRINT = "INSERT INTO timetracking.sprints(sprint_name, sprint_start, sprint_end, sprint_description) VALUES(?, ?, ?, ?)";
    private static final String FIND_BY_ID = "SELECT * FROM timetracking.sprints WHERE sprint_id = ?";
    private static final String FIND_ALL_ROWS = "SELECT COUNT(sprint_id) FROM timetracking.sprints";
    private static final String FIND_ALL_SPRINTS = "SELECT * FROM timetracking.sprints LIMIT ?, ?";
    private static final String UPDATE_SPRINT = "UPDATE timetracking.sprints SET sprint_name = ?, sprint_start = ?, sprint_end = ?, sprint_description = ? WHERE sprint_id = ?";


    public SprintDaoImpl(WrapperConnector connector) {
        super(INSERT_SPRINT, FIND_BY_ID, FIND_ALL_ROWS, FIND_ALL_SPRINTS, UPDATE_SPRINT, connector);
    }

    @Override
    protected void updateStatementMapper(SprintEntity sprint, PreparedStatement preparedStatement) throws SQLException {
        createStatementMapper(sprint, preparedStatement);
        preparedStatement.setInt(5, sprint.getId());
    }

    @Override
    protected void createStatementMapper(SprintEntity sprint, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setNString(1, sprint.getName());
        preparedStatement.setDate(2, Date.valueOf(sprint.getStart()));
        preparedStatement.setDate(3, Date.valueOf(sprint.getEnd()));
        preparedStatement.setNString(4, sprint.getDescription());
    }

    @Override
    protected Optional<SprintEntity> mapResultSetToEntity(ResultSet sprint) throws SQLException {
        return Optional.of(SprintEntity.builder().withId(sprint.getInt(1))
                .withName(sprint.getNString(2))
                .withStart(sprint.getDate(3).toLocalDate())
                .withEnd(sprint.getDate(4).toLocalDate())
                .withDescription(sprint.getNString(5))
                .build());
    }
}
