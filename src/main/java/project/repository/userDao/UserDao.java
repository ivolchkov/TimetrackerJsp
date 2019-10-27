package project.repository.userDao;

import project.domain.user.User;
import project.repository.CrudRepository;

import java.util.Optional;

public interface UserDao extends CrudRepository<Integer, User> {
    Optional<User> findByEmail(String email);

    boolean deleteByEmail(String email);
}
