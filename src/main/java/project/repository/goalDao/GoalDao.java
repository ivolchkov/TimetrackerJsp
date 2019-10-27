package project.repository.goalDao;

import project.domain.goal.Goal;
import project.repository.CrudRepository;

public interface GoalDao extends CrudRepository<Integer, Goal> {
}
