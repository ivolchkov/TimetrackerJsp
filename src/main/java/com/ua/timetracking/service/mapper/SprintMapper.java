package com.ua.timetracking.service.mapper;

import com.ua.timetracking.domain.Sprint;
import com.ua.timetracking.entity.SprintEntity;

public class SprintMapper {
    public SprintEntity mapSprintToSprintEntity(Sprint domain) {
        return SprintEntity.builder()
                .withName(domain.getName())
                .withStart(domain.getStart())
                .withEnd(domain.getEnd())
                .withDescription(domain.getDescription())
                .build();
    }

    public Sprint mapSprintEntityToSprint(SprintEntity entity) {
        return Sprint.builder()
                .withId(entity.getId())
                .withName(entity.getName())
                .withStart(entity.getStart())
                .withEnd(entity.getEnd())
                .withDescription(entity.getDescription())
                .build();
    }
}
