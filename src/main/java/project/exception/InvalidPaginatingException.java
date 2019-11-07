package project.exception;

public class InvalidPaginatingException extends RuntimeException {
    public InvalidPaginatingException() {
    }

    public InvalidPaginatingException(String message) {
        super(message);
    }

    public InvalidPaginatingException(String message, Throwable cause) {
        super(message, cause);
    }
}
