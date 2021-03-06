package com.ua.timetracking.command.scrumMaster;

import com.ua.timetracking.command.Command;
import com.ua.timetracking.domain.Backlog;
import com.ua.timetracking.service.BacklogService;

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
