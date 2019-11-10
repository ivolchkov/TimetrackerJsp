package project.command.scrumMaster;

import project.command.Command;
import project.domain.backlog.Backlog;
import project.service.backlog.BacklogService;

import javax.servlet.http.HttpServletRequest;

public class CreateProject implements Command {
    private final BacklogService backlogService;

    public CreateProject(BacklogService backlogService) {
        this.backlogService = backlogService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String projectName = request.getParameter("projectName");
        String description = request.getParameter("description");

        Backlog backlog = new Backlog(projectName, description);

        backlogService.createBacklog(backlog);

        return "scrum-master-service.jsp";
    }
}
