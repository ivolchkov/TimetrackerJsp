package project.service.mapper;

import org.junit.Test;
import project.domain.Sprint;
import project.entity.SprintEntity;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class SprintMapperTest {
    private static final SprintEntity SPRINT_ENTITY = getSprintEntity(SprintEntity.builder());

    private static final SprintEntity ENTITY_WITH_ID = getSprintEntity(SprintEntity.builder()
            .withId(1));

    private static final Sprint DOMAIN = getSprint();

    private final SprintMapper sprintMapper = new SprintMapper();

    @Test
    public void mapSprintToSprintEntityShouldMapToEntity() {
        SprintEntity actual = sprintMapper.mapSprintToSprintEntity(DOMAIN);

        assertEquals(SPRINT_ENTITY, actual);
    }

    @Test
    public void mapSprintEntityToSprintShouldMapToDomain() {
        Sprint actual = sprintMapper.mapSprintEntityToSprint(ENTITY_WITH_ID);

        assertEquals(DOMAIN, actual);
    }

    private static SprintEntity getSprintEntity(SprintEntity.SprintBuilder builder) {
        return builder
                .withName("Test")
                .withStart(LocalDate.MIN)
                .withEnd(LocalDate.MAX)
                .withDescription("Test description")
                .build();
    }

    private static Sprint getSprint() {
        return Sprint.builder()
                .withId(1)
                .withName("Test")
                .withStart(LocalDate.MIN)
                .withEnd(LocalDate.MAX)
                .withDescription("Test description")
                .build();
    }
}