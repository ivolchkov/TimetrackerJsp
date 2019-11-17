package project.command.scrumMaster;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import project.domain.Sprint;
import project.service.SprintService;

import javax.servlet.http.HttpServletRequest;

import java.time.LocalDate;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CreateSprintTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private SprintService sprintService;

    @InjectMocks
    private CreateSprint command;

    @Test
    public void executeShouldReturnPage() {
        when(request.getParameter(anyString())).thenReturn(LocalDate.now().toString());
        when(sprintService.createSprint(any(Sprint.class))).thenReturn(true);
        String expected = "scrum-master-service.jsp";
        String actual = command.execute(request);

        assertEquals(expected, actual);
    }
}