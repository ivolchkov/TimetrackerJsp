package com.ua.timetracking.entity;

import java.util.List;
import java.util.Objects;

public class UserEntity {
    private final Integer id;
    private final String name;
    private final String surname;
    private final String email;
    private final String password;
    private final Role role;
    private final BacklogEntity backlog;
    private final List<StoryEntity> stories;

    private UserEntity(UserBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.surname = builder.surname;
        this.email = builder.email;
        this.password = builder.password;
        this.role = builder.role;
        this.backlog = builder.backlog;
        this.stories = builder.stories;
    }

    public UserEntity(UserEntity user, String password) {
        this.id = user.id;
        this.name = user.name;
        this.surname = user.surname;
        this.email = user.email;
        this.password = password;
        this.role = user.role;
        this.backlog = user.backlog;
        this.stories = user.stories;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public List<StoryEntity> getStories() {
        return stories;
    }

    public BacklogEntity getBacklog() {
        return backlog;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserEntity user = (UserEntity) o;

        return Objects.equals(id, user.id) &&
                Objects.equals(name, user.name) &&
                Objects.equals(surname, user.surname) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                role == user.role &&
                Objects.equals(backlog, user.backlog) &&
                Objects.equals(stories, user.stories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, email, password, role, backlog, stories);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("User № ").append(id).append(", ").
                append("name: ").append(name).append(", ").
                append("surname: ").append(surname).append(", ").
                append("email: ").append(email).append(", ").
                append("role: ").append(role.getDescription());

        return stringBuilder.toString();
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public static class UserBuilder {
        private Integer id;
        private String name;
        private String surname;
        private String email;
        private String password;
        private Role role;
        private BacklogEntity backlog;
        private List<StoryEntity> stories;

        private UserBuilder() {}

        public UserBuilder withId(Integer id) {
            this.id = id;
            return this;
        }

        public UserBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder withSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public UserBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder withPassword(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder withRole(Role role) {
            this.role = role;
            return this;
        }

        public UserBuilder withBacklog(BacklogEntity backlog) {
            this.backlog = backlog;
            return this;
        }

        public UserBuilder withStories(List<StoryEntity> stories) {
            this.stories = stories;
            return this;
        }

        public UserEntity build() {
            return new UserEntity(this);
        }
    }
}
