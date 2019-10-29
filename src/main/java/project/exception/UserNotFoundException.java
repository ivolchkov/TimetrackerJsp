package project.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
    }

    public UserNotFoundException(String s) {
        super(s);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
