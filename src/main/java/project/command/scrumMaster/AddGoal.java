package project.command.scrumMaster;

import project.command.Command;
import project.domain.backlog.Backlog;
import project.domain.goal.Goal;
import project.service.backlog.BacklogService;
import project.service.goal.GoalService;

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
        Integer id = Integer.parseInt(request.getParameter("backlogId"));

        Backlog backlog = backlogService.showBacklogById(id);
        Goal goal = new Goal(name, backlog);

        goalService.createGoal(goal);

        return "scrum-master-service.jsp";
    }
}
