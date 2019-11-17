package project.service;

import project.domain.Goal;

import java.util.List;

public interface GoalService {
    boolean createGoal(Goal goal);

    Goal showGoalById(Integer id);

    List<Goal> showAllGoals(Integer currentPage, Integer recordsPerPage);

    Integer showNumberOfRows();
}
