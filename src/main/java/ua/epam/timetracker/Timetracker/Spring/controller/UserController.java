package ua.epam.timetracker.Timetracker.Spring.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ua.epam.timetracker.Timetracker.Spring.domain.Role;
import ua.epam.timetracker.Timetracker.Spring.domain.User;
import ua.epam.timetracker.Timetracker.Spring.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@Controller("/user")
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
    public String register() {
        return "register";
    }

    @PostMapping("/signUp")
    public String signUp(HttpServletRequest request) {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String repeatedPassword = request.getParameter("repeatedPassword");
        Role role = Role.valueOfName(request.getParameter("role"));

        if (!Objects.equals(password, repeatedPassword)) {
            return "register";
        }

        User user = User.builder()
                .name(name)
                .surname(surname)
                .email(email)
                .password(password)
                .role(role)
                .build();

        userService.register(user);

        return "index";
    }

    @PostMapping("/signIn")
    public String signIn(HttpServletRequest request) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = userService.login(email, password);
        request.getSession().setAttribute("user", user);
        Role role = user.getRole();

        switch (role) {
            case ADMIN:
                return "admin-service";
            case DEVELOPER:
                return "developer-service";
            case SCRUM_MASTER:
                return "scrum-master-service";
            default:
                return "index";
        }
    }

    @GetMapping("/signOut")
    public String signOut(HttpSession session) {
        session.invalidate();

        return "index";
    }
}
