package project.service.backlog;

import project.domain.backlog.Backlog;

import java.util.List;

public interface BacklogService {
    boolean createBacklog(Backlog backlog);

    List<Backlog> showAllBacklogs();
}
