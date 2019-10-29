package project.domain.backlog;

import project.domain.goal.Goal;
import project.domain.user.User;

import java.util.List;
import java.util.Objects;

public class Backlog {
    private final Integer id;
    private final String projectName;
    private final String description;
    private final List<Goal> goals;
    private final List<User> users;

    private Backlog(BacklogBuilder builder) {
        this.id = builder.id;
        this.projectName = builder.projectName;
        this.description = builder.description;
        this.goals = builder.goals;
        this.users = builder.users;
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

    public List<Goal> getGoals() {
        return goals;
    }

    public List<User> getUsers() {
        return users;
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
                Objects.equals(description, backlog.description) &&
                Objects.equals(goals, backlog.goals) &&
                Objects.equals(users, backlog.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, projectName, description, goals, users);
    }

    @Override
    public String toString() {
        return "Backlog № " + id + ", project name: " + projectName + ", description: " + description;
    }

    public static BacklogBuilder builder() {
        return new BacklogBuilder();
    }


    public static final class BacklogBuilder {
        private Integer id;
        private String projectName;
        private String description;
        private List<Goal> goals;
        private List<User> users;

        private BacklogBuilder() {
        }

        public BacklogBuilder withId(Integer id) {
            this.id = id;
            return this;
        }

        public BacklogBuilder withProjectName(String projectName) {
            this.projectName = projectName;
            return this;
        }

        public BacklogBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public BacklogBuilder withGoals(List<Goal> goals) {
            this.goals = goals;
            return this;
        }

        public BacklogBuilder withUsers(List<User> users) {
            this.users = users;
            return this;
        }

        public Backlog build() {
            return new Backlog(this);
        }
    }
}
