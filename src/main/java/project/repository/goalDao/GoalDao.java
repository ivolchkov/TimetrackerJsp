package project.repository.goalDao;

import project.domain.goal.Goal;
import project.repository.CrudRepository;

import java.util.List;

public interface GoalDao extends CrudRepository<Integer, Goal> {
    List<Goal> findByBacklog(Integer id);
}
