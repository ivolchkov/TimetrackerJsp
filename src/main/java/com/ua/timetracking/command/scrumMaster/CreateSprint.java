package com.ua.timetracking.command.scrumMaster;

import com.ua.timetracking.command.Command;
import com.ua.timetracking.domain.Sprint;
import com.ua.timetracking.service.SprintService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

public class CreateSprint implements Command {
    private final SprintService sprintService;

    public CreateSprint(SprintService sprintService) {
        this.sprintService = sprintService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        LocalDate start = LocalDate.parse(request.getParameter("start"));
        LocalDate end = LocalDate.parse(request.getParameter("end"));

        Sprint sprint = Sprint.builder()
                .withName(name)
                .withStart(start)
                .withEnd(end)
                .withDescription(description)
                .build();

        sprintService.createSprint(sprint);

        return "scrum-master-service.jsp";
    }
}
