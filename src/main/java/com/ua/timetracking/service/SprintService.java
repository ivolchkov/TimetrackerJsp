package com.ua.timetracking.service;

import com.ua.timetracking.domain.Sprint;

import java.util.List;

public interface SprintService {
    boolean createSprint(Sprint sprint);

    List<Sprint> showAllSprints(Integer currentPage, Integer recordsPerPage);

    Integer showNumberOfRows();
}
