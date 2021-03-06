package com.ua.timetracking.service.mapper;

import com.ua.timetracking.domain.Backlog;
import com.ua.timetracking.entity.BacklogEntity;

public class BacklogMapper {
    public BacklogEntity mapBacklogToBacklogEntity(Backlog domain) {
        return BacklogEntity.builder()
                .withProjectName(domain.getProjectName())
                .withDescription(domain.getDescription())
                .build();
    }

    public Backlog mapBacklogEntityToBacklog(BacklogEntity entity) {
        return new Backlog(entity.getId(), entity.getProjectName(), entity.getDescription());
    }
}
