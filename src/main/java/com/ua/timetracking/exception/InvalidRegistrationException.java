package com.ua.timetracking.exception;

public class InvalidRegistrationException extends RuntimeException {
    public InvalidRegistrationException(String s) {
        super(s);
    }

    public InvalidRegistrationException(String message, Throwable cause) {
        super(message, cause);
    }
}
