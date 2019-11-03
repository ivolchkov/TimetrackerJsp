package project.command.user;

import project.command.Command;
import project.context.ApplicationContextInjector;
import project.domain.user.User;
import project.service.user.UserService;

import javax.servlet.http.HttpServletRequest;

public class SignIn implements Command {
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";

    private final UserService userService;

    public SignIn() {
        userService = ApplicationContextInjector.getInstance().getUserService();
    }

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;

        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);

        try {
            userService.login(email, password);
            page = "index.jsp";
        } catch (Exception e) {
            System.out.println("Oppaa " + e.getMessage());
        }

        return page;
    }
}
