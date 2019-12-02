package com.ua.timetracking.service;


import com.ua.timetracking.domain.Story;
import com.ua.timetracking.domain.User;

import java.util.List;

public interface StoryService {
    boolean createStory(Story story);

    List<Story> showStoryByUser(Integer userId, Integer currentPage, Integer recordsPerPage);

    List<Story> showAllStories(Integer currentPage, Integer recordsPerPage);

    List<Story> showStoriesWithoutUser(Integer currentPage, Integer recordsPerPage);

    Integer showNumberOfRows();

    Integer showNumberOfRowsWithoutUser();

    Integer showNumberOfRowsByUserId(Integer id);

    void addStoryToUser(Story story, User user);
}
