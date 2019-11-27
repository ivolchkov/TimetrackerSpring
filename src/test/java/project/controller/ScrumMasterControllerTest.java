package project.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.ModelAndView;
import project.domain.Backlog;
import project.domain.Goal;
import project.domain.Sprint;
import project.domain.Story;
import project.service.BacklogService;
import project.service.GoalService;
import project.service.SprintService;
import project.service.StoryService;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@WebMvcTest(ScrumMasterController.class)
public class ScrumMasterControllerTest {
    private static final String MAIN_PAGE = "scrum-master-service";

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

    @Test
    public void mainShouldReturnMainPage() throws Exception {
        mvc.perform(get("/" + MAIN_PAGE))
                .andExpect(view().name(MAIN_PAGE))
                .andExpect(status().isOk());
    }


    @Test
    public void createBacklogShouldCreateBacklog() throws Exception {
        Backlog backlog = createBacklog();

        when(backlogService.createBacklog(any(Backlog.class))).thenReturn(backlog);

        mvc.perform(post("/create-backlog")
                .flashAttr("backlog", backlog))
                .andExpect(view().name(MAIN_PAGE));

        ArgumentCaptor<Backlog> backlogCaptor = ArgumentCaptor.forClass(Backlog.class);

        verify(backlogService).createBacklog(backlogCaptor.capture());

        assertThat(backlogCaptor.getValue(), equalTo(backlog));
    }

    @Test
    public void createGoalShouldCreateGoal() throws Exception {
        Goal goal = createGoal();
        Backlog backlog = createBacklog();

        when(goalService.createGoal(any(Goal.class))).thenReturn(goal);
        when(backlogService.showBacklogById(anyInt())).thenReturn(backlog);

        mvc.perform(post("/create-goal")
                .flashAttr("goal", goal)
                .param("backlogId", "1"))
                .andExpect(view().name(MAIN_PAGE));

        ArgumentCaptor<Goal> goalCaptor = ArgumentCaptor.forClass(Goal.class);

        verify(goalService).createGoal(goalCaptor.capture());
        verify(backlogService).showBacklogById(anyInt());
        assertThat(goalCaptor.getValue(), equalTo(goal));
    }

    @Test
    public void createSprintShouldCreateSprint() throws Exception {
        Sprint sprint = Sprint.builder()
                .id(1).name("Test")
                .start(LocalDate.MIN)
                .end(LocalDate.MAX)
                .build();

        when(sprintService.createSprint(any(Sprint.class))).thenReturn(sprint);

        mvc.perform(post("/create-sprint")
                .flashAttr("sprint", sprint))
                .andExpect(view().name(MAIN_PAGE));

        ArgumentCaptor<Sprint> sprintCaptor = ArgumentCaptor.forClass(Sprint.class);

        verify(sprintService).createSprint(sprintCaptor.capture());

        assertThat(sprintCaptor.getValue(), equalTo(sprint));
    }

    @Test
    public void createStoryShouldCreateStory() throws Exception {
        Story story = Story.builder().id(1).name("Test").build();
        Goal goal = createGoal();

        when(storyService.createStory(any(Story.class))).thenReturn(story);
        when(goalService.showGoalById(anyInt())).thenReturn(goal);

        mvc.perform(post("/create-story")
                .flashAttr("story", story)
                .param("goalId", "1"))
                .andExpect(view().name(MAIN_PAGE));

        ArgumentCaptor<Story> storyCaptor = ArgumentCaptor.forClass(Story.class);

        verify(storyService).createStory(storyCaptor.capture());
        verify(goalService).showGoalById(anyInt());
        assertThat(storyCaptor.getValue(), equalTo(story));
    }

    @Test
    public void allBacklogsShouldShowAllProjects() throws Exception {
        when(backlogService.showAllBacklogs(anyInt(), anyInt())).thenReturn(Collections.emptyList());
        when(backlogService.showNumberOfRows()).thenReturn(20L);

        ModelAndView mav = mvc.perform(get("/all-backlogs"))
                .andExpect(view().name("createGoal"))
                .andReturn().getModelAndView();

        Map<String, Object> model = Objects.requireNonNull(mav).getModel();

        assertThat(model.get("backlogs"), equalTo(Collections.emptyList()));
        assertThat(model.get("goal"), equalTo(new Goal()));

        verify(backlogService).showNumberOfRows();
        verify(backlogService).showAllBacklogs(anyInt(), anyInt());
    }

    @Test
    public void allGoalsShouldShowAllGoals() throws Exception {
        when(goalService.showAllGoals(anyInt(), anyInt())).thenReturn(Collections.emptyList());
        when(goalService.showNumberOfRows()).thenReturn(20L);

        ModelAndView mav = mvc.perform(get("/all-goals"))
                .andExpect(view().name("createStory"))
                .andReturn().getModelAndView();

        Map<String, Object> model = Objects.requireNonNull(mav).getModel();

        assertThat(model.get("goals"), equalTo(Collections.emptyList()));
        assertThat(model.get("story"), equalTo(new Story()));

        verify(goalService).showNumberOfRows();
        verify(goalService).showAllGoals(anyInt(), anyInt());
    }

    private static Backlog createBacklog() {
        return Backlog.builder().id(1).projectName("Test").build();
    }

    private static Goal createGoal() {
        return Goal.builder().id(1).name("Test").build();
    }
}