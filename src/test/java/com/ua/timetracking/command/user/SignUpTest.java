package com.ua.timetracking.command.user;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import com.ua.timetracking.service.UserService;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SignUpTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private UserService service;

    @InjectMocks
    private SignUp command;

    @After
    public void resetMock() {
        reset(request, service);
    }

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