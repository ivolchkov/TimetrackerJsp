package com.ua.timetracking.service.mapper;

import com.ua.timetracking.domain.Goal;
import com.ua.timetracking.domain.Story;
import com.ua.timetracking.domain.User;
import com.ua.timetracking.entity.GoalEntity;
import com.ua.timetracking.entity.Status;
import com.ua.timetracking.entity.StoryEntity;
import com.ua.timetracking.entity.UserEntity;
import org.junit.Test;

import java.time.LocalTime;

import static org.junit.Assert.assertEquals;

public class StoryMapperTest {
    private static final User USER = User.builder().withId(1).build();

    private static final UserEntity USER_ENTITY = UserEntity.builder().withId(1).build();

    private static final GoalEntity GOAL_ENTITY = GoalEntity.builder()
            .withId(1)
            .build();

    private static final StoryEntity STORY_ENTITY = getStoryEntity(StoryEntity.builder());

    private static final StoryEntity ENTITY_WITH_ID = getStoryEntity(StoryEntity.builder()
            .withId(1));

    private static final StoryEntity ENTITY_WITH_USER = StoryEntity.builder()
            .withId(1)
            .withUser(USER_ENTITY)
            .build();

    private static final Story DOMAIN = getStory();

    private final StoryMapper storyMapper = new StoryMapper();

    @Test
    public void mapStoryToStoryEntityShouldMapToEntity() {
        StoryEntity actual = storyMapper.mapStoryToStoryEntity(DOMAIN);

        assertEquals(STORY_ENTITY, actual);
    }

    @Test
    public void mapStoryEntityToStoryShouldMapToDomain() {
        Story actual = storyMapper.mapStoryEntityToStory(ENTITY_WITH_ID);

        assertEquals(DOMAIN, actual);
    }

    @Test
    public void mapStoryToStoryEntityShouldMapToEntityWithUser() {
        StoryEntity actual = storyMapper.mapStoryToStoryEntity(DOMAIN, USER);

        assertEquals(ENTITY_WITH_USER, actual);
    }

    private static StoryEntity getStoryEntity(StoryEntity.StoryBuilder builder) {
        return builder
                .withName("Test")
                .withSpentTime(LocalTime.MAX)
                .withDescription("Test description")
                .withStatus(Status.DONE)
                .withGoal(GOAL_ENTITY)
                .build();
    }

    private static Story getStory() {
        return Story.builder()
                .withId(1)
                .withName("Test")
                .withSpentTime(LocalTime.MAX)
                .withDescription("Test description")
                .withStatus(Status.DONE)
                .withGoal(new Goal(1))
                .build();
    }
}