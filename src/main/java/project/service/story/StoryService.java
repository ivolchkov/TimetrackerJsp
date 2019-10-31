package project.service.story;


import project.domain.sprint.Sprint;
import project.domain.story.Story;
import project.domain.user.User;
import project.entity.story.Status;

import java.util.List;

public interface StoryService {
    boolean createStory(Story story);

    List<Story> showStoryByStatus(Status status);

    List<Story> showStoryByGoal(Integer goalId);

    List<Story> showStoryByUser(Integer userId);

    List<Story> showStoryBySprint(Integer sprintId);

    void addStoryToUser(Story story, User user);

    void addStoryToSprint(Story story, Sprint sprint);
}
