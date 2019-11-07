package project.command.user;

import project.command.Command;
import project.domain.user.User;
import project.entity.user.Role;
import project.service.user.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class SignUp implements Command {
    private final UserService userService;

    public SignUp(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String repeatedPassword = request.getParameter("repeatedPassword");
        Role role = Role.valueOf(request.getParameter("role"));

        if (!Objects.equals(password, repeatedPassword)) {
            return "register.jsp";
        }

        User user = User.builder()
                .withName(name)
                .withSurname(surname)
                .withEmail(email)
                .withPassword(password)
                .withRole(role)
                .build();

        userService.register(user);

        return "index.jsp";
    }
}
