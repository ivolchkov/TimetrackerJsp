package project.service.mapper;

import org.junit.Test;
import project.domain.Backlog;
import project.entity.BacklogEntity;

import static org.junit.Assert.*;

public class BacklogMapperTest {
    private static final BacklogEntity ENTITY = BacklogEntity.builder()
            .withProjectName("Test")
            .withDescription("Test description")
            .build();

    private static final BacklogEntity ENTITY_WITH_ID = BacklogEntity.builder()
            .withId(1)
            .withProjectName("Test")
            .withDescription("Test description")
            .build();

    private static final Backlog DOMAIN = new Backlog(1, "Test", "Test description");

    private static final BacklogMapper MAPPER = new BacklogMapper();

    @Test
    public void mapBacklogToBacklogEntityShouldMapToEntity() {
        BacklogEntity actual = MAPPER.mapBacklogToBacklogEntity(DOMAIN);

        assertEquals(ENTITY, actual);
    }

    @Test
    public void mapBacklogEntityToBacklogShouldMapToDomain() {
        Backlog actual = MAPPER.mapBacklogEntityToBacklog(ENTITY_WITH_ID);

        assertEquals(DOMAIN, actual);
    }
}