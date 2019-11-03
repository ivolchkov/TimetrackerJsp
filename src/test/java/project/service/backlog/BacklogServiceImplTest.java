package project.service.backlog;

import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import project.domain.backlog.Backlog;
import project.entity.backlog.BacklogEntity;
import project.exception.InvalidEntityCreation;
import project.repository.backlogDao.BacklogDao;
import project.service.mapper.BacklogMapper;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BacklogServiceImplTest {
    private static final Backlog backlog = new Backlog(1, "Test", "Test");
    private static final List<BacklogEntity> entities = Arrays.asList(
            BacklogEntity.builder().withId(1).build(),
            BacklogEntity.builder().withId(2).build());
    private static final List<Backlog> backlogs = Arrays.asList(backlog, backlog);

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Mock
    private BacklogDao backlogDao;

    @Mock
    private BacklogMapper mapper;

    @InjectMocks
    private BacklogServiceImpl service;

    @After
    public void resetMock() {
        reset(backlogDao);
        reset(mapper);
    }

    @Test
    public void shouldCreateBacklog() {
        when(mapper.mapBacklogToBacklogEntity(any(Backlog.class))).thenReturn(entities.get(1));
        when(backlogDao.save(any(BacklogEntity.class))).thenReturn(true);

        assertTrue(service.createBacklog(backlog));
    }

    @Test
    public void shouldThrowInvalidEntityCreationWithNullBacklog() {
        exception.expect(InvalidEntityCreation.class);
        exception.expectMessage("Backlog is not valid");

        service.createBacklog(null);
    }

    @Test
    public void shouldShowAllBacklogs() {
        when(backlogDao.findAll()).thenReturn(entities);
        when(mapper.mapBacklogEntityToBacklog(any(BacklogEntity.class))).thenReturn(backlog);

        List<Backlog> actual = service.showAllBacklogs();

        assertEquals(backlogs, actual);
    }

    @Test
    public void shouldReturnEmptyList() {
        when(backlogDao.findAll()).thenReturn(Collections.emptyList());

        List<Backlog> actual = service.showAllBacklogs();

        assertEquals(Collections.emptyList(), actual);
    }
}