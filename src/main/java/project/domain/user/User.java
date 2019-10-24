package project.domain.user;

import project.domain.Entity;

import java.util.Objects;

public class User extends Entity {
    private final String name;
    private final String surname;
    private final String email;
    private final String password;
    private final Role role;

    private User(UserBuilder builder) {
        super(builder.id);
        this.name = builder.name;
        this.surname = builder.surname;
        this.email = builder.email;
        this.password = builder.password;
        this.role = builder.role;
    }

    public User(User user, String password) {
        super(user.getId());
        this.name = user.name;
        this.surname = user.surname;
        this.email = user.email;
        this.password = password;
        this.role = user.role;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        if (!super.equals(o)) {
            return false;
        }

        User user = (User) o;
        return Objects.equals(name, user.name) &&
                Objects.equals(surname, user.surname) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, surname, email, password, role);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("User № ").append(super.getId()).append(", ").
                append("name: ").append(name).append(", ").
                append("surname: ").append(surname).append(", ").
                append("email: ").append(email).
                append("role: ").append(role.getDescription());

        return stringBuilder.toString();
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public static class UserBuilder {
        private Long id;
        private String name;
        private String surname;
        private String email;
        private String password;
        private Role role;

        private UserBuilder() {}

        public UserBuilder withId(Long id) {
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

        public User build() {
            return new User(this);
        }
    }
}
