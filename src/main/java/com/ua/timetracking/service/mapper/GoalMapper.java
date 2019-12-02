package com.ua.timetracking.service.mapper;

import com.ua.timetracking.domain.Backlog;
import com.ua.timetracking.domain.Goal;
import com.ua.timetracking.entity.BacklogEntity;
import com.ua.timetracking.entity.GoalEntity;

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
