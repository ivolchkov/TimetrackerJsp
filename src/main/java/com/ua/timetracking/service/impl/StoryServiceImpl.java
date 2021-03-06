package com.ua.timetracking.service.impl;

import com.ua.timetracking.domain.Story;
import com.ua.timetracking.domain.User;
import com.ua.timetracking.entity.StoryEntity;
import com.ua.timetracking.exception.InvalidEntityCreation;
import com.ua.timetracking.exception.InvalidEntityUpdating;
import com.ua.timetracking.exception.InvalidPaginatingException;
import com.ua.timetracking.repository.StoryDao;
import com.ua.timetracking.service.StoryService;
import com.ua.timetracking.service.mapper.StoryMapper;
import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class StoryServiceImpl implements StoryService {
    private static final Logger LOGGER = Logger.getLogger(StoryServiceImpl.class);

    private final StoryDao storyDao;
    private final StoryMapper mapper;

    public StoryServiceImpl(StoryDao storyDao, StoryMapper mapper) {
        this.storyDao = storyDao;
        this.mapper = mapper;
    }

    @Override
    public boolean createStory(Story story) {
        if (Objects.isNull(story)) {
            LOGGER.warn("Story is not valid");
            throw new InvalidEntityCreation("Story is not valid");
        }

        return storyDao.save(mapper.mapStoryToStoryEntity(story));
    }

    @Override
    public List<Story> showStoryByUser(Integer userId, Integer currentPage, Integer recordsPerPage) {
        if (Objects.isNull(userId)) {
            LOGGER.warn("User id is not valid");
            throw new IllegalArgumentException("User id is not valid");
        }
        paginatingValidation(currentPage, recordsPerPage);

        Integer offset = currentPage * recordsPerPage - recordsPerPage;
        List<StoryEntity> result = storyDao.findByUser(userId, offset, recordsPerPage);

        return listMapping(result);
    }

    @Override
    public List<Story> showAllStories(Integer currentPage, Integer recordsPerPage) {
        paginatingValidation(currentPage, recordsPerPage);

        Integer offset = currentPage * recordsPerPage - recordsPerPage;
        List<StoryEntity> result = storyDao.findAll(offset, recordsPerPage);

        return listMapping(result);
    }

    @Override
    public List<Story> showStoriesWithoutUser(Integer currentPage, Integer recordsPerPage) {
        paginatingValidation(currentPage, recordsPerPage);

        Integer offset = currentPage * recordsPerPage - recordsPerPage;
        List<StoryEntity> result = storyDao.findWithoutUser(offset, recordsPerPage);

        return listMapping(result);
    }

    @Override
    public Integer showNumberOfRows() {
        return storyDao.findAmountOfRows();
    }

    @Override
    public Integer showNumberOfRowsWithoutUser() {
        return storyDao.findAmountOfRowsWithoutUser();
    }

    @Override
    public Integer showNumberOfRowsByUserId(Integer id) {
        return storyDao.findAmountOfRowsByUser(id);
    }

    @Override
    public void addStoryToUser(Story story, User user) {
        if (Objects.isNull(story) || Objects.isNull(user)) {
            LOGGER.warn("Invalid story updating");
            throw new InvalidEntityUpdating("Invalid story updating");
        }

        storyDao.updateUserId(mapper.mapStoryToStoryEntity(story, user));
    }

    private void paginatingValidation(Integer currentPage, Integer recordsPerPage) {
        if (currentPage <= 0 || recordsPerPage <= 0) {
            LOGGER.error("Invalid number of current page or records per page");
            throw new InvalidPaginatingException("Invalid number of current page or records per page");
        }
    }

    private List<Story> listMapping(List<StoryEntity> result) {
        return result.isEmpty() ? Collections.emptyList() :
                result.stream()
                        .map(mapper::mapStoryEntityToStory)
                        .collect(Collectors.toList());
    }
}
