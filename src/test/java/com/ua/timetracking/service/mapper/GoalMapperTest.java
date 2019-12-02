package com.ua.timetracking.service.mapper;

import org.junit.Test;
import com.ua.timetracking.domain.Backlog;
import com.ua.timetracking.domain.Goal;
import com.ua.timetracking.entity.BacklogEntity;
import com.ua.timetracking.entity.GoalEntity;

import static org.junit.Assert.*;

public class GoalMapperTest {
    private static final BacklogEntity BACKLOG_ENTITY = BacklogEntity.builder().withId(1).build();

    private static final GoalEntity GOAL_ENTITY = getGoalEntity(GoalEntity.builder());

    private static final GoalEntity ENTITY_WITH_ID = getGoalEntity(GoalEntity.builder()
            .withId(1));

    private static final Goal DOMAIN = new Goal(1, "Test", new Backlog(1));

    private final GoalMapper goalMapper = new GoalMapper();

    @Test
    public void mapGoalToGoalEntityShouldMapToEntity() {
        GoalEntity actual = goalMapper.mapGoalToGoalEntity(DOMAIN);

        assertEquals(GOAL_ENTITY, actual);
    }

    @Test
    public void mapGoalEntityToGoalShouldMapToDomain() {
        Goal actual = goalMapper.mapGoalEntityToGoal(ENTITY_WITH_ID);

        assertEquals(DOMAIN, actual);
    }

    private static GoalEntity getGoalEntity(GoalEntity.GoalBuilder builder) {
        return builder
                .withName("Test")
                .withBacklog(BACKLOG_ENTITY)
                .build();
    }
}