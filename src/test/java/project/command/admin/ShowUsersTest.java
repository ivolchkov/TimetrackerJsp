package project.command.admin;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import project.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ShowUsersTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private UserService storyService;

    @InjectMocks
    private ShowUsers command;

    @Test
    public void executeShouldReturnPage() {
        when(request.getParameter(anyString())).thenReturn("1");
        when(storyService.findAll(anyInt(), anyInt())).thenReturn(Collections.emptyList());
        when(storyService.showNumberOfRows()).thenReturn(10);
        String expected = "showUsers.jsp";
        String actual = command.execute(request);

        verify(storyService).findAll(anyInt(), anyInt());
        assertEquals(expected, actual);
    }
}