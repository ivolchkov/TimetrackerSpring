package ua.com.timetracker.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ua.com.timetracker.configuration.LoginSuccessHandler;
import ua.com.timetracker.domain.Role;
import ua.com.timetracker.domain.User;
import ua.com.timetracker.service.UserService;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @MockBean
    private LoginSuccessHandler handler;

    @Test
    public void mainShouldReturnMainPage() throws Exception {
        mvc.perform(get("/index"))
                .andExpect(view().name("index"))
                .andExpect(status().isOk());
    }

    @Test
    public void loginShouldReturnLoginPage() throws Exception {
        mvc.perform(get("/login"))
                .andExpect(view().name("sign-in"))
                .andExpect(status().isOk());
    }

    @Test
    public void signUpShouldSignUp() throws Exception {
        User user = createUser();

        mvc.perform(post("/signUp")
                .flashAttr("user", user)
                .param("repeatedPassword", "Vfkmdbyf1997")
                .param("role", "DEVELOPER"))
                .andExpect(view().name("redirect:/index"));

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);

        verify(userService).register(userCaptor.capture());

        assertThat(userCaptor.getValue(), is(user));
    }

    @Test
    public void signUpShouldNotSignUp() throws Exception {
        User user = User.builder().id(1).build();

        mvc.perform(post("/signUp")
                .flashAttr("user", user)
                .param("repeatedPassword", "Vfkmdbyf1997")
                .param("role", "DEVELOPER"))
                .andExpect(view().name("register"));
    }


    private static User createUser() {
        return User.builder()
                .name("Igor")
                .surname("Volchkov")
                .email("igorik@gmail.com")
                .password("Vfkmdbyf1997")
                .role(Role.DEVELOPER)
                .build();
    }
}