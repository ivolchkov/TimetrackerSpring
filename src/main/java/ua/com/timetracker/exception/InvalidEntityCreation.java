package ua.com.timetracker.exception;

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
