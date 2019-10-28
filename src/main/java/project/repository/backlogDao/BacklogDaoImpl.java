package project.repository.backlogDao;

import project.domain.backlog.Backlog;
import project.repository.AbstractDao;
import project.repository.connector.WrapperConnector;

import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class BacklogDaoImpl extends AbstractDao<Backlog> implements BacklogDao {
    private static final String INSERT_BACKLOG = "INSERT INTO timetracking.backlogs(backlog_project_name, backlog_description) VALUES(?, ?)";
    private static final String FIND_BY_ID = "SELECT * FROM timetracking.backlogs WHERE backlog_id = ?";
    private static final String FIND_ALL_BACKLOGS = "SELECT * FROM timetracking.backlogs";
    private static final String UPDATE_BACKLOG = "UPDATE timetracking.backlogs SET backlog_project_name = ?, backlog_description = ? WHERE backlog_id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM timetracking.backlogs WHERE backlog_id = ?";

    public BacklogDaoImpl(WrapperConnector connector) {
        super(connector);
    }

    @Override
    public boolean save(Backlog backlog) {
        return save(backlog, INSERT_BACKLOG);
    }

    @Override
    public Optional<Backlog> findById(Integer id) {
        return findById(id, FIND_BY_ID);
    }

    @Override
    public List<Backlog> findAll() {
        return findAll(FIND_ALL_BACKLOGS);
    }

    @Override
    public void update(Backlog backlog) {
        update(backlog, UPDATE_BACKLOG);
    }

    @Override
    public boolean deleteById(Integer id) {
        return deleteById(id, DELETE_BY_ID);
    }

    @Override
    protected int statementMapper(Backlog backlog, PreparedStatement preparedStatement) throws SQLException {
        ParameterMetaData parameterMetaData = preparedStatement.getParameterMetaData();
        int parameterCount = parameterMetaData.getParameterCount();

        if (parameterCount == 2) {
            defaultStatementMap(backlog, preparedStatement);
        } else if (parameterCount == 3) {
            defaultStatementMap(backlog, preparedStatement);
            preparedStatement.setInt(3, backlog.getId());
        }

        return preparedStatement.executeUpdate();
    }

    private void defaultStatementMap(Backlog backlog, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, backlog.getProjectName());
        preparedStatement.setString(2, backlog.getDescription());
    }

    @Override
    protected Optional<Backlog> mapResultSetToEntity(ResultSet backlog) throws SQLException {
        Integer id = backlog.getInt(1);
        String projectName = backlog.getString(2);
        String description = backlog.getString(3);

        return Optional.of(new Backlog(id, projectName, description));
    }
}
