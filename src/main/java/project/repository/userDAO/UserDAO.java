package project.repository.userDAO;

import project.domain.activity.Activity;
import project.domain.user.User;
import project.repository.CRUDRepository;

import java.util.List;
import java.util.Optional;

public interface UserDAO extends CRUDRepository<Long, User> {
    Optional<User> findByEmail(String email);

    boolean deleteByEmail(String email);
}
