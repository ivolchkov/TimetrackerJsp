package project.command.admin;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import project.service.sprint.SprintService;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ShowSprintsTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private SprintService sprintService;

    @InjectMocks
    private ShowSprints command;

    @Test
    public void executeShouldReturnPage() {
        when(request.getParameter(anyString())).thenReturn("1");
        when(sprintService.showAllSprints(anyInt(), anyInt())).thenReturn(Collections.emptyList());
        when(sprintService.showNumberOfRows()).thenReturn(10);
        String expected = "showSprints.jsp";
        String actual = command.execute(request);

        assertEquals(expected, actual);
    }
}