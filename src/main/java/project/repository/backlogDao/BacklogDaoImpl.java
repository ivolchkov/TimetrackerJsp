package project.repository.backlogDao;

import project.entity.backlog.BacklogEntity;
import project.repository.AbstractDao;
import project.repository.connector.WrapperConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class BacklogDaoImpl extends AbstractDao<BacklogEntity> implements BacklogDao {
    private static final String INSERT_BACKLOG = "INSERT INTO timetracking.backlogs(backlog_project_name, backlog_description) VALUES(?, ?)";
    private static final String FIND_BY_ID = "SELECT * FROM timetracking.backlogs WHERE backlog_id = ?";
    private static final String FIND_ALL_BACKLOGS = "SELECT * FROM timetracking.backlogs";
    private static final String UPDATE_BACKLOG = "UPDATE timetracking.backlogs SET backlog_project_name = ?, backlog_description = ? WHERE backlog_id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM timetracking.backlogs WHERE backlog_id = ?";

    public BacklogDaoImpl(WrapperConnector connector) {
        super(connector);
    }

    @Override
    public boolean save(BacklogEntity backlog) {
        return save(backlog, INSERT_BACKLOG);
    }

    @Override
    public Optional<BacklogEntity> findById(Integer id) {
        return findById(id, FIND_BY_ID);
    }

    @Override
    public List<BacklogEntity> findAll() {
        return findAll(FIND_ALL_BACKLOGS);
    }

    @Override
    public void update(BacklogEntity backlog) {
        update(backlog, UPDATE_BACKLOG);
    }

    @Override
    public boolean deleteById(Integer id) {
        return deleteById(id, DELETE_BY_ID);
    }

    @Override
    protected void updateStatementMapper(BacklogEntity backlog, PreparedStatement preparedStatement) throws SQLException {
        createStatementMapper(backlog, preparedStatement);
        preparedStatement.setInt(3, backlog.getId());
    }

    @Override
    protected void createStatementMapper(BacklogEntity backlog, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, backlog.getProjectName());
        preparedStatement.setString(2, backlog.getDescription());
    }

    @Override
    protected Optional<BacklogEntity> mapResultSetToEntity(ResultSet backlog) throws SQLException {
        return Optional.of(BacklogEntity.builder()
                .withId(backlog.getInt(1))
                .withProjectName(backlog.getString(2))
                .withDescription(backlog.getString(3))
                .build());
    }
}
