package com.ua.timetracking.command.admin;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import com.ua.timetracking.service.BacklogService;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ShowProjectsTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private BacklogService backlogService;

    @InjectMocks
    private ShowProjects command;

    @Test
    public void executeShouldReturnPage() {
        when(request.getParameter(anyString())).thenReturn("1");
        when(backlogService.showAllBacklogs(anyInt(), anyInt())).thenReturn(Collections.emptyList());
        when(backlogService.showNumberOfRows()).thenReturn(10);
        String expected = "showProjects.jsp";
        String actual = command.execute(request);

        verify(backlogService).showAllBacklogs(anyInt(), anyInt());
        assertEquals(expected, actual);
    }
}