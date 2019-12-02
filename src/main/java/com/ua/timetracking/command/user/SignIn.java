package com.ua.timetracking.command.user;

import com.ua.timetracking.command.Command;
import com.ua.timetracking.domain.User;
import com.ua.timetracking.entity.Role;
import com.ua.timetracking.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class SignIn implements Command {
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";

    private final UserService userService;

    public SignIn(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);

        User user = userService.login(email, password);
        request.getSession().setAttribute("user", user);
        Role role = user.getRole();

        switch (role) {
            case ADMIN:
                return "admin-service.jsp";
            case DEVELOPER:
                return "developer-service.jsp";
            case SCRUM_MASTER:
                return "scrum-master-service.jsp";
            default:
                return "index.jsp";
        }
    }
}
