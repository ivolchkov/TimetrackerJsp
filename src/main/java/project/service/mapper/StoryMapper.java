package project.service.mapper;

import project.domain.goal.Goal;
import project.domain.sprint.Sprint;
import project.domain.story.Story;
import project.domain.user.User;
import project.entity.goal.GoalEntity;
import project.entity.sprint.SprintEntity;
import project.entity.story.StoryEntity;
import project.entity.user.UserEntity;

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
                .withName(domain.getName())
                .withSpentTime(domain.getSpentTime())
                .withDescription(domain.getDescription())
                .withStatus(domain.getStatus())
                .withGoal(GoalEntity.builder()
                        .withId(domain.getGoal().getId())
                        .build())
                .withUser(UserEntity.builder()
                        .withId(user.getId())
                        .build())
                .build();
    }

    public StoryEntity mapStoryToStoryEntity(Story domain, Sprint sprint) {
        return StoryEntity.builder()
                .withId(domain.getId())
                .withName(domain.getName())
                .withSpentTime(domain.getSpentTime())
                .withDescription(domain.getDescription())
                .withStatus(domain.getStatus())
                .withGoal(GoalEntity.builder()
                        .withId(domain.getGoal().getId())
                        .build())
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
