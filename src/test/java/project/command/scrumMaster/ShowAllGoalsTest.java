package project.command.scrumMaster;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import project.service.goal.GoalService;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ShowAllGoalsTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private GoalService goalService;

    @InjectMocks
    private ShowAllGoals command;

    @Test
    public void executeShouldReturnPage() {
        when(goalService.showNumberOfRows()).thenReturn(10);
        String expected = "createStory.jsp";
        String actual = command.execute(request);

        assertEquals(expected, actual);
    }
}