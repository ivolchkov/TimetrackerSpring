package ua.epam.timetracker.Timetracker.Spring.exception;

public class InvalidEntityUpdating extends RuntimeException {
    public InvalidEntityUpdating() {
    }

    public InvalidEntityUpdating(String message) {
        super(message);
    }

    public InvalidEntityUpdating(String message, Throwable cause) {
        super(message, cause);
    }
}
