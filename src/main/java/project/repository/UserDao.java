package project.repository;

import project.entity.UserEntity;

import java.util.Optional;

public interface UserDao extends CrudRepository<Integer, UserEntity> {
    Optional<UserEntity> findByEmail(String email);
}
