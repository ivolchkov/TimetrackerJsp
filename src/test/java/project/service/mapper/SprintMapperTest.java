package project.service.mapper;

import org.junit.Test;
import project.domain.sprint.Sprint;
import project.entity.sprint.SprintEntity;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class SprintMapperTest {
    private static final SprintEntity ENTITY = SprintEntity.builder()
            .withName("Test")
            .withStart(LocalDate.MIN)
            .withEnd(LocalDate.MAX)
            .withDescription("Test description")
            .build();

    private static final SprintEntity ENTITY_WITH_ID = SprintEntity.builder()
            .withId(1)
            .withName("Test")
            .withStart(LocalDate.MIN)
            .withEnd(LocalDate.MAX)
            .withDescription("Test description")
            .build();

    private static final Sprint DOMAIN = Sprint.builder()
            .withId(1)
            .withName("Test")
            .withStart(LocalDate.MIN)
            .withEnd(LocalDate.MAX)
            .withDescription("Test description")
            .build();

    private static final SprintMapper MAPPER = new SprintMapper();

    @Test
    public void mapSprintToSprintEntityShouldMapToEntity() {
        SprintEntity actual = MAPPER.mapSprintToSprintEntity(DOMAIN);

        assertEquals(ENTITY, actual);
    }

    @Test
    public void mapSprintEntityToSprintShouldMapToDomain() {
        Sprint actual = MAPPER.mapSprintEntityToSprint(ENTITY_WITH_ID);

        assertEquals(DOMAIN, actual);
    }
}