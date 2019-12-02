package com.ua.timetracking.command.scrumMaster;

import com.ua.timetracking.command.Command;
import com.ua.timetracking.domain.Backlog;
import com.ua.timetracking.service.BacklogService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowAllBacklogs implements Command {
    private final BacklogService backlogService;

    public ShowAllBacklogs(BacklogService backlogService) {
        this.backlogService = backlogService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int currentPage = 1;
        int recordsPerPage = backlogService.showNumberOfRows();

        List<Backlog> backlogs = backlogService.showAllBacklogs(currentPage, recordsPerPage);

        request.setAttribute("backlogs", backlogs);

        return "createGoal.jsp";
    }
}
