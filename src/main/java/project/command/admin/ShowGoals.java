package project.command.admin;

import project.command.Command;
import project.domain.goal.Goal;
import project.service.goal.GoalService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowGoals implements Command {
    private final GoalService goalService;

    public ShowGoals(GoalService goalService) {
        this.goalService = goalService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int currentPage = Integer.parseInt(request.getParameter("currentPage"));
        int recordsPerPage = Integer.parseInt(request.getParameter("recordsPerPage"));

        List<Goal> goals = goalService.showAllGoals(currentPage, recordsPerPage);

        request.setAttribute("goals", goals);

        int rows = goalService.showNumberOfRows();

        paginating(request, "showGoals", rows, currentPage, recordsPerPage);

        return "showGoals.jsp";
    }
}
