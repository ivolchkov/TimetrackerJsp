package project.command.user;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import project.domain.User;
import project.entity.Role;
import project.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SignInTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @Mock
    private UserService userService;

    @InjectMocks
    private SignIn command;

    @After
    public void resetMock() {
        reset(request, session, userService);
    }

    @Test
    public void executeShouldReturnAdminPage() {
        User user = User.builder().withId(1).withRole(Role.ADMIN).build();
        when(request.getSession()).thenReturn(session);
        when(request.getParameter(anyString())).thenReturn("test");
        when(userService.login(anyString(), anyString())).thenReturn(user);
        String expected = "admin-service.jsp";
        String actual = command.execute(request);

        assertEquals(expected, actual);
    }

    @Test
    public void executeShouldReturnDeveloperPage() {
        User user = User.builder().withId(1).withRole(Role.DEVELOPER).build();
        when(request.getSession()).thenReturn(session);
        when(request.getParameter(anyString())).thenReturn("test");
        when(userService.login(anyString(), anyString())).thenReturn(user);
        String expected = "developer-service.jsp";
        String actual = command.execute(request);

        assertEquals(expected, actual);
    }

    @Test
    public void executeShouldReturnScrumMasterPage() {
        User user = User.builder().withId(1).withRole(Role.SCRUM_MASTER).build();
        when(request.getSession()).thenReturn(session);
        when(request.getParameter(anyString())).thenReturn("test");
        when(userService.login(anyString(), anyString())).thenReturn(user);
        String expected = "scrum-master-service.jsp";
        String actual = command.execute(request);

        assertEquals(expected, actual);
    }
}