package com.ua.timetracking.command.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SignOutTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @InjectMocks
    private SignOut command;

    @Test
    public void executeShouldReturnPage() {
        when(request.getSession()).thenReturn(session);
        String expected = "index.jsp";
        String actual = command.execute(request);

        assertEquals(expected, actual);
    }
}