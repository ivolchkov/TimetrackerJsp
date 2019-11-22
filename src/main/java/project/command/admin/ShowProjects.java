package project.command.admin;

import project.command.Command;
import project.domain.Backlog;
import project.service.BacklogService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowProjects implements Command {
    private final BacklogService backlogService;

    public ShowProjects(BacklogService backlogService) {
        this.backlogService = backlogService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int currentPage = parseParameter(request, "currentPage");
        int recordsPerPage = parseParameter(request, "recordsPerPage");

        List<Backlog> backlogs = backlogService.showAllBacklogs(currentPage, recordsPerPage);

        request.setAttribute("backlogs", backlogs);

        int rows = backlogService.showNumberOfRows();

        paginating(request, "showProjects", rows, currentPage, recordsPerPage);

        return "showProjects.jsp";
    }
}
