package project.service.impl;

import org.apache.log4j.Logger;
import project.domain.Backlog;
import project.entity.BacklogEntity;
import project.exception.EntityNotFoundException;
import project.exception.InvalidEntityCreation;
import project.exception.InvalidPaginatingException;
import project.repository.BacklogDao;
import project.service.BacklogService;
import project.service.mapper.BacklogMapper;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class BacklogServiceImpl implements BacklogService {
    private static final Logger LOGGER = Logger.getLogger(BacklogServiceImpl.class);

    private final BacklogDao backlogDao;
    private final BacklogMapper mapper;

    public BacklogServiceImpl(BacklogDao backlogDao, BacklogMapper mapper) {
        this.backlogDao = backlogDao;
        this.mapper = mapper;
    }

    @Override
    public boolean createBacklog(Backlog backlog) {
        if (Objects.isNull(backlog)) {
            LOGGER.warn("Backlog is not valid");
            throw new InvalidEntityCreation("Backlog is not valid");
        }

        return backlogDao.save(mapper.mapBacklogToBacklogEntity(backlog));
    }

    @Override
    public Backlog showBacklogById(Integer id) {
        if (Objects.nonNull(id)) {
            Optional<BacklogEntity> entity = backlogDao.findById(id);

            if ( entity.isPresent() ) {
                return mapper.mapBacklogEntityToBacklog(entity.get());
            }

            LOGGER.warn("There is no backlog by this id");
            throw new EntityNotFoundException("There is no backlog by this id");
        }
        LOGGER.warn("There is no backlog by this id");
        throw new EntityNotFoundException("There is no backlog by this id");
    }

    @Override
    public List<Backlog> showAllBacklogs(Integer currentPage, Integer recordsPerPage) {
        if (currentPage <= 0 || recordsPerPage <= 0) {
            LOGGER.error("Invalid number of current page or records per page");
            throw new InvalidPaginatingException("Invalid number of current page or records per page");
        }

        Integer offset = currentPage * recordsPerPage - recordsPerPage;
        List<BacklogEntity> result = backlogDao.findAll(offset, recordsPerPage);

        return result.isEmpty() ? Collections.emptyList()
                : result.stream()
                .map(mapper::mapBacklogEntityToBacklog)
                .collect(Collectors.toList());
    }

    @Override
    public Integer showNumberOfRows() {
        return backlogDao.findAmountOfRows();
    }
}
