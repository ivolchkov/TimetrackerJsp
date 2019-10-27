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

    public Goal(Integer id) {
        this.id = id;
    }

    public Goal(String name, Backlog backlog) {
        this.name = name;
        this.backlog = backlog;
    }

    public Goal(Integer id, String name, Backlog backlog) {
        this(name, backlog);
        this.id = id;
    }

    public Goal(Integer id, String name, Backlog backlog, List<Story> stories) {
        this(id, name, backlog);
        this.stories = stories;
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

    public Goal setStories(List<Story> stories) {
        this.stories = stories;
        return this;
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
}
