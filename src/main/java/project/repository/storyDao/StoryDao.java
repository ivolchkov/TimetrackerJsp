package project.repository.storyDao;

import project.entity.story.Status;
import project.entity.story.StoryEntity;
import project.repository.CrudRepository;

import java.util.List;

public interface StoryDao extends CrudRepository<Integer, StoryEntity> {
    List<StoryEntity> findByNamePattern(String pattern);

    List<StoryEntity> findByStatus(Status status);

    List<StoryEntity> findByGoal(Integer id);

    List<StoryEntity> findByUser(Integer id);

    List<StoryEntity> findBySprint(Integer id);

    List<StoryEntity> findWithoutUser(Integer offset, Integer amount);

    Integer findAmountOfRowsWithoutUser();

    void updateUserId(StoryEntity story);

    void updateSprintId(StoryEntity story);
}
