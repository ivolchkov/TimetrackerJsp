package project.service.goal;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import project.domain.goal.Goal;
import project.entity.goal.GoalEntity;
import project.exception.InvalidEntityCreation;
import project.exception.InvalidPaginatingException;
import project.repository.goalDao.GoalDao;
import project.service.mapper.GoalMapper;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
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
        reset(goalDao);
        reset(mapper);
    }

    @Test
    public void shouldCreateGoal() {
        when(mapper.mapGoalToGoalEntity(any(Goal.class))).thenReturn(ENTITIES.get(1));
        when(goalDao.save(any(GoalEntity.class))).thenReturn(true);

        assertTrue(service.createGoal(GOAL));
    }

    @Test
    public void shouldThrowInvalidEntityCreationWithNullGoal() {
        exception.expect(InvalidEntityCreation.class);
        exception.expectMessage("Goal is not valid");

        service.createGoal(null);
    }

    @Test
    public void shouldShowAllGoals() {
        when(goalDao.findAll(any(Integer.class) , any(Integer.class))).thenReturn(ENTITIES);
        when(mapper.mapGoalEntityToGoal(any(GoalEntity.class))).thenReturn(GOAL);

        List<Goal> actual = service.showAllGoals(1, 10);

        assertEquals(GOALS, actual);
    }

    @Test
    public void shouldReturnEmptyList() {
        when(goalDao.findAll(any(Integer.class) , any(Integer.class))).thenReturn(Collections.emptyList());

        List<Goal> actual = service.showAllGoals(1 , 10);

        assertEquals(Collections.emptyList(), actual);
    }

    @Test
    public void shouldThrowInvalidPaginatingException() {
        exception.expect(InvalidPaginatingException.class);
        exception.expectMessage("Invalid number of current page or records per page");

        service.showAllGoals(0 ,1);
    }
}