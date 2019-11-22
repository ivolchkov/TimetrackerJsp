package project.command.admin;

import project.command.Command;
import project.domain.User;
import project.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowUsers implements Command {
    private final UserService userService;

    public ShowUsers(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int currentPage = parseParameter(request, "currentPage");
        int recordsPerPage = parseParameter(request, "recordsPerPage");;

        List<User> users = userService.findAll(currentPage, recordsPerPage);

        request.setAttribute("users", users);

        int rows = userService.showNumberOfRows();

        paginating(request, "showUsers", rows, currentPage, recordsPerPage);

        return "showUsers.jsp";
    }
}
