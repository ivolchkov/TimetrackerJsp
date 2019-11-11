package project.command.developer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import project.domain.user.User;
import project.service.story.StoryService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import java.util.Collections;
import java.util.Enumeration;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ShowDeveloperStoriesTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @Mock
    private StoryService sprintService;

    @InjectMocks
    private ShowDeveloperStories command;

    @Test
    public void executeShouldReturnPage() {
        User user = User.builder().withId(1).build();
        when(request.getParameter(anyString())).thenReturn("1");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(user);
        when(sprintService.showStoryByUser(anyInt(), anyInt(), anyInt())).thenReturn(Collections.emptyList());
        when(sprintService.showNumberOfRowsByUserId(anyInt())).thenReturn(10);
        String expected = "showDeveloperStories.jsp";
        String actual = command.execute(request);

        assertEquals(expected, actual);
    }
}