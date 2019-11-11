package project.command.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import project.service.user.UserService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import java.util.Enumeration;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SignUpTest {
    @Mock
    private HttpServletRequest request;

    @InjectMocks
    private SignUp command;

    @Test
    public void executeShouldReturnSuccessfulRegisterPage() {
        when(request.getParameter(anyString())).thenReturn("ADMIN");
        String expected = "index.jsp";
        String actual = command.execute(request);

        assertEquals(expected, actual);
    }

    @Test
    public void executeShouldReturnInvalidRegisterPage() {
        when(request.getParameter(anyString())).thenReturn("ADMIN");
        when(request.getParameter("repeatedPassword")).thenReturn("error");
        String expected = "register.jsp";
        String actual = command.execute(request);

        assertEquals(expected, actual);
    }
}