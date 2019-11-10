package project.repository.goalDao;

import project.entity.goal.GoalEntity;
import project.repository.CrudRepository;

import java.util.List;

public interface GoalDao extends CrudRepository<Integer, GoalEntity> {
    List<GoalEntity> findByBacklog(Integer id, Integer offset, Integer amount);
}
