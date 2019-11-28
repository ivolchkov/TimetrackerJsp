package project.repository;

import project.entity.UserEntity;

import java.util.Optional;

public interface UserDao {
    Optional<UserEntity> findByEmail(String email);
}
