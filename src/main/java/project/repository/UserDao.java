package project.repository;

import project.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserDao extends CrudRepository<Integer, UserEntity> {
    Optional<UserEntity> findByEmail(String email);

    List<UserEntity> findByBacklog(Integer id, Integer offset, Integer amount);

    void updateBacklogId(UserEntity user);

}
