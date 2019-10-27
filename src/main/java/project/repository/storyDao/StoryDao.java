package project.repository.storyDao;

import project.domain.story.Status;
import project.domain.story.Story;
import project.repository.CrudRepository;

import java.util.List;

public interface StoryDao extends CrudRepository<Integer, Story> {
    List<Story> findByNamePattern(String pattern);
    List<Story> findByStatus(Status status);

    void updateUserId(Story story);
    void updateSprintId(Story story);
}
