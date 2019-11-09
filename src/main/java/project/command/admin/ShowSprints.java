package project.command.admin;

import project.command.Command;
import project.domain.sprint.Sprint;
import project.service.sprint.SprintService;

import javax.servlet.http.*;
import java.util.*;

public class ShowSprints implements Command {
    private final SprintService sprintService;

    public ShowSprints(SprintService sprintService) {
        this.sprintService = sprintService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int currentPage = Integer.parseInt(request.getParameter("currentPage"));
        int recordsPerPage = Integer.parseInt(request.getParameter("recordsPerPage"));

        List<Sprint> sprints = sprintService.showAllSprints(currentPage, recordsPerPage);

        request.setAttribute("sprints", sprints);

        int rows = sprintService.showNumberOfRows();

        paginating(request, "showSprints", rows, currentPage, recordsPerPage);

        return "showSprints.jsp";
    }

}
