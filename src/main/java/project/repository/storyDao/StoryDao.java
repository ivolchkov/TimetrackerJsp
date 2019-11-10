package project.repository.storyDao;

import project.entity.story.Status;
import project.entity.story.StoryEntity;
import project.repository.CrudRepository;

import java.util.List;

public interface StoryDao extends CrudRepository<Integer, StoryEntity> {
    List<StoryEntity> findByNamePattern(String pattern, Integer offset, Integer amount);

    List<StoryEntity> findByStatus(Status status, Integer offset, Integer amount);

    List<StoryEntity> findByGoal(Integer id, Integer offset, Integer amount);

    List<StoryEntity> findByUser(Integer id, Integer offset, Integer amount);

    List<StoryEntity> findBySprint(Integer id, Integer offset, Integer amount);

    List<StoryEntity> findWithoutUser(Integer offset, Integer amount);

    Integer findAmountOfRowsWithoutUser();

    Integer findAmountOfRowsByUser(Integer id);

    void updateUserId(StoryEntity story);

    void updateSprintId(StoryEntity story);
}
