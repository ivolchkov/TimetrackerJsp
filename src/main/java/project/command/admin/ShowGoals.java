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

        int numberOfPages = rows / recordsPerPage;

        if (numberOfPages % recordsPerPage > 0) {
            numberOfPages += 1;
        }

        request.setAttribute("command", "showGoals");
        request.setAttribute("numberOfPages", numberOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("recordsPerPage", recordsPerPage);

        return "showGoals.jsp";
    }
}
