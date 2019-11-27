package project.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import project.domain.Role;
import project.domain.User;
import project.service.UserService;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
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

        assertThat(userCaptor.getValue(), equalTo(user));
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

    @Test
    public void signInShouldSignIn() throws Exception {
        User user = createUser();
        when(userService.login(anyString(), anyString())).thenReturn(user);

        mvc.perform(post("/signIn")
                .param("email", "igorik")
                .param("password", "Vfkmdbyf1997"))
                .andExpect(view().name("redirect:/developer-service"));
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