package com.ua.timetracking.service;

import com.ua.timetracking.domain.User;

import java.util.List;

public interface UserService {
    boolean register(User user);

    User login(String email, String password);

    List<User> findAll(Integer currentPage, Integer recordsPerPage);

    Integer showNumberOfRows();
}
