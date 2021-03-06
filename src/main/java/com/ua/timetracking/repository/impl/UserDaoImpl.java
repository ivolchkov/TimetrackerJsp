package com.ua.timetracking.repository.impl;

import org.apache.log4j.Logger;
import com.ua.timetracking.entity.Role;
import com.ua.timetracking.entity.UserEntity;
import com.ua.timetracking.exception.DatabaseRuntimeException;
import com.ua.timetracking.repository.AbstractDao;
import com.ua.timetracking.repository.UserDao;
import com.ua.timetracking.repository.connector.WrapperConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserDaoImpl extends AbstractDao<UserEntity> implements UserDao {
    private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);

    private static final String INSERT_USER = "INSERT INTO timetracking.users(user_name, user_surname, user_email, user_password, user_role) VALUES(?, ?, ?, ?, ?)";
    private static final String FIND_BY_ID = "SELECT * FROM timetracking.users WHERE user_id = ?";
    private static final String FIND_ALL_USERS = "SELECT * FROM timetracking.users LIMIT ?, ?";
    private static final String FIND_ALL_ROWS = "SELECT COUNT(user_id) FROM timetracking.users";
    private static final String FIND_BY_EMAIL = "SELECT * FROM timetracking.users WHERE user_email = ?";
    private static final String UPDATE_USER = "UPDATE timetracking.users SET user_name = ?, user_surname = ?, user_email = ?, user_password = ?, user_role = ?, backlog_id = ? WHERE user_id = ?";

    public UserDaoImpl(WrapperConnector connector) {
        super(INSERT_USER, FIND_BY_ID, FIND_ALL_USERS, FIND_ALL_ROWS, UPDATE_USER, connector);
    }

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_EMAIL)) {
            preparedStatement.setString(1, email);

            ResultSet user = preparedStatement.executeQuery();

            return user.next() ? mapResultSetToEntity(user) : Optional.empty();
        } catch (SQLException e) {
            LOGGER.error("Invalid user search" , e);
            throw new DatabaseRuntimeException("Invalid user search", e);
        }
    }

    @Override
    protected Optional<UserEntity> mapResultSetToEntity(ResultSet user) throws SQLException {
        String userRole = user.getString(6);
        Role role = userRole.equals("Admin") ? Role.ADMIN :
                (userRole.equals("Developer") ? Role.DEVELOPER : Role.SCRUM_MASTER);

        return Optional.ofNullable(UserEntity.builder()
                .withId(user.getInt(1))
                .withName(user.getNString(2))
                .withSurname(user.getNString(3))
                .withEmail(user.getString(4))
                .withPassword(user.getString(5))
                .withRole(role)
                .build());
    }

    @Override
    protected void updateStatementMapper(UserEntity user, PreparedStatement preparedStatement) throws SQLException {
        createStatementMapper(user, preparedStatement);
        preparedStatement.setInt(6, user.getBacklog().getId());
        preparedStatement.setInt(7, user.getId());
    }

    @Override
    protected void createStatementMapper(UserEntity user, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setNString(1, user.getName());
        preparedStatement.setNString(2, user.getSurname());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setString(4, user.getPassword());
        preparedStatement.setString(5, user.getRole().getDescription());
    }

}
