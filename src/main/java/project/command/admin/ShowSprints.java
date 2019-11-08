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

        int numberOfPages = rows / recordsPerPage;

        if (numberOfPages % recordsPerPage > 0) {
            numberOfPages += 1;
        }

        request.setAttribute("command", "showSprints");
        request.setAttribute("numberOfPages", numberOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("recordsPerPage", recordsPerPage);

        return "showSprints.jsp";
    }

}
