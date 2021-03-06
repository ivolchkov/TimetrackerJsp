package com.ua.timetracking.service.impl;

import org.apache.log4j.Logger;
import com.ua.timetracking.domain.Sprint;
import com.ua.timetracking.entity.SprintEntity;
import com.ua.timetracking.exception.InvalidEntityCreation;
import com.ua.timetracking.exception.InvalidPaginatingException;
import com.ua.timetracking.repository.SprintDao;
import com.ua.timetracking.service.SprintService;
import com.ua.timetracking.service.mapper.SprintMapper;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class SprintServiceImpl implements SprintService {
    private static final Logger LOGGER = Logger.getLogger(SprintServiceImpl.class);

    private final SprintDao sprintDao;
    private final SprintMapper mapper;

    public SprintServiceImpl(SprintDao sprintDao, SprintMapper mapper) {
        this.sprintDao = sprintDao;
        this.mapper = mapper;
    }

    @Override
    public boolean createSprint(Sprint sprint) {
        if (Objects.isNull(sprint)) {
            LOGGER.warn("Sprint is not valid");
            throw new InvalidEntityCreation("Sprint is not valid");
        }

        return sprintDao.save(mapper.mapSprintToSprintEntity(sprint));
    }

    @Override
    public List<Sprint> showAllSprints(Integer currentPage, Integer recordsPerPage) {
        if (currentPage <= 0 || recordsPerPage <= 0) {
            LOGGER.error("Invalid number of current page or records per page");
            throw new InvalidPaginatingException("Invalid number of current page or records per page");
        }

        Integer offset = currentPage * recordsPerPage - recordsPerPage;
        List<SprintEntity> result = sprintDao.findAll(offset, recordsPerPage);

        return result.isEmpty() ? Collections.emptyList() :
                result.stream()
                        .map(mapper::mapSprintEntityToSprint)
                        .collect(Collectors.toList());
    }

    @Override
    public Integer showNumberOfRows() {
        return sprintDao.findAmountOfRows();
    }
}
