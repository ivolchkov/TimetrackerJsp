package project.service.backlog;

import org.apache.log4j.Logger;
import project.domain.backlog.Backlog;
import project.entity.backlog.BacklogEntity;
import project.exception.InvalidEntityCreation;
import project.exception.InvalidPaginatingException;
import project.repository.backlogDao.BacklogDao;
import project.service.mapper.BacklogMapper;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
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
        if (Objects.isNull(backlog) ) {
            LOGGER.warn("Backlog is not valid");
            throw new InvalidEntityCreation("Backlog is not valid");
        }

        return backlogDao.save(mapper.mapBacklogToBacklogEntity(backlog));
    }

    @Override
    public List<Backlog> showAllBacklogs(Integer currentPage, Integer recordsPerPage) {
        if ( currentPage == 0 || recordsPerPage == 0 ) {
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
