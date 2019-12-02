package com.ua.timetracking.service.mapper;

import com.ua.timetracking.domain.Goal;
import com.ua.timetracking.domain.Story;
import com.ua.timetracking.domain.User;
import com.ua.timetracking.entity.GoalEntity;
import com.ua.timetracking.entity.StoryEntity;
import com.ua.timetracking.entity.UserEntity;

public class StoryMapper {
    public StoryEntity mapStoryToStoryEntity(Story domain) {
        return StoryEntity.builder()
                .withName(domain.getName())
                .withSpentTime(domain.getSpentTime())
                .withDescription(domain.getDescription())
                .withStatus(domain.getStatus())
                .withGoal(GoalEntity.builder()
                        .withId(domain.getGoal().getId())
                        .build())
                .build();
    }

    public StoryEntity mapStoryToStoryEntity(Story domain, User user) {
        return StoryEntity.builder()
                .withId(domain.getId())
                .withUser(UserEntity.builder()
                        .withId(user.getId())
                        .build())
                .build();
    }

    public Story mapStoryEntityToStory(StoryEntity entity) {
        return Story.builder()
                .withId(entity.getId())
                .withName(entity.getName())
                .withSpentTime(entity.getSpentTime())
                .withDescription(entity.getDescription())
                .withStatus(entity.getStatus())
                .withGoal(new Goal(entity.getGoal().getId()))
                .build();
    }
}
