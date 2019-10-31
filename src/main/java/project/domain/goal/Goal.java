package project.domain.goal;

import project.domain.backlog.Backlog;

import java.util.Objects;

public class Goal {
    private final Integer id;
    private final String name;
    private final Backlog backlog;

    public Goal(Integer id) {
        this.id = id;
        this.name = null;
        this.backlog = null;
    }

    public Goal(Integer id, String name, Backlog backlog) {
        this.id = id;
        this.name = name;
        this.backlog = backlog;
    }

    public Integer getId() {
        return id;
    }

    public Backlog getBacklog() {
        return backlog;
    }

    public String getName() {
        return name;
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
                Objects.equals(backlog, goal.backlog);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, backlog);
    }

    @Override
    public String toString() {
        return "Goal № " + id + ", name: " + name;
    }

}
