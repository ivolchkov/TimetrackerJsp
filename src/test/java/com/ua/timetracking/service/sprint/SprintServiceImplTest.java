package com.ua.timetracking.service.sprint;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import com.ua.timetracking.domain.Sprint;
import com.ua.timetracking.entity.SprintEntity;
import com.ua.timetracking.exception.InvalidEntityCreation;
import com.ua.timetracking.exception.InvalidPaginatingException;
import com.ua.timetracking.repository.SprintDao;
import com.ua.timetracking.service.impl.SprintServiceImpl;
import com.ua.timetracking.service.mapper.SprintMapper;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SprintServiceImplTest {
    private static final Sprint SPRINT = Sprint.builder().withId(1).build();
    private static final List<SprintEntity> ENTITIES = Arrays.asList(
            SprintEntity.builder().withId(1).build(),
            SprintEntity.builder().withId(2).build());
    private static final List<Sprint> SPRINTS = Arrays.asList(SPRINT, SPRINT);

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
        reset(sprintDao, mapper);
    }

    @Test
    public void createSprintShouldCreateSprint() {
        when(mapper.mapSprintToSprintEntity(any(Sprint.class))).thenReturn(ENTITIES.get(1));
        when(sprintDao.save(any(SprintEntity.class))).thenReturn(true);

        assertTrue(service.createSprint(SPRINT));
    }

    @Test
    public void createSprintShouldThrowInvalidEntityCreationWithNullSprint() {
        exception.expect(InvalidEntityCreation.class);
        exception.expectMessage("Sprint is not valid");

        service.createSprint(null);
    }

    @Test
    public void createSprintShouldShowAllSprints() {
        when(sprintDao.findAll(any(Integer.class) , any(Integer.class))).thenReturn(ENTITIES);
        when(mapper.mapSprintEntityToSprint(any(SprintEntity.class))).thenReturn(SPRINT);

        List<Sprint> actual = service.showAllSprints(1 , 10);

        assertEquals(SPRINTS, actual);
    }

    @Test
    public void createSprintShouldReturnEmptyList() {
        when(sprintDao.findAll(any(Integer.class) , any(Integer.class))).thenReturn(Collections.emptyList());

        List<Sprint> actual = service.showAllSprints(1 , 10);

        assertEquals(Collections.emptyList(), actual);
    }

    @Test
    public void createSprintShouldThrowInvalidPaginatingException() {
        exception.expect(InvalidPaginatingException.class);
        exception.expectMessage("Invalid number of current page or records per page");

        service.showAllSprints(0 ,1);
    }
}