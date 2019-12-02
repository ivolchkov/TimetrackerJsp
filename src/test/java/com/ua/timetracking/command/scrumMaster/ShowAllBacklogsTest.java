package com.ua.timetracking.command.scrumMaster;

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
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ShowAllBacklogsTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private BacklogService backlogService;

    @InjectMocks
    private ShowAllBacklogs command;

    @Test
    public void executeShouldReturnPage() {
        when(backlogService.showAllBacklogs(anyInt(), anyInt())).thenReturn(Collections.emptyList());
        when(backlogService.showNumberOfRows()).thenReturn(10);
        String expected = "createGoal.jsp";
        String actual = command.execute(request);

        assertEquals(expected, actual);
    }
}