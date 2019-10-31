package project.entity.goal;

import project.entity.backlog.BacklogEntity;
import project.entity.story.StoryEntity;

import java.util.List;
import java.util.Objects;

public class GoalEntity {
    private final Integer id;
    private final String name;
    private final BacklogEntity backlog;
    private final List<StoryEntity> stories;

    private GoalEntity(GoalBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.backlog = builder.backlog;
        this.stories = builder.stories;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BacklogEntity getBacklog() {
        return backlog;
    }

    public List<StoryEntity> getStories() {
        return stories;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GoalEntity goal = (GoalEntity) o;

        return Objects.equals(id, goal.id) &&
                Objects.equals(name, goal.name) &&
                Objects.equals(backlog, goal.backlog) &&
                Objects.equals(stories, goal.stories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, backlog, stories);
    }

    @Override
    public String toString() {
        return "Goal № " + id + ", name: " + name;
    }

    public static GoalBuilder builder() {
        return new GoalBuilder();
    }

    public static final class GoalBuilder {
        private Integer id;
        private String name;
        private BacklogEntity backlog;
        private List<StoryEntity> stories;

        private GoalBuilder() {
        }

        public GoalBuilder withId(Integer id) {
            this.id = id;
            return this;
        }

        public GoalBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public GoalBuilder withBacklog(BacklogEntity backlog) {
            this.backlog = backlog;
            return this;
        }

        public GoalBuilder withStories(List<StoryEntity> stories) {
            this.stories = stories;
            return this;
        }

        public GoalEntity build() {
            return new GoalEntity(this);
        }
    }
}
