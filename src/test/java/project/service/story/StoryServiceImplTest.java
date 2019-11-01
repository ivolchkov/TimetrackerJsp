package project.service.story;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import project.domain.goal.Goal;
import project.domain.story.Story;
import project.entity.story.Status;
import project.entity.story.StoryEntity;
import project.exception.InvalidEntityCreation;
import project.exception.InvalidEntityUpdating;
import project.repository.storyDao.StoryDao;
import project.service.mapper.StoryMapper;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StoryServiceImplTest {
    private static Story story;
    private static List<StoryEntity> entities;
    private static List<Story> stories;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Mock
    private StoryDao storyDao;

    @Mock
    private StoryMapper mapper;

    @InjectMocks
    private StoryServiceImpl service;

    @BeforeClass
    public static void init() {
        story = Story.builder().withId(1).build();

        entities = Arrays.asList(StoryEntity.builder().withId(1).build(),
                StoryEntity.builder().withId(2).build());
        stories = Arrays.asList(story,story);
    }

    @After
    public void resetMock() {
        reset(storyDao);
        reset(mapper);
    }

    @Test
    public void shouldCreateStory() {
        when(mapper.mapStoryToStoryEntity(any(Story.class))).thenReturn(entities.get(1));
        when(storyDao.save(any(StoryEntity.class))).thenReturn(true);

        assertTrue(service.createStory(story));
    }

    @Test
    public void shouldThrowInvalidEntityCreationWithNullStory() {
        exception.expect(InvalidEntityCreation.class);
        exception.expectMessage("Story is not valid");

        service.createStory(null);
    }

    @Test
    public void shouldShowAllStoriesByStatus() {
        when(storyDao.findByStatus(any(Status.class))).thenReturn(entities);
        when(mapper.mapStoryEntityToStory(any(StoryEntity.class))).thenReturn(story);

        List<Story> actual = service.showStoryByStatus(Status.TO_DO);

        assertEquals(stories, actual);
    }

    @Test
    public void shouldReturnEmptyList() {
        when(storyDao.findByStatus(any(Status.class))).thenReturn(Collections.emptyList());

        List<Story> actual = service.showStoryByStatus(Status.TO_DO);

        assertEquals(Collections.emptyList(), actual);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWithNullStatus() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Parameter is not valid");

        service.showStoryByStatus(null);
    }

    @Test
    public void shouldShowAllStoriesByGoal() {
        when(storyDao.findByGoal(any(Integer.class))).thenReturn(entities);
        when(mapper.mapStoryEntityToStory(any(StoryEntity.class))).thenReturn(story);

        List<Story> actual = service.showStoryByGoal(1);

        assertEquals(stories, actual);
    }

    @Test
    public void shouldReturnEmptyListSearchingByGoal() {
        when(storyDao.findByGoal(any(Integer.class))).thenReturn(Collections.emptyList());

        List<Story> actual = service.showStoryByGoal(1);

        assertEquals(Collections.emptyList(), actual);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWithNullGoalId() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Parameter is not valid");

        service.showStoryByGoal(null);
    }

    @Test
    public void shouldShowAllStoriesByUser() {
        when(storyDao.findByUser(any(Integer.class))).thenReturn(entities);
        when(mapper.mapStoryEntityToStory(any(StoryEntity.class))).thenReturn(story);

        List<Story> actual = service.showStoryByUser(1);

        assertEquals(stories, actual);
    }

    @Test
    public void shouldReturnEmptyListSearchingByUser() {
        when(storyDao.findByUser(any(Integer.class))).thenReturn(Collections.emptyList());

        List<Story> actual = service.showStoryByUser(1);

        assertEquals(Collections.emptyList(), actual);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWithNullUserId() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Parameter is not valid");

        service.showStoryByUser(null);
    }

    @Test
    public void shouldShowAllStoriesBySprint() {
        when(storyDao.findBySprint(any(Integer.class))).thenReturn(entities);
        when(mapper.mapStoryEntityToStory(any(StoryEntity.class))).thenReturn(story);

        List<Story> actual = service.showStoryBySprint(1);

        assertEquals(stories, actual);
    }

    @Test
    public void shouldReturnEmptyListSearchingBySprint() {
        when(storyDao.findBySprint(any(Integer.class))).thenReturn(Collections.emptyList());

        List<Story> actual = service.showStoryBySprint(1);

        assertEquals(Collections.emptyList(), actual);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWithNullSprintId() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Parameter is not valid");

        service.showStoryBySprint(null);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionAddingStoryToUser() {
        exception.expect(InvalidEntityUpdating.class);
        exception.expectMessage("Invalid story updating");

        service.addStoryToUser(story, null);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionAddingStoryToSprint() {
        exception.expect(InvalidEntityUpdating.class);
        exception.expectMessage("Invalid story updating");

        service.addStoryToSprint(story, null);
    }
}