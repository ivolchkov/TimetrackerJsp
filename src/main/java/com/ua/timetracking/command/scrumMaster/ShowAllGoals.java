package com.ua.timetracking.command.scrumMaster;

import com.ua.timetracking.command.Command;
import com.ua.timetracking.domain.Goal;
import com.ua.timetracking.service.GoalService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowAllGoals implements Command {
    private final GoalService goalService;

    public ShowAllGoals(GoalService goalService) {
        this.goalService = goalService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int currentPage = 1;
        int recordsPerPage = goalService.showNumberOfRows();

        List<Goal> goals = goalService.showAllGoals(currentPage, recordsPerPage);

        request.setAttribute("goals", goals);

        return "createStory.jsp";
    }
}
