package com.ua.timetracking.exception;

public class InvalidEncodingException extends RuntimeException {
    public InvalidEncodingException() {
    }

    public InvalidEncodingException(String message) {
        super(message);
    }

    public InvalidEncodingException(String message, Throwable cause) {
        super(message, cause);
    }
}
