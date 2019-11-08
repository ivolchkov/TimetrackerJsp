package project.command.admin;

import project.command.Command;
import project.domain.backlog.Backlog;
import project.service.backlog.BacklogService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowProjects implements Command {
    private final BacklogService backlogService;

    public ShowProjects(BacklogService backlogService) {
        this.backlogService = backlogService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int currentPage = Integer.parseInt(request.getParameter("currentPage"));
        int recordsPerPage = Integer.parseInt(request.getParameter("recordsPerPage"));

        List<Backlog> backlogs = backlogService.showAllBacklogs(currentPage, recordsPerPage);

        request.setAttribute("backlogs", backlogs);

        int rows = backlogService.showNumberOfRows();

        int numberOfPages = rows / recordsPerPage;

        if (numberOfPages % recordsPerPage > 0) {
            numberOfPages += 1;
        }

        request.setAttribute("command", "showProjects");
        request.setAttribute("numberOfPages", numberOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("recordsPerPage", recordsPerPage);

        return "showProjects.jsp";
    }
}
