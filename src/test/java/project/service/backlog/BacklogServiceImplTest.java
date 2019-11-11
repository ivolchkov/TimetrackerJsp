package project.service.backlog;

import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import project.domain.backlog.Backlog;
import project.entity.backlog.BacklogEntity;
import project.exception.EntityNotFoundException;
import project.exception.InvalidEntityCreation;
import project.exception.InvalidPaginatingException;
import project.repository.backlogDao.BacklogDao;
import project.service.mapper.BacklogMapper;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BacklogServiceImplTest {
    private static final Backlog BACKLOG = new Backlog(1, "Test", "Test");
    private static final List<BacklogEntity> ENTITIES = Arrays.asList(
            BacklogEntity.builder().withId(1).build(),
            BacklogEntity.builder().withId(2).build());
    private static final List<Backlog> BACKLOGS = Arrays.asList(BACKLOG, BACKLOG);

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
    public void shouldReturnBacklogById() {
        when(backlogDao.findById(anyInt())).thenReturn(Optional.of(ENTITIES.get(1)));
        when(mapper.mapBacklogEntityToBacklog(any(BacklogEntity.class))).thenReturn(BACKLOG);
        Backlog actual = service.showBacklogById(1);

        assertEquals(BACKLOG, actual);
    }

    @Test
    public void shouldThrowEntityNotFoundExceptionWithNullId() {
        exception.expect(EntityNotFoundException.class);
        exception.expectMessage("There is no backlog by this id");

        service.showBacklogById(null);
    }

    @Test
    public void shouldThrowEntityNotFoundExceptionWithNoEntity() {
        when(backlogDao.findById(anyInt())).thenReturn(Optional.empty());
        exception.expect(EntityNotFoundException.class);
        exception.expectMessage("There is no backlog by this id");

        service.showBacklogById(null);
    }

    @Test
    public void shouldCreateBacklog() {
        when(mapper.mapBacklogToBacklogEntity(any(Backlog.class))).thenReturn(ENTITIES.get(1));
        when(backlogDao.save(any(BacklogEntity.class))).thenReturn(true);

        assertTrue(service.createBacklog(BACKLOG));
    }

    @Test
    public void shouldThrowInvalidEntityCreationWithNullBacklog() {
        exception.expect(InvalidEntityCreation.class);
        exception.expectMessage("Backlog is not valid");

        service.createBacklog(null);
    }

    @Test
    public void shouldShowAllBacklogs() {
        when(backlogDao.findAll(any(Integer.class) , any(Integer.class))).thenReturn(ENTITIES);
        when(mapper.mapBacklogEntityToBacklog(any(BacklogEntity.class))).thenReturn(BACKLOG);

        List<Backlog> actual = service.showAllBacklogs(1 , 10);

        assertEquals(BACKLOGS, actual);
    }

    @Test
    public void shouldReturnEmptyList() {
        when(backlogDao.findAll(any(Integer.class) , any(Integer.class))).thenReturn(Collections.emptyList());

        List<Backlog> actual = service.showAllBacklogs(1 , 10);

        assertEquals(Collections.emptyList(), actual);
    }

    @Test
    public void shouldThrowInvalidPaginatingException() {
        exception.expect(InvalidPaginatingException.class);
        exception.expectMessage("Invalid number of current page or records per page");

        service.showAllBacklogs(0 ,1);
    }
}