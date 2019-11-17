package project.command.scrumMaster;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import project.domain.Backlog;
import project.domain.Goal;
import project.service.BacklogService;
import project.service.GoalService;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AddGoalTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private BacklogService backlogService;

    @Mock
    private GoalService goalService;

    @InjectMocks
    private AddGoal command;

    @Test
    public void executeShouldReturnPage() {
        Backlog backlog = new Backlog(1);
        when(request.getParameter(anyString())).thenReturn("1");
        when(backlogService.showBacklogById(anyInt())).thenReturn(backlog);
        when(goalService.createGoal(any(Goal.class))).thenReturn(true);
        String expected = "scrum-master-service.jsp";
        String actual = command.execute(request);

        assertEquals(expected, actual);
    }
}