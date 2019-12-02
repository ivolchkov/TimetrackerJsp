package com.ua.timetracking.command.scrumMaster;

import com.ua.timetracking.command.Command;
import com.ua.timetracking.domain.Backlog;
import com.ua.timetracking.domain.Goal;
import com.ua.timetracking.service.BacklogService;
import com.ua.timetracking.service.GoalService;

import javax.servlet.http.HttpServletRequest;

public class AddGoal implements Command {
    private final BacklogService backlogService;
    private final GoalService goalService;

    public AddGoal(BacklogService backlogService, GoalService goalService) {
        this.backlogService = backlogService;
        this.goalService = goalService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String name = request.getParameter("name");
        Integer id = parseParameter(request, "backlogId");

        Backlog backlog = backlogService.showBacklogById(id);
        Goal goal = new Goal(name, backlog);

        goalService.createGoal(goal);

        return "scrum-master-service.jsp";
    }
}
