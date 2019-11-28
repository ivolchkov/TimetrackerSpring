package ua.com.timetracker.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/login")
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

    @PostMapping("/signIn")
    public String signIn(HttpSession session,
                         @RequestParam(name = "email") String email,
                         @RequestParam(name = "password") String password) {
        User user = userService.login(email, password);
        session.setAttribute("user", user);
        Role role = user.getRole();

        switch (role) {
            case ADMIN:
                return "redirect:/admin-service";
            case DEVELOPER:
                return "redirect:/developer-service";
            case SCRUM_MASTER:
                return "redirect:/scrum-master-service";
            default:
                return "redirect:/index";
        }
    }

    @GetMapping("/signOut")
    public String signOut(HttpSession session) {
        session.invalidate();

        return "redirect:/index";
    }
}
