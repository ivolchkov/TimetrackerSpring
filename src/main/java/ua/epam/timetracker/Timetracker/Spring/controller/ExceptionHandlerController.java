package ua.epam.timetracker.Timetracker.Spring.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ua.epam.timetracker.Timetracker.Spring.exception.*;

import java.sql.SQLException;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(SQLException.class)
    public String handleSQLException() {
        return "error";
    }

    @ExceptionHandler(NullPointerException.class)
    public String handleNullPointerException() {
        return "error";
    }

    @ExceptionHandler(InvalidEntityUpdating.class)
    public String handleInvalidEntityUpdating() {
        return "error";
    }

    @ExceptionHandler(InvalidPaginatingException.class)
    public String handleInvalidPaginatingException() {
        return "error";
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
        return "register";
    }

    @ExceptionHandler(Throwable.class)
    public String handleThrowable() {
        return "error";
    }
}
