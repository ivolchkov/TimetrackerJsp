package project.repository.userDao;

import project.domain.user.User;
import project.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserDao extends CrudRepository<Integer, User> {
    Optional<User> findByEmail(String email);
    List<User> findByBacklog(Integer id);

    boolean deleteByEmail(String email);
}
