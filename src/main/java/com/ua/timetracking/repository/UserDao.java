package com.ua.timetracking.repository;

import com.ua.timetracking.entity.UserEntity;

import java.util.Optional;

public interface UserDao extends CrudRepository<Integer, UserEntity> {
    Optional<UserEntity> findByEmail(String email);
}
