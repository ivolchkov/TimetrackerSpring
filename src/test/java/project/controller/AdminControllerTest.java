package project.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.ModelAndView;
import project.service.*;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@WebMvcTest(AdminController.class)
public class AdminControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private BacklogService backlogService;

    @MockBean
    private GoalService goalService;

    @MockBean
    private StoryService storyService;

    @MockBean
    private SprintService sprintService;

    @MockBean
    private UserService userService;

    @Test
    public void mainShouldReturnMainPage() throws Exception {
        mvc.perform(get("/admin-service"))
                .andExpect(view().name("admin-service"))
                .andExpect(status().isOk());
    }

    @Test
    public void projectsShouldShowAllProjects() throws Exception {
        when(backlogService.showAllBacklogs(anyInt(), anyInt())).thenReturn(Collections.emptyList());
        when(backlogService.showNumberOfRows()).thenReturn(20L);

        ModelAndView mav = mvc.perform(get("/backlogs")
                .param("currentPage", "1")
                .param("recordsPerPage", "10"))
                .andExpect(view().name("showProjects"))
                .andReturn().getModelAndView();

        Map<String, Object> model = Objects.requireNonNull(mav).getModel();

        assertThat(model.get("backlogs"), equalTo(Collections.emptyList()));
        assertThat(model.get("currentPage"), equalTo(1));
        assertThat(model.get("recordsPerPage"), equalTo(10));
        assertThat(model.get("numberOfPages"), equalTo(2L));

        verify(backlogService).showNumberOfRows();
        verify(backlogService).showAllBacklogs(anyInt(), anyInt());
    }

    @Test
    public void goalsShouldShowAllGoals() throws Exception {
        when(goalService.showAllGoals(anyInt(), anyInt())).thenReturn(Collections.emptyList());
        when(goalService.showNumberOfRows()).thenReturn(20L);

        ModelAndView mav = mvc.perform(get("/goals")
                .param("currentPage", "1")
                .param("recordsPerPage", "10"))
                .andExpect(view().name("showGoals"))
                .andReturn().getModelAndView();

        Map<String, Object> model = Objects.requireNonNull(mav).getModel();

        assertThat(model.get("goals"), equalTo(Collections.emptyList()));
        assertThat(model.get("currentPage"), equalTo(1));
        assertThat(model.get("recordsPerPage"), equalTo(10));
        assertThat(model.get("numberOfPages"), equalTo(2L));

        verify(goalService).showNumberOfRows();
        verify(goalService).showAllGoals(anyInt(), anyInt());
    }

    @Test
    public void storiesShouldShowAllStories() throws Exception {
        when(storyService.showAllStories(anyInt(), anyInt())).thenReturn(Collections.emptyList());
        when(storyService.showNumberOfRows()).thenReturn(20L);

        ModelAndView mav = mvc.perform(get("/stories")
                .param("currentPage", "1")
                .param("recordsPerPage", "10"))
                .andExpect(view().name("showStories"))
                .andReturn().getModelAndView();

        Map<String, Object> model = Objects.requireNonNull(mav).getModel();

        assertThat(model.get("stories"), equalTo(Collections.emptyList()));
        assertThat(model.get("currentPage"), equalTo(1));
        assertThat(model.get("recordsPerPage"), equalTo(10));
        assertThat(model.get("numberOfPages"), equalTo(2L));

        verify(storyService).showNumberOfRows();
        verify(storyService).showAllStories(anyInt(), anyInt());
    }

    @Test
    public void usersShouldShowAllUsers() throws Exception {
        when(userService.findAll(anyInt(), anyInt())).thenReturn(Collections.emptyList());
        when(userService.showNumberOfRows()).thenReturn(20L);

        ModelAndView mav = mvc.perform(get("/users")
                .param("currentPage", "1")
                .param("recordsPerPage", "10"))
                .andExpect(view().name("showUsers"))
                .andReturn().getModelAndView();

        Map<String, Object> model = Objects.requireNonNull(mav).getModel();

        assertThat(model.get("users"), equalTo(Collections.emptyList()));
        assertThat(model.get("currentPage"), equalTo(1));
        assertThat(model.get("recordsPerPage"), equalTo(10));
        assertThat(model.get("numberOfPages"), equalTo(2L));

        verify(userService).showNumberOfRows();
        verify(userService).findAll(anyInt(), anyInt());
    }

    @Test
    public void sprintsShouldShowAllSprints() throws Exception {
        when(sprintService.showAllSprints(anyInt(), anyInt())).thenReturn(Collections.emptyList());
        when(sprintService.showNumberOfRows()).thenReturn(20L);

        ModelAndView mav = mvc.perform(get("/sprints")
                .param("currentPage", "1")
                .param("recordsPerPage", "10"))
                .andExpect(view().name("showSprints"))
                .andReturn().getModelAndView();

        Map<String, Object> model = Objects.requireNonNull(mav).getModel();

        assertThat(model.get("sprints"), equalTo(Collections.emptyList()));
        assertThat(model.get("currentPage"), equalTo(1));
        assertThat(model.get("recordsPerPage"), equalTo(10));
        assertThat(model.get("numberOfPages"), equalTo(2L));

        verify(sprintService).showNumberOfRows();
        verify(sprintService).showAllSprints(anyInt(), anyInt());
    }
}