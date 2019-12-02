package com.ua.timetracking.domain;

import java.util.Objects;

public class Backlog {
    private final Integer id;
    private final String projectName;
    private final String description;

    public Backlog(Integer id) {
        this.id = id;
        this.projectName = null;
        this.description = null;
    }

    public Backlog(String projectName, String description) {
        this.id = null;
        this.projectName = projectName;
        this.description = description;
    }

    public Backlog(Integer id, String projectName, String description) {
        this.id = id;
        this.projectName = projectName;
        this.description = description;
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
