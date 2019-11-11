package project.command.scrumMaster;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import project.domain.goal.Goal;
import project.domain.story.Story;
import project.service.goal.GoalService;
import project.service.story.StoryService;

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
        when(request.getParameter(anyString())).thenReturn(LocalTime.now().toString());
        when(goalService.showGoalById(anyInt())).thenReturn(goal);
        when(storyService.createStory(any(Story.class))).thenReturn(true);
        String expected = "scrum-master-service.jsp";
        String actual = command.execute(request);

        assertEquals(expected, actual);
    }
}