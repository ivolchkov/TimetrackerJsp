package project.service.mapper;

import org.junit.Test;
import project.domain.goal.Goal;
import project.domain.sprint.Sprint;
import project.domain.story.Story;
import project.domain.user.User;
import project.entity.goal.GoalEntity;
import project.entity.sprint.SprintEntity;
import project.entity.story.Status;
import project.entity.story.StoryEntity;
import project.entity.user.UserEntity;

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

    private static final StoryEntity ENTITY = StoryEntity.builder()
            .withName("Test")
            .withSpentTime(LocalTime.MAX)
            .withDescription("Test description")
            .withStatus(Status.DONE)
            .withGoal(GOAL_ENTITY)
            .build();

    private static final StoryEntity ENTITY_WITH_ID = StoryEntity.builder()
            .withId(1)
            .withName("Test")
            .withSpentTime(LocalTime.MAX)
            .withDescription("Test description")
            .withStatus(Status.DONE)
            .withGoal(GOAL_ENTITY)
            .build();

    private static final StoryEntity ENTITY_WITH_USER = StoryEntity.builder()
            .withId(1)
            .withUser(USER_ENTITY)
            .build();

    private static final StoryEntity ENTITY_WITH_SPRINT = StoryEntity.builder()
            .withId(1)
            .withSprint(SPRINT_ENTITY)
            .build();

    private static final Story DOMAIN = Story.builder()
            .withId(1)
            .withName("Test")
            .withSpentTime(LocalTime.MAX)
            .withDescription("Test description")
            .withStatus(Status.DONE)
            .withGoal(new Goal(1))
            .build();

    private static final StoryMapper MAPPER = new StoryMapper();

    @Test
    public void mapStoryToStoryEntityShouldMapToEntity() {
        StoryEntity actual = MAPPER.mapStoryToStoryEntity(DOMAIN);

        assertEquals(ENTITY, actual);
    }

    @Test
    public void mapStoryEntityToStoryShouldMapToDomain() {
        Story actual = MAPPER.mapStoryEntityToStory(ENTITY_WITH_ID);

        assertEquals(DOMAIN, actual);
    }

    @Test
    public void mapStoryToStoryEntityShouldMapToEntityWithUser() {
        StoryEntity actual = MAPPER.mapStoryToStoryEntity(DOMAIN, USER);

        assertEquals(ENTITY_WITH_USER, actual);
    }

    @Test
    public void mapStoryToStoryEntityShouldMapToEntityWithSprint() {
        StoryEntity actual = MAPPER.mapStoryToStoryEntity(DOMAIN, SPRINT);

        assertEquals(ENTITY_WITH_SPRINT, actual);
    }
}