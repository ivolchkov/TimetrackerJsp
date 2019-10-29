package project.service.user;

import project.domain.user.User;

public interface UserService {
    boolean register(User user);
    boolean login(String email, String password);
}
