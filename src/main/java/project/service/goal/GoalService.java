package project.service.goal;

import project.domain.goal.Goal;

import java.util.List;

public interface GoalService {
    boolean createGoal(Goal goal);

    List<Goal> showAllGoals(Integer currentPage, Integer recordsPerPage);

    Integer showNumberOfRows();
}
