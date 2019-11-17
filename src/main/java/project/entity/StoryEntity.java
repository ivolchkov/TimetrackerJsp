package project.entity;

import java.time.LocalTime;
import java.util.Objects;

public class StoryEntity {
    private final Integer id;
    private final String name;
    private final LocalTime spentTime;
    private final String description;
    private final Status status;
    private final GoalEntity goal;
    private final UserEntity user;
    private final SprintEntity sprint;

    private StoryEntity(StoryBuilder builder) {
        id = builder.id;
        name = builder.name;
        spentTime = builder.spentTime;
        description = builder.description;
        status = builder.status;
        goal = builder.goal;
        user = builder.user;
        sprint = builder.sprint;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalTime getSpentTime() {
        return spentTime;
    }

    public String getDescription() {
        return description;
    }

    public Status getStatus() {
        return status;
    }

    public GoalEntity getGoal() {
        return goal;
    }

    public UserEntity getUser() {
        return user;
    }

    public SprintEntity getSprint() {
        return sprint;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        StoryEntity story = (StoryEntity) o;

        return Objects.equals(id, story.id) &&
                Objects.equals(name, story.name) &&
                Objects.equals(spentTime, story.spentTime) &&
                Objects.equals(description, story.description) &&
                status == story.status &&
                Objects.equals(goal, story.goal) &&
                Objects.equals(user, story.user) &&
                Objects.equals(sprint, story.sprint);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, spentTime, description, status, goal, user, sprint);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Story № ").append(id).append(", ").
                append("name: ").append(name).append(", ").
                append("spent time: ").append(spentTime).append(", ").
                append("status: ").append(status.getDescription()).append(", ").
                append("description: ").append(description);

        return stringBuilder.toString();
    }

    public static StoryBuilder builder() {
        return new StoryBuilder();
    }

    public static final class StoryBuilder {
        private Integer id;
        private String name;
        private LocalTime spentTime;
        private String description;
        private Status status;
        private GoalEntity goal;
        private UserEntity user;
        private SprintEntity sprint;

        private StoryBuilder() {
        }

        public StoryBuilder withId(Integer id) {
            this.id = id;
            return this;
        }

        public StoryBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public StoryBuilder withSpentTime(LocalTime spentTime) {
            this.spentTime = spentTime;
            return this;
        }

        public StoryBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public StoryBuilder withStatus(Status status) {
            this.status = status;
            return this;
        }

        public StoryBuilder withGoal(GoalEntity goal) {
            this.goal = goal;
            return this;
        }

        public StoryBuilder withUser(UserEntity user) {
            this.user = user;
            return this;
        }

        public StoryBuilder withSprint(SprintEntity sprint) {
            this.sprint = sprint;
            return this;
        }

        public StoryEntity build() {
            return new StoryEntity(this);
        }
    }
}
