package com.ua.timetracking.service.goal;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import com.ua.timetracking.domain.Goal;
import com.ua.timetracking.entity.GoalEntity;
import com.ua.timetracking.exception.EntityNotFoundException;
import com.ua.timetracking.exception.InvalidEntityCreation;
import com.ua.timetracking.exception.InvalidPaginatingException;
import com.ua.timetracking.repository.GoalDao;
import com.ua.timetracking.service.impl.GoalServiceImpl;
import com.ua.timetracking.service.mapper.GoalMapper;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GoalServiceImplTest {
    private static final Goal GOAL = new Goal(1);
    private static final List<GoalEntity> ENTITIES = Arrays.asList(
            GoalEntity.builder().withId(1).build(),
            GoalEntity.builder().withId(2).build());
    private static final List<Goal> GOALS = Arrays.asList(GOAL, GOAL);

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Mock
    private GoalDao goalDao;

    @Mock
    private GoalMapper mapper;

    @InjectMocks
    private GoalServiceImpl service;

    @After
    public void resetMock() {
        reset(goalDao, mapper);
    }

    @Test
    public void showGoalByIdShouldReturnGoalById() {
        when(goalDao.findById(anyInt())).thenReturn(Optional.of(ENTITIES.get(1)));
        when(mapper.mapGoalEntityToGoal(any(GoalEntity.class))).thenReturn(GOAL);
        Goal actual = service.showGoalById(1);

        assertEquals(GOAL, actual);
    }

    @Test
    public void showGoalByIdShouldThrowEntityNotFoundExceptionWithNullId() {
        exception.expect(EntityNotFoundException.class);
        exception.expectMessage("There is no goal by this id");

        service.showGoalById(null);
    }

    @Test
    public void showGoalByIdShouldThrowEntityNotFoundExceptionWithNoEntity() {
        when(goalDao.findById(anyInt())).thenReturn(Optional.empty());
        exception.expect(EntityNotFoundException.class);
        exception.expectMessage("There is no goal by this id");

        service.showGoalById(null);
    }

    @Test
    public void createGoalShouldCreateGoal() {
        when(mapper.mapGoalToGoalEntity(any(Goal.class))).thenReturn(ENTITIES.get(1));
        when(goalDao.save(any(GoalEntity.class))).thenReturn(true);

        assertTrue(service.createGoal(GOAL));
    }

    @Test
    public void createGoalShouldThrowInvalidEntityCreationWithNullGoal() {
        exception.expect(InvalidEntityCreation.class);
        exception.expectMessage("Goal is not valid");

        service.createGoal(null);
    }

    @Test
    public void showAllGoalsShouldShowAllGoals() {
        when(goalDao.findAll(any(Integer.class) , any(Integer.class))).thenReturn(ENTITIES);
        when(mapper.mapGoalEntityToGoal(any(GoalEntity.class))).thenReturn(GOAL);

        List<Goal> actual = service.showAllGoals(1, 10);

        assertEquals(GOALS, actual);
    }

    @Test
    public void showAllGoalsShouldReturnEmptyList() {
        when(goalDao.findAll(any(Integer.class) , any(Integer.class))).thenReturn(Collections.emptyList());

        List<Goal> actual = service.showAllGoals(1 , 10);

        assertEquals(Collections.emptyList(), actual);
    }

    @Test
    public void showAllGoalsShouldThrowInvalidPaginatingException() {
        exception.expect(InvalidPaginatingException.class);
        exception.expectMessage("Invalid number of current page or records per page");

        service.showAllGoals(0 ,1);
    }
}