package project.service.sprint;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import project.domain.sprint.Sprint;
import project.entity.sprint.SprintEntity;
import project.exception.InvalidEntityCreation;
import project.repository.sprintDao.SprintDao;
import project.service.mapper.SprintMapper;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SprintServiceImplTest {
    private static final Sprint sprint = Sprint.builder().withId(1).build();
    private static final List<SprintEntity> entities = Arrays.asList(
            SprintEntity.builder().withId(1).build(),
            SprintEntity.builder().withId(2).build());
    private static final List<Sprint> sprints = Arrays.asList(sprint,sprint);

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Mock
    private SprintDao sprintDao;

    @Mock
    private SprintMapper mapper;

    @InjectMocks
    private SprintServiceImpl service;

    @After
    public void resetMock() {
        reset(sprintDao);
        reset(mapper);
    }

    @Test
    public void shouldCreateSprint() {
        when(mapper.mapSprintToSprintEntity(any(Sprint.class))).thenReturn(entities.get(1));
        when(sprintDao.save(any(SprintEntity.class))).thenReturn(true);

        assertTrue(service.createSprint(sprint));
    }

    @Test
    public void shouldThrowInvalidEntityCreationWithNullSprint() {
        exception.expect(InvalidEntityCreation.class);
        exception.expectMessage("Sprint is not valid");

        service.createSprint(null);
    }

    @Test
    public void shouldShowAllSprints() {
        when(sprintDao.findAll()).thenReturn(entities);
        when(mapper.mapSprintEntityToSprint(any(SprintEntity.class))).thenReturn(sprint);

        List<Sprint> actual = service.showAllSprints();

        assertEquals(sprints, actual);
    }

    @Test
    public void shouldReturnEmptyList() {
        when(sprintDao.findAll()).thenReturn(Collections.emptyList());

        List<Sprint> actual = service.showAllSprints();

        assertEquals(Collections.emptyList(), actual);
    }
}