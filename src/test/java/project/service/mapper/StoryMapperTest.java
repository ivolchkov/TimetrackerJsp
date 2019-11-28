package project.service.mapper;

import org.junit.Test;
import project.domain.Goal;
import project.domain.Sprint;
import project.domain.Story;
import project.domain.User;
import project.entity.GoalEntity;
import project.entity.SprintEntity;
import project.entity.Status;
import project.entity.StoryEntity;
import project.entity.UserEntity;

import java.time.LocalTime;

import static org.junit.Assert.*;

public class StoryMapperTest {
    private static final User USER = User.builder().withId(1).build();

    private static final Sprint SPRINT = Sprint.builder().withId(1).build();

    private static final UserEntity USER_ENTITY = UserEntity.builder().withId(1).build();

    private static final SprintEntity SPRINT_ENTITY = SprintEntity.builder().withId(1).build();

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

    private static final StoryEntity ENTITY_WITH_SPRINT = StoryEntity.builder()
            .withId(1)
            .withSprint(SPRINT_ENTITY)
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

    @Test
    public void mapStoryToStoryEntityShouldMapToEntityWithSprint() {
        StoryEntity actual = storyMapper.mapStoryToStoryEntity(DOMAIN, SPRINT);

        assertEquals(ENTITY_WITH_SPRINT, actual);
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