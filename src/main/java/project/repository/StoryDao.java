package project.repository;

import project.entity.Status;
import project.entity.StoryEntity;

import java.util.List;

public interface StoryDao extends CrudRepository<Integer, StoryEntity> {
    List<StoryEntity> findByStatus(Status status, Integer offset, Integer amount);

    List<StoryEntity> findByGoal(Integer id, Integer offset, Integer amount);

    List<StoryEntity> findByUser(Integer id, Integer offset, Integer amount);

    List<StoryEntity> findBySprint(Integer id, Integer offset, Integer amount);

    List<StoryEntity> findWithoutUser(Integer offset, Integer amount);

    Integer findAmountOfRowsWithoutUser();

    Integer findAmountOfRowsByUser(Integer id);

    void updateUserId(StoryEntity story);
}
