package ua.epam.timetracker.Timetracker.Spring.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException() {
    }

    public EntityNotFoundException(String s) {
        super(s);
    }

    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
