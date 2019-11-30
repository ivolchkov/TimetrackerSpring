package ua.com.timetracker.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ua.com.timetracker.exception.*;

import java.sql.SQLException;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler({SQLException.class,
            NullPointerException.class,
            InvalidEntityUpdating.class,
            InvalidPaginatingException.class,
            IllegalArgumentException.class,
            Throwable.class})
    public String handleSQLException() {
        return "error-spring";
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public String handleEntityNotFoundException() {
        return "sign-in";
    }

    @ExceptionHandler({AlreadyRegisteredException.class, InvalidRegistrationException.class})
    public String handleRegisteredException() {
        return "register";
    }

}
