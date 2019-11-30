package ua.com.timetracker.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.com.timetracker.domain.Role;
import ua.com.timetracker.domain.User;
import ua.com.timetracker.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Objects;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@Controller
public class UserController {
    private final UserService userService;

    @GetMapping("/index")
    public String main() {
        return "index";
    }

    @RequestMapping(value = "/login")
    public String login() {
        return "sign-in";
    }

    @GetMapping("/register")
    public ModelAndView register() {
        ModelAndView mav = new ModelAndView();

        mav.addObject("user", new User());
        mav.setViewName("register");

        return mav;
    }

    @PostMapping("/signUp")
    public ModelAndView signUp(@Valid User user, BindingResult bindingResult,
                               @RequestParam(name = "repeatedPassword") String repeatedPassword,
                               @RequestParam(name = "role") String roleName) {
        ModelAndView mav = new ModelAndView();

        if (bindingResult.hasErrors()) {
            mav.setViewName("register");
        } else if (!Objects.equals(user.getPassword(), repeatedPassword)) {
            mav.addObject("confirmationError", true);
            mav.setViewName("register");
        } else {
            Role role = Role.valueOfName(roleName);
            user.setRole(role);
            userService.register(user);
            mav.setViewName("redirect:/index");
        }

        return mav;
    }

    @GetMapping("/signOut")
    public String signOut(HttpSession session) {
        session.invalidate();

        return "redirect:/index";
    }
}

