package project.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.ModelAndView;
import project.domain.User;
import project.service.StoryService;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@WebMvcTest(DeveloperController.class)
public class DeveloperControllerTest {
    private static final User USER = User.builder().id(1).build();

    @Autowired
    private MockMvc mvc;

    @MockBean
    private StoryService storyService;

    @Test
    public void mainShouldReturnMainPage() throws Exception {
        mvc.perform(get("/developer-service").sessionAttr("user", USER))
                .andExpect(view().name("developer-service"))
                .andExpect(status().isOk());
    }

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

        assertThat(model.get("stories"), equalTo(Collections.emptyList()));
        assertThat(model.get("currentPage"), equalTo(1));
        assertThat(model.get("recordsPerPage"), equalTo(10));
        assertThat(model.get("numberOfPages"), equalTo(2L));

        verify(storyService).showNumberOfRowsWithoutUser();
        verify(storyService).showStoriesWithoutUser(anyInt(), anyInt());
    }

    @Test
    public void developerStoriesShouldShowDeveloperStories() throws Exception {
        when(storyService.showStoryByUser(anyInt(), anyInt(), anyInt())).thenReturn(Collections.emptyList());
        when(storyService.showNumberOfRowsByUserId(anyInt())).thenReturn(20L);

        ModelAndView mav = mvc.perform(get("/developer-stories")
                .param("id", "1")
                .param("currentPage", "1")
                .param("recordsPerPage", "10")
                .sessionAttr("user", USER))
                .andExpect(view().name("showDeveloperStories"))
                .andReturn().getModelAndView();

        Map<String, Object> model = Objects.requireNonNull(mav).getModel();

        assertThat(model.get("stories"), equalTo(Collections.emptyList()));
        assertThat(model.get("currentPage"), equalTo(1));
        assertThat(model.get("recordsPerPage"), equalTo(10));
        assertThat(model.get("numberOfPages"), equalTo(2L));

        verify(storyService).showNumberOfRowsByUserId(anyInt());
        verify(storyService).showStoryByUser(anyInt(), anyInt(), anyInt());
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

        assertThat(model.get("stories"), equalTo(Collections.emptyList()));
        assertThat(model.get("currentPage"), equalTo(1));
        assertThat(model.get("recordsPerPage"), equalTo(10));
        assertThat(model.get("numberOfPages"), equalTo(2L));

        verify(storyService).showStoriesWithoutUser(anyInt(), anyInt());
        verify(storyService).showNumberOfRowsWithoutUser();
    }

}