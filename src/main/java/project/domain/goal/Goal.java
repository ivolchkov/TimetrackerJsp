package project.domain.goal;

import project.domain.backlog.Backlog;
import project.domain.story.Story;

import java.util.List;
import java.util.Objects;

public class Goal {
    private Integer id;
    private String name;
    private Backlog backlog;
    private List<Story> stories;

    private Goal(GoalBuilder builder) {
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

    public Backlog getBacklog() {
        return backlog;
    }

    public List<Story> getStories() {
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

        Goal goal = (Goal) o;

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
        private Backlog backlog;
        private List<Story> stories;

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

        public GoalBuilder withBacklog(Backlog backlog) {
            this.backlog = backlog;
            return this;
        }

        public GoalBuilder withStories(List<Story> stories) {
            this.stories = stories;
            return this;
        }

        public Goal build() {
            return new Goal(this);
        }
    }
}
