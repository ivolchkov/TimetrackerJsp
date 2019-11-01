package project.service.story;

import org.apache.log4j.Logger;
import project.domain.sprint.Sprint;
import project.domain.story.Story;
import project.domain.user.User;
import project.entity.story.Status;
import project.exception.InvalidEntityCreation;
import project.exception.InvalidEntityUpdating;
import project.repository.storyDao.StoryDao;
import project.service.mapper.StoryMapper;

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
        if (Objects.isNull(story) ) {
            LOGGER.warn("Story is not valid");
            throw new InvalidEntityCreation("Story is not valid");
        }

        return storyDao.save(mapper.mapStoryToStoryEntity(story));
    }

    @Override
    public List<Story> showStoryByStatus(Status status) {
        validateParam(status);

        return storyDao.findByStatus(status).stream()
                .map(mapper::mapStoryEntityToStory)
                .collect(Collectors.toList());
    }

    @Override
    public List<Story> showStoryByGoal(Integer goalId) {
        validateParam(goalId);

        return storyDao.findByGoal(goalId).stream()
                .map(mapper::mapStoryEntityToStory)
                .collect(Collectors.toList());
    }

    @Override
    public List<Story> showStoryByUser(Integer userId) {
        validateParam(userId);

        return storyDao.findByUser(userId).stream()
                .map(mapper::mapStoryEntityToStory)
                .collect(Collectors.toList());
    }

    @Override
    public List<Story> showStoryBySprint(Integer sprintId) {
        validateParam(sprintId);

        return storyDao.findBySprint(sprintId).stream()
                .map(mapper::mapStoryEntityToStory)
                .collect(Collectors.toList());
    }

    @Override
    public void addStoryToUser(Story story, User user) {
        validateUpdateParam(story, user);

        storyDao.updateUserId(mapper.mapStoryToStoryEntity(story, user));
    }

    @Override
    public void addStoryToSprint(Story story, Sprint sprint) {
        validateUpdateParam(story, sprint);

        storyDao.updateSprintId(mapper.mapStoryToStoryEntity(story, sprint));
    }

    private <T> void validateParam(T param) {
        if (Objects.isNull(param)) {
            LOGGER.warn("Parameter is not valid");
            throw new IllegalArgumentException("Parameter is not valid");
        }
    }

    private <T> void validateUpdateParam(Story story, T param) {
        if (Objects.isNull(story) || Objects.isNull(param) ) {
            LOGGER.warn("Invalid story updating");
            throw new InvalidEntityUpdating("Invalid story updating");
        }
    }
}