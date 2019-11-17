package project.service.mapper;

import project.domain.Goal;
import project.domain.Sprint;
import project.domain.Story;
import project.domain.User;
import project.entity.GoalEntity;
import project.entity.SprintEntity;
import project.entity.StoryEntity;
import project.entity.UserEntity;

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

    public StoryEntity mapStoryToStoryEntity(Story domain, Sprint sprint) {
        return StoryEntity.builder()
                .withId(domain.getId())
                .withSprint(SprintEntity.builder()
                        .withId(sprint.getId())
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
