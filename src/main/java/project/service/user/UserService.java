package project.service.user;

import project.domain.backlog.Backlog;
import project.domain.user.User;

import java.util.List;

public interface UserService {
    boolean register(User user);

    User login(String email, String password);

    List<User> findTeam(Integer backlogId);

    List<User> findAll();

    void addProjectToUser(User user, Backlog backlog);
}
