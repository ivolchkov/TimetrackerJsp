package project.service.mapper;

import org.junit.Test;
import project.domain.Backlog;
import project.entity.BacklogEntity;

import static org.junit.Assert.*;

public class BacklogMapperTest {
    private static final BacklogEntity BACKLOG_ENTITY = getBacklogEntity(BacklogEntity.builder());

    private static final BacklogEntity ENTITY_WITH_ID = getBacklogEntity(BacklogEntity.builder()
            .withId(1));

    private static final Backlog DOMAIN = new Backlog(1, "Test", "Test description");

    private final BacklogMapper backlogMapper = new BacklogMapper();

    @Test
    public void mapBacklogToBacklogEntityShouldMapToEntity() {
        BacklogEntity actual = backlogMapper.mapBacklogToBacklogEntity(DOMAIN);

        assertEquals(BACKLOG_ENTITY, actual);
    }

    @Test
    public void mapBacklogEntityToBacklogShouldMapToDomain() {
        Backlog actual = backlogMapper.mapBacklogEntityToBacklog(ENTITY_WITH_ID);

        assertEquals(DOMAIN, actual);
    }

    private static BacklogEntity getBacklogEntity(BacklogEntity.BacklogBuilder builder) {
        return builder
                .withProjectName("Test")
                .withDescription("Test description")
                .build();
    }
}