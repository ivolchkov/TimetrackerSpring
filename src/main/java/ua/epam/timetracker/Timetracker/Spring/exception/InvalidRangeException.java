package ua.epam.timetracker.Timetracker.Spring.exception;

public class InvalidRangeException extends RuntimeException {
    public InvalidRangeException() {
    }

    public InvalidRangeException(String message) {
        super(message);
    }

    public InvalidRangeException(String message, Throwable cause) {
        super(message, cause);
    }
}
