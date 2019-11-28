package ua.com.timetracker.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ua.com.timetracker.exception.*;

import java.sql.SQLException;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(SQLException.class)
    public String handleSQLException() {
        return "error-spring";
    }

    @ExceptionHandler(NullPointerException.class)
    public String handleNullPointerException() {
        return "error-spring";
    }

    @ExceptionHandler(InvalidEntityUpdating.class)
    public String handleInvalidEntityUpdating() {
        return "error-spring";
    }

    @ExceptionHandler(InvalidPaginatingException.class)
    public String handleInvalidPaginatingException() {
        return "error-spring";
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public String handleEntityNotFoundException() {
        return "sign-in";
    }

    @ExceptionHandler(InvalidRegistrationException.class)
    public String handleInvalidRegistrationException() {
        return "register";
    }

    @ExceptionHandler(AlreadyRegisteredException.class)
    public String handleAlreadyRegisteredException() {
        return "register";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgumentException() {
        return "error-spring";
    }

    @ExceptionHandler(Throwable.class)
    public String handleThrowable() {
        return "error-spring";
    }
}
