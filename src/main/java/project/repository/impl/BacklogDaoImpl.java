package project.repository.impl;

import project.entity.BacklogEntity;
import project.repository.AbstractDao;
import project.repository.BacklogDao;
import project.repository.connector.WrapperConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class BacklogDaoImpl extends AbstractDao<BacklogEntity> implements BacklogDao {
    private static final String INSERT_BACKLOG = "INSERT INTO timetracking.backlogs(backlog_project_name, backlog_description) VALUES(?, ?)";
    private static final String FIND_BY_ID = "SELECT * FROM timetracking.backlogs WHERE backlog_id = ?";
    private static final String FIND_ALL_BACKLOGS = "SELECT * FROM timetracking.backlogs LIMIT ?, ?";
    private static final String FIND_ALL_ROWS = "SELECT COUNT(backlog_id) FROM timetracking.backlogs";
    private static final String UPDATE_BACKLOG = "UPDATE timetracking.backlogs SET backlog_project_name = ?, backlog_description = ? WHERE backlog_id = ?";

    public BacklogDaoImpl(WrapperConnector connector) {
        super(INSERT_BACKLOG, FIND_BY_ID, FIND_ALL_BACKLOGS, FIND_ALL_ROWS, UPDATE_BACKLOG, connector);
    }

    @Override
    protected void updateStatementMapper(BacklogEntity backlog, PreparedStatement preparedStatement) throws SQLException {
        createStatementMapper(backlog, preparedStatement);
        preparedStatement.setInt(3, backlog.getId());
    }

    @Override
    protected void createStatementMapper(BacklogEntity backlog, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setNString(1, backlog.getProjectName());
        preparedStatement.setNString(2, backlog.getDescription());
    }

    @Override
    protected Optional<BacklogEntity> mapResultSetToEntity(ResultSet backlog) throws SQLException {
        return Optional.of(BacklogEntity.builder()
                .withId(backlog.getInt(1))
                .withProjectName(backlog.getNString(2))
                .withDescription(backlog.getNString(3))
                .build());
    }
}
