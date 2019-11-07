package project.command.admin;

import project.command.Command;
import project.domain.user.User;
import project.service.user.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowUsers implements Command {
    private final UserService userService;

    public ShowUsers(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int currentPage = Integer.parseInt(request.getParameter("currentPage"));
        int recordsPerPage = Integer.parseInt(request.getParameter("recordsPerPage"));

        List<User> users = userService.findAll(currentPage,
                recordsPerPage);

        request.setAttribute("users", users);

        int rows = userService.showNumberOfRows();

        int numberOfPages = rows / recordsPerPage;

        if (numberOfPages % recordsPerPage > 0) {
            numberOfPages += 1;
        }

        request.setAttribute("numberOfPages", numberOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("recordsPerPage", recordsPerPage);

        return "showUsers.jsp";
    }
}
