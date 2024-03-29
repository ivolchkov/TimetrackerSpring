package ua.com.timetracker.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.ModelAndView;
import ua.com.timetracker.configuration.LoginSuccessHandler;
import ua.com.timetracker.configuration.SecurityConfiguration;
import ua.com.timetracker.domain.User;
import ua.com.timetracker.service.StoryService;
import ua.com.timetracker.service.UserService;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@WebMvcTest(value = DeveloperController.class, excludeAutoConfiguration = SecurityConfiguration.class)
@WithMockUser(username="igorik@gmail.com", authorities="DEVELOPER")
public class DeveloperControllerTest {
    private static final User USER = User.builder().id(1).build();

    @Autowired
    private MockMvc mvc;

    @MockBean
    private StoryService storyService;

    @MockBean
    private UserService userService;

    @MockBean
    private LoginSuccessHandler handler;

    @Test
    public void freeStoriesShouldShowFreeStories() throws Exception {
        when(storyService.showStoriesWithoutUser(anyInt(), anyInt())).thenReturn(Collections.emptyList());
        when(storyService.showNumberOfRowsWithoutUser()).thenReturn(20L);

        ModelAndView mav = mvc.perform(get("/free-stories")
                .param("currentPage", "1")
                .param("recordsPerPage", "10")
                .sessionAttr("user", USER))
                .andExpect(view().name("freeStories"))
                .andReturn().getModelAndView();

        Map<String, Object> model = Objects.requireNonNull(mav).getModel();

        assertThat(model.get("stories"), is(Collections.emptyList()));
        assertThat(model.get("currentPage"), is(1));
        assertThat(model.get("recordsPerPage"), is(10));
        assertThat(model.get("numberOfPages"), is(2L));

        verify(storyService).showNumberOfRowsWithoutUser();
        verify(storyService).showStoriesWithoutUser(anyInt(), anyInt());
    }

    @Test
    public void addStoryShouldAddStory() throws Exception {
        when(storyService.showStoriesWithoutUser(anyInt(), anyInt())).thenReturn(Collections.emptyList());
        when(storyService.showNumberOfRowsWithoutUser()).thenReturn(20L);

        ModelAndView mav = mvc.perform(post("/add-story")
                .param("userId", "1")
                .param("storyId", "10")
                .param("currentPage", "1")
                .param("recordsPerPage", "10")
                .sessionAttr("user", USER))
                .andExpect(view().name("redirect:/free-stories"))
                .andReturn().getModelAndView();

        Map<String, Object> model = Objects.requireNonNull(mav).getModel();

        assertThat(model.get("stories"), is(Collections.emptyList()));
        assertThat(model.get("currentPage"), is(1));
        assertThat(model.get("recordsPerPage"), is(10));
        assertThat(model.get("numberOfPages"), is(2L));

        verify(storyService).showStoriesWithoutUser(anyInt(), anyInt());
        verify(storyService).showNumberOfRowsWithoutUser();
    }
}