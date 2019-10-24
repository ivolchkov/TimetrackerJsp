package project.repository;

import project.domain.activity.Activity;
import project.domain.user.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO extends CRUDRepository<User> {
    Optional<User> findByEmail(String email);
    List<Activity> findActivities(Long id);

    boolean deleteByEmail(String email);
}
