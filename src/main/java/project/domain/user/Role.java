package project.domain.user;

public enum Role {
    ADMIN("Admin"), CLIENT("Client");

    String description;

    Role(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
