package project.command.scrumMaster;

import project.command.Command;
import project.domain.goal.Goal;
import project.service.goal.GoalService;

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
