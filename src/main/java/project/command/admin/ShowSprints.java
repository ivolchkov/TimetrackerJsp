package project.command.admin;

import project.command.Command;
import project.domain.Sprint;
import project.service.SprintService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowSprints implements Command {
    private final SprintService sprintService;

    public ShowSprints(SprintService sprintService) {
        this.sprintService = sprintService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int currentPage = parseParameter(request, "currentPage");
        int recordsPerPage = parseParameter(request, "recordsPerPage");

        List<Sprint> sprints = sprintService.showAllSprints(currentPage, recordsPerPage);

        request.setAttribute("sprints", sprints);

        int rows = sprintService.showNumberOfRows();

        paginating(request, "showSprints", rows, currentPage, recordsPerPage);

        return "showSprints.jsp";
    }

}
