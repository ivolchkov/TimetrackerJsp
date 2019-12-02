package com.ua.timetracking.exception;

public class InvalidEntityCreation extends RuntimeException {
    public InvalidEntityCreation() {
    }

    public InvalidEntityCreation(String message) {
        super(message);
    }

    public InvalidEntityCreation(String message, Throwable cause) {
        super(message, cause);
    }
}
