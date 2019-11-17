package ua.epam.timetracker.Timetracker.Spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ua.epam.timetracker.Timetracker.Spring.domain.User;
import ua.epam.timetracker.Timetracker.Spring.service.UserService;

import javax.validation.Valid;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = {"/index"})
    public ModelAndView main() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @GetMapping(value = {"/login"})
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("sign-in");
        return modelAndView;
    }

    @GetMapping(value = {"/register"})
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("register");
        return modelAndView;
    }

    @PostMapping("/signUp")
    public String signUp(@Valid User user, BindingResult result) {
//        if (result.hasErrors()) {
//            return "add-user";
//        }

        userService.register(user);
        return "index";
    }

    @PostMapping("/signIn")
    public String signIn(@Valid User user, BindingResult result) {
//        if (result.hasErrors()) {
//            return "add-user";
//        }

        userService.login(user.getEmail(), user.getPassword());
        return "index";
    }
}
