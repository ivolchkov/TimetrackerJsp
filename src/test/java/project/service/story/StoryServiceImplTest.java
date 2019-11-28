package project.service.story;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import project.domain.Story;
import project.entity.Status;
import project.entity.StoryEntity;
import project.exception.InvalidEntityCreation;
import project.exception.InvalidEntityUpdating;
import project.exception.InvalidPaginatingException;
import project.repository.StoryDao;
import project.service.impl.StoryServiceImpl;
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
    private static final Story STORY = Story.builder().withId(1).withStatus(Status.TO_DO).build();
    private static final List<StoryEntity> ENTITIES = Arrays.asList(
            StoryEntity.builder().withId(1).withStatus(Status.TO_DO).build(),
            StoryEntity.builder().withId(2).withStatus(Status.TO_DO).build());
    private static final List<Story> STORIES = Arrays.asList(STORY, STORY);

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Mock
    private StoryDao storyDao;

    @Mock
    private StoryMapper mapper;

    @InjectMocks
    private StoryServiceImpl service;

    @After
    public void resetMock() {
        reset(storyDao, mapper);
    }

    @Test
    public void shouldCreateStory() {
        when(mapper.mapStoryToStoryEntity(any(Story.class))).thenReturn(ENTITIES.get(1));
        when(storyDao.save(any(StoryEntity.class))).thenReturn(true);

        assertTrue(service.createStory(STORY));
    }

    @Test
    public void shouldThrowInvalidEntityCreationWithNullStory() {
        exception.expect(InvalidEntityCreation.class);
        exception.expectMessage("Story is not valid");

        service.createStory(null);
    }

    @Test
    public void shouldShowAllStoriesByStatus() {
        when(storyDao.findByStatus(any(Status.class), any(Integer.class), any(Integer.class))).thenReturn(ENTITIES);
        when(mapper.mapStoryEntityToStory(any(StoryEntity.class))).thenReturn(STORY);

        List<Story> actual = service.showStoryByStatus(Status.TO_DO, 1 , 1);

        assertEquals(STORIES, actual);
    }

    @Test
    public void shouldReturnEmptyListSearchingByStatus() {
        when(storyDao.findByStatus(any(Status.class), any(Integer.class), any(Integer.class))).thenReturn(Collections.emptyList());

        List<Story> actual = service.showStoryByStatus(Status.TO_DO, 1 , 1);

        assertEquals(Collections.emptyList(), actual);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWithNullStatus() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Parameter is not valid");

        service.showStoryByStatus(null , 1, 1);
    }

    @Test
    public void shouldShowAllStoriesByGoal() {
        when(storyDao.findByGoal(any(Integer.class), any(Integer.class), any(Integer.class))).thenReturn(ENTITIES);
        when(mapper.mapStoryEntityToStory(any(StoryEntity.class))).thenReturn(STORY);

        List<Story> actual = service.showStoryByGoal(1, 1, 1);

        assertEquals(STORIES, actual);
    }

    @Test
    public void shouldReturnEmptyListSearchingByGoal() {
        when(storyDao.findByGoal(any(Integer.class), any(Integer.class), any(Integer.class))).thenReturn(Collections.emptyList());

        List<Story> actual = service.showStoryByGoal(1,1, 1);

        assertEquals(Collections.emptyList(), actual);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWithNullGoalId() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Parameter is not valid");

        service.showStoryByGoal(null, 1, 1);
    }

    @Test
    public void shouldShowAllStoriesByUser() {
        when(storyDao.findByUser(any(Integer.class), any(Integer.class), any(Integer.class))).thenReturn(ENTITIES);
        when(mapper.mapStoryEntityToStory(any(StoryEntity.class))).thenReturn(STORY);

        List<Story> actual = service.showStoryByUser(1, 1, 1);

        assertEquals(STORIES, actual);
    }

    @Test
    public void shouldReturnEmptyListSearchingByUser() {
        when(storyDao.findByUser(any(Integer.class), any(Integer.class), any(Integer.class))).thenReturn(Collections.emptyList());

        List<Story> actual = service.showStoryByUser(1,1,1);

        assertEquals(Collections.emptyList(), actual);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWithNullUserId() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Parameter is not valid");

        service.showStoryByUser(null,1,1);
    }

    @Test
    public void shouldShowAllStoriesBySprint() {
        when(storyDao.findBySprint(any(Integer.class), any(Integer.class), any(Integer.class))).thenReturn(ENTITIES);
        when(mapper.mapStoryEntityToStory(any(StoryEntity.class))).thenReturn(STORY);

        List<Story> actual = service.showStoryBySprint(1,1,1);

        assertEquals(STORIES, actual);
    }

    @Test
    public void shouldReturnEmptyListSearchingBySprint() {
        when(storyDao.findBySprint(any(Integer.class), any(Integer.class), any(Integer.class))).thenReturn(Collections.emptyList());

        List<Story> actual = service.showStoryBySprint(1,1,1);

        assertEquals(Collections.emptyList(), actual);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWithNullSprintId() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Parameter is not valid");

        service.showStoryBySprint(null,1,1);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionAddingStoryToUser() {
        exception.expect(InvalidEntityUpdating.class);
        exception.expectMessage("Invalid story updating");

        service.addStoryToUser(STORY, null);
    }

    @Test
    public void showAllStoriesShouldShowAllStories() {
        when(storyDao.findAll(any(Integer.class) , any(Integer.class))).thenReturn(ENTITIES);
        when(mapper.mapStoryEntityToStory(any(StoryEntity.class))).thenReturn(STORY);

        List<Story> actual = service.showAllStories(1 , 10);

        assertEquals(STORIES, actual);
    }

    @Test
    public void showAllStoriesShouldReturnEmptyList() {
        when(storyDao.findAll(any(Integer.class) , any(Integer.class))).thenReturn(Collections.emptyList());

        List<Story> actual = service.showAllStories(1 , 10);

        assertEquals(Collections.emptyList(), actual);
    }

    @Test
    public void showAllStoriesShouldThrowInvalidPaginatingException() {
        exception.expect(InvalidPaginatingException.class);
        exception.expectMessage("Invalid number of current page or records per page");

        service.showAllStories(0 ,1);
    }

    @Test
    public void showStoriesWithoutUserShouldShowAllStories() {
        when(storyDao.findWithoutUser(any(Integer.class) , any(Integer.class))).thenReturn(ENTITIES);
        when(mapper.mapStoryEntityToStory(any(StoryEntity.class))).thenReturn(STORY);

        List<Story> actual = service.showStoriesWithoutUser(1 , 10);

        assertEquals(STORIES, actual);
    }

    @Test
    public void showStoriesWithoutUserShouldReturnEmptyList() {
        when(storyDao.findAll(any(Integer.class) , any(Integer.class))).thenReturn(Collections.emptyList());

        List<Story> actual = service.showStoriesWithoutUser(1 , 10);

        assertEquals(Collections.emptyList(), actual);
    }

    @Test
    public void showStoriesWithoutUserShouldThrowInvalidPaginatingException() {
        exception.expect(InvalidPaginatingException.class);
        exception.expectMessage("Invalid number of current page or records per page");

        service.showStoriesWithoutUser(0 ,1);
    }
}