package com.ua.timetracking.command.admin;

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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ShowStoriesTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private StoryService storyService;

    @InjectMocks
    private ShowStories command;

    @Test
    public void executeShouldReturnPage() {
        when(request.getParameter(anyString())).thenReturn("1");
        when(storyService.showAllStories(anyInt(), anyInt())).thenReturn(Collections.emptyList());
        when(storyService.showNumberOfRows()).thenReturn(10);
        String expected = "showStories.jsp";
        String actual = command.execute(request);

        verify(storyService).showAllStories(anyInt(), anyInt());
        assertEquals(expected, actual);
    }
}