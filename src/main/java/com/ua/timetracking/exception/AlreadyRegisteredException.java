package com.ua.timetracking.exception;

public class AlreadyRegisteredException extends RuntimeException {
    public AlreadyRegisteredException() {
    }

    public AlreadyRegisteredException(String s) {
        super(s);
    }

    public AlreadyRegisteredException(String message, Throwable cause) {
        super(message, cause);
    }
}
