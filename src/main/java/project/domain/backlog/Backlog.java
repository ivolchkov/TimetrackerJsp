package project.domain.backlog;

import project.domain.goal.Goal;

import java.util.List;
import java.util.Objects;

public class Backlog {
    private Integer id;
    private String projectName;
    private String description;
    private List<Goal> goals;

    public Backlog(Integer id) {
        this.id = id;
    }

    public Backlog(Integer id, String projectName) {
        this.id = id;
        this.projectName = projectName;
    }

    public Backlog(String projectName, String description) {
        this.projectName = projectName;
        this.description = description;
    }

    public Backlog(Integer id, String projectName, String description) {
        this(id, projectName);
        this.description = description;
    }

    public Backlog(Integer id, String projectName, String description, List<Goal> goals) {
        this(id, projectName, description);
        this.description = description;
        this.goals = goals;
    }

    public Integer getId() {
        return id;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getDescription() {
        return description;
    }

    public Backlog setDescription(String description) {
        this.description = description;
        return this;
    }

    public List<Goal> getGoals() {
        return goals;
    }

    public Backlog setGoals(List<Goal> goals) {
        this.goals = goals;
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

        Backlog backlog = (Backlog) o;

        return Objects.equals(id, backlog.id) &&
                Objects.equals(projectName, backlog.projectName) &&
                Objects.equals(description, backlog.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, projectName, description);
    }

    @Override
    public String toString() {
        return "Backlog № " + id + ", project name: " + projectName + ", description: " + description;
    }
}
