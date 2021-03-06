package com.ua.timetracking.service.story;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import com.ua.timetracking.domain.Story;
import com.ua.timetracking.entity.Status;
import com.ua.timetracking.entity.StoryEntity;
import com.ua.timetracking.exception.InvalidEntityCreation;
import com.ua.timetracking.exception.InvalidEntityUpdating;
import com.ua.timetracking.exception.InvalidPaginatingException;
import com.ua.timetracking.repository.StoryDao;
import com.ua.timetracking.service.impl.StoryServiceImpl;
import com.ua.timetracking.service.mapper.StoryMapper;

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
    public void createStoryShouldCreateStory() {
        when(mapper.mapStoryToStoryEntity(any(Story.class))).thenReturn(ENTITIES.get(1));
        when(storyDao.save(any(StoryEntity.class))).thenReturn(true);

        assertTrue(service.createStory(STORY));
    }

    @Test
    public void createStoryShouldThrowInvalidEntityCreationWithNullStory() {
        exception.expect(InvalidEntityCreation.class);
        exception.expectMessage("Story is not valid");

        service.createStory(null);
    }

    @Test
    public void showStoryByUserShouldShowAllStoriesByUser() {
        when(storyDao.findByUser(any(Integer.class), any(Integer.class), any(Integer.class))).thenReturn(ENTITIES);
        when(mapper.mapStoryEntityToStory(any(StoryEntity.class))).thenReturn(STORY);

        List<Story> actual = service.showStoryByUser(1, 1, 1);

        assertEquals(STORIES, actual);
    }

    @Test
    public void showStoryByUserShouldReturnEmptyListSearchingByUser() {
        when(storyDao.findByUser(any(Integer.class), any(Integer.class), any(Integer.class))).thenReturn(Collections.emptyList());

        List<Story> actual = service.showStoryByUser(1,1,1);

        assertEquals(Collections.emptyList(), actual);
    }

    @Test
    public void showStoryByUserShouldThrowIllegalArgumentExceptionWithNullUserId() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("User id is not valid");

        service.showStoryByUser(null,1,1);
    }

    @Test
    public void addStoryToUserShouldThrowIllegalArgumentExceptionAddingStoryToUser() {
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