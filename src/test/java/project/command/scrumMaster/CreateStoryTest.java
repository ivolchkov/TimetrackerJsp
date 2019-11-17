package project.command.scrumMaster;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import project.domain.Goal;
import project.domain.Story;
import project.service.GoalService;
import project.service.StoryService;

import javax.servlet.http.HttpServletRequest;

import java.time.LocalTime;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CreateStoryTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private StoryService storyService;

    @Mock
    private GoalService goalService;

    @InjectMocks
    private CreateStory command;

    @Test
    public void executeShouldReturnPage() {
        Goal goal = new Goal(1);
        when(request.getParameter("spentTime")).thenReturn(LocalTime.now().toString());
        when(request.getParameter("goalId")).thenReturn("1");
        when(goalService.showGoalById(anyInt())).thenReturn(goal);
        when(storyService.createStory(any(Story.class))).thenReturn(true);
        String expected = "scrum-master-service.jsp";
        String actual = command.execute(request);

        assertEquals(expected, actual);
    }
}