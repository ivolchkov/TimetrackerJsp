package project.service.mapper;

import org.junit.Test;
import project.domain.Backlog;
import project.domain.Goal;
import project.entity.BacklogEntity;
import project.entity.GoalEntity;

import static org.junit.Assert.*;

public class GoalMapperTest {
    private static final BacklogEntity BACKLOG_ENTITY = BacklogEntity.builder().withId(1).build();

    private static final GoalEntity ENTITY = GoalEntity.builder()
            .withName("Test")
            .withBacklog(BACKLOG_ENTITY)
            .build();

    private static final GoalEntity ENTITY_WITH_ID = GoalEntity.builder()
            .withId(1)
            .withName("Test")
            .withBacklog(BACKLOG_ENTITY)
            .build();

    private static final Goal DOMAIN = new Goal(1, "Test", new Backlog(1));

    private static final GoalMapper MAPPER = new GoalMapper();

    @Test
    public void mapGoalToGoalEntityShouldMapToEntity() {
        GoalEntity actual = MAPPER.mapGoalToGoalEntity(DOMAIN);

        assertEquals(ENTITY, actual);
    }

    @Test
    public void mapGoalEntityToGoalShouldMapToDomain() {
        Goal actual = MAPPER.mapGoalEntityToGoal(ENTITY_WITH_ID);

        assertEquals(DOMAIN, actual);
    }
}