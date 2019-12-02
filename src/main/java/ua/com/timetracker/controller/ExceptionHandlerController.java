package ua.com.timetracker.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import ua.com.timetracker.domain.User;
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
    public String handleException() {
        return "error-spring";
    }

    @ExceptionHandler({AlreadyRegisteredException.class, InvalidRegistrationException.class})
    public ModelAndView handleRegisteredException() {
        ModelAndView modelAndView = new ModelAndView("redirect:/register");
        modelAndView.addObject("registerError", true);
        modelAndView.addObject("user", new User());

        return modelAndView;
    }

}
