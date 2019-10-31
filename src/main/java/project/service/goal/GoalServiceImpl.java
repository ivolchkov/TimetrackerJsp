package project.service.goal;

import org.apache.log4j.Logger;
import project.domain.goal.Goal;
import project.entity.goal.GoalEntity;
import project.exception.InvalidEntityCreation;
import project.repository.goalDao.GoalDao;
import project.service.mapper.GoalMapper;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
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
        if (Objects.isNull(goal) ) {
            LOGGER.warn("Goal is not valid");
            throw new InvalidEntityCreation("Goal is not valid");
        }

        return goalDao.save(mapper.mapGoalToGoalEntity(goal));
    }

    @Override
    public List<Goal> showAllGoals() {
        List<GoalEntity> result = goalDao.findAll();

        return result.isEmpty() ? Collections.emptyList()
                : result.stream()
                .map(mapper::mapGoalEntityToGoal)
                .collect(Collectors.toList());
    }
}
