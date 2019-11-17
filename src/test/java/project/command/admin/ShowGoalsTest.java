package project.command.admin;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import project.service.GoalService;

import javax.servlet.http.HttpServletRequest;

import java.util.Collections;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ShowGoalsTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private GoalService goalService;

    @InjectMocks
    private ShowGoals command;

    @Test
    public void executeShouldReturnPage() {
        when(request.getParameter(anyString())).thenReturn("1");
        when(goalService.showAllGoals(anyInt(), anyInt())).thenReturn(Collections.emptyList());
        when(goalService.showNumberOfRows()).thenReturn(10);
        String expected = "showGoals.jsp";
        String actual = command.execute(request);

        verify(goalService).showAllGoals(anyInt(), anyInt());
        assertEquals(expected, actual);
    }
}