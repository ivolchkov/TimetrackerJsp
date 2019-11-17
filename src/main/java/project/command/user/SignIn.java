package project.command.user;

import project.command.Command;
import project.domain.User;
import project.entity.Role;
import project.service.UserService;

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
        String page;
        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);

        User user = userService.login(email, password);
        request.getSession().setAttribute("user", user);
        Role role = user.getRole();

        switch (role) {
            case ADMIN:
                page = "admin-service.jsp";
                break;
            case DEVELOPER:
                page = "developer-service.jsp";
                break;
            case SCRUM_MASTER:
                page = "scrum-master-service.jsp";
                break;
            default:
                page = "index.jsp";
        }

        return page;
    }
}
