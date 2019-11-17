package project.service;

import project.domain.Backlog;

import java.util.List;

public interface BacklogService {
    boolean createBacklog(Backlog backlog);

    Backlog showBacklogById(Integer id);

    List<Backlog> showAllBacklogs(Integer currentPage, Integer recordsPerPage);

    Integer showNumberOfRows();
}