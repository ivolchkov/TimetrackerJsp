package com.ua.timetracking.command.admin;

import com.ua.timetracking.command.Command;
import com.ua.timetracking.domain.Goal;
import com.ua.timetracking.service.GoalService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowGoals implements Command {
    private final GoalService goalService;

    public ShowGoals(GoalService goalService) {
        this.goalService = goalService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int currentPage = parseParameter(request, "currentPage");
        int recordsPerPage = parseParameter(request, "recordsPerPage");

        List<Goal> goals = goalService.showAllGoals(currentPage, recordsPerPage);

        request.setAttribute("goals", goals);

        int rows = goalService.showNumberOfRows();

        paginating(request, "showGoals", rows, currentPage, recordsPerPage);

        return "showGoals.jsp";
    }
}
