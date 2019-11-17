package project.service;


import project.domain.Sprint;
import project.domain.Story;
import project.domain.User;
import project.entity.Status;

import java.util.List;

public interface StoryService {
    boolean createStory(Story story);

    List<Story> showStoryByStatus(Status status, Integer currentPage, Integer recordsPerPage);

    List<Story> showStoryByGoal(Integer goalId, Integer currentPage, Integer recordsPerPage);

    List<Story> showStoryByUser(Integer userId, Integer currentPage, Integer recordsPerPage);

    List<Story> showStoryBySprint(Integer sprintId, Integer currentPage, Integer recordsPerPage);

    List<Story> showAllStories(Integer currentPage, Integer recordsPerPage);

    List<Story> showStoriesWithoutUser(Integer currentPage, Integer recordsPerPage);

    Integer showNumberOfRows();

    Integer showNumberOfRowsWithoutUser();

    Integer showNumberOfRowsByUserId(Integer id);

    void addStoryToUser(Story story, User user);

    void addStoryToSprint(Story story, Sprint sprint);
}
