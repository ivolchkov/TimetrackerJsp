package com.ua.timetracking.command.developer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import com.ua.timetracking.service.StoryService;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ShowFreeStoriesTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private StoryService storyService;

    @InjectMocks
    private ShowFreeStories command;

    @Test
    public void executeShouldReturnPage() {
        when(request.getParameter(anyString())).thenReturn("1");
        when(storyService.showStoriesWithoutUser(anyInt(), anyInt())).thenReturn(Collections.emptyList());
        when(storyService.showNumberOfRowsWithoutUser()).thenReturn(10);
        String expected = "freeStories.jsp";
        String actual = command.execute(request);

        assertEquals(expected, actual);
    }
}