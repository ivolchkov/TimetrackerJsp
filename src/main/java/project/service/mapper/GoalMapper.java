package project.service.mapper;

import project.domain.backlog.Backlog;
import project.domain.goal.Goal;
import project.entity.backlog.BacklogEntity;
import project.entity.goal.GoalEntity;

public class GoalMapper {
    public GoalEntity mapGoalToGoalEntity(Goal domain) {
        return GoalEntity.builder()
                .withName(domain.getName())
                .withBacklog(BacklogEntity.builder()
                        .withId(domain.getBacklog().getId())
                        .build())
                .build();
    }

    public Goal mapGoalEntityToGoal(GoalEntity entity) {
        return new Goal(entity.getId(), entity.getName(), new Backlog(entity.getBacklog().getId()));
    }
}
