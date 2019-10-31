package project.service.sprint;

import org.apache.log4j.Logger;
import project.domain.sprint.Sprint;
import project.entity.sprint.SprintEntity;
import project.exception.InvalidEntityCreation;
import project.repository.sprintDao.SprintDao;
import project.service.mapper.SprintMapper;

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
    public boolean createBacklog(Sprint sprint) {
        if (Objects.isNull(sprint) ) {
            LOGGER.warn("Sprint is not valid");
            throw new InvalidEntityCreation("Sprint is not valid");
        }

        return sprintDao.save(mapper.mapSprintToSprintEntity(sprint));
    }

    @Override
    public List<Sprint> showAllBacklogs() {
        List<SprintEntity> result = sprintDao.findAll();

        return result.isEmpty() ? Collections.emptyList()
                : result.stream()
                .map(mapper::mapSprintEntityToSprint)
                .collect(Collectors.toList());
    }
}
