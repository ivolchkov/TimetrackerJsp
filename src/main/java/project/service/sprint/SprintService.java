package project.service.sprint;

import project.domain.sprint.Sprint;

import java.util.List;

public interface SprintService {
    boolean createBacklog(Sprint sprint);

    List<Sprint> showAllBacklogs();
}
