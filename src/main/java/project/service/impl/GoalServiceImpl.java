package project.service.impl;

import org.apache.log4j.Logger;
import project.domain.Goal;
import project.entity.GoalEntity;
import project.exception.EntityNotFoundException;
import project.exception.InvalidEntityCreation;
import project.exception.InvalidPaginatingException;
import project.repository.GoalDao;
import project.service.GoalService;
import project.service.mapper.GoalMapper;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class GoalServiceImpl implements GoalService {
    private static final Logger LOGGER = Logger.getLogger(GoalServiceImpl.class);

    private final GoalDao goalDao;
    private final GoalMapper mapper;

    public GoalServiceImpl(GoalDao goalDao, GoalMapper mapper) {
        this.goalDao = goalDao;
        this.mapper = mapper;
    }

    @Override
    public boolean createGoal(Goal goal) {
        if (Objects.isNull(goal)) {
            LOGGER.warn("Goal is not valid");
            throw new InvalidEntityCreation("Goal is not valid");
        }

        return goalDao.save(mapper.mapGoalToGoalEntity(goal));
    }

    @Override
    public Goal showGoalById(Integer id) {
        if (Objects.nonNull(id)) {
            Optional<GoalEntity> entity = goalDao.findById(id);

            if (entity.isPresent()) {
                return mapper.mapGoalEntityToGoal(entity.get());
            }

            LOGGER.warn("There is no goal by this id");
            throw new EntityNotFoundException("There is no goal by this id");
        }
        LOGGER.warn("There is no goal by this id");
        throw new EntityNotFoundException("There is no goal by this id");
    }

    @Override
    public List<Goal> showAllGoals(Integer currentPage, Integer recordsPerPage) {
        if (currentPage <= 0 || recordsPerPage <= 0) {
            LOGGER.error("Invalid number of current page or records per page");
            throw new InvalidPaginatingException("Invalid number of current page or records per page");
        }

        Integer offset = currentPage * recordsPerPage - recordsPerPage;
        List<GoalEntity> result = goalDao.findAll(offset, recordsPerPage);

        return result.isEmpty() ? Collections.emptyList() :
                result.stream()
                        .map(mapper::mapGoalEntityToGoal)
                        .collect(Collectors.toList());
    }

    @Override
    public Integer showNumberOfRows() {
        return goalDao.findAmountOfRows();
    }
}
