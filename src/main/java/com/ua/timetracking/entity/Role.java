package com.ua.timetracking.entity;

import java.util.Arrays;

public enum Role {
    ADMIN("Admin"), DEVELOPER("Developer"), SCRUM_MASTER("Scrum master");

    String description;

    Role(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public static Role valueOfName(String name) {
        return Arrays.stream(values())
                .filter(x -> x.name().equalsIgnoreCase(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Value of name role is null or there are no match by this name"));
    }
}
