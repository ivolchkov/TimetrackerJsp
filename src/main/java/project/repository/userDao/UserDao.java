package project.repository.userDao;

import project.entity.user.UserEntity;
import project.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserDao extends CrudRepository<Integer, UserEntity> {
    Optional<UserEntity> findByEmail(String email);

    List<UserEntity> findByBacklog(Integer id, Integer offset, Integer amount);

    void updateBacklogId(UserEntity user);

    boolean deleteByEmail(String email);
}
