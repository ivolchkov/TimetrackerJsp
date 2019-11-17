package project.service;

import project.domain.User;

import java.util.List;

public interface UserService {
    boolean register(User user);

    User login(String email, String password);

    List<User> findAll(Integer currentPage, Integer recordsPerPage);

    Integer showNumberOfRows();
}
