package project.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import project.domain.*;
import project.service.BacklogService;
import project.service.GoalService;
import project.service.SprintService;
import project.service.StoryService;


import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@Controller("/scrum-master")
public class ScrumMasterController {
    private static final String MAIN_PAGE = "scrum-master-service";

    private final BacklogService backlogService;
    private final GoalService goalService;
    private final SprintService sprintService;
    private final StoryService storyService;

    @GetMapping("/scrum-master-service")
    public String main() {
        return MAIN_PAGE;
    }

    @GetMapping("/project")
    public ModelAndView story() {
        ModelAndView mav = new ModelAndView();

        mav.addObject("backlog", new Backlog());
        mav.setViewName("createProject");

        return mav;
    }

    @GetMapping("/sprint")
    public ModelAndView sprint() {
        ModelAndView mav = new ModelAndView();

        mav.addObject("sprint", new Sprint());
        mav.setViewName("createSprint");

        return mav;
    }

    @PostMapping("/create-backlog")
    public String createBacklog(@Valid Backlog backlog, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "createProject";
        }

        backlogService.createBacklog(backlog);

        return MAIN_PAGE;
    }

    @PostMapping("/create-goal")
    public String createGoal(@Valid Goal goal, BindingResult bindingResult, @RequestParam("backlogId") Integer backlogId) {
        if (bindingResult.hasErrors()) {
            return "createGoal";
        }
        Backlog project = backlogService.showBacklogById(backlogId);
        goal.setBacklog(project);

        goalService.createGoal(goal);

        return MAIN_PAGE;
    }

    @PostMapping("/create-sprint")
    public String createSprint(@Valid Sprint sprint, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "createSprint";
        }

        sprintService.createSprint(sprint);

        return MAIN_PAGE;
    }

    @PostMapping("/create-story")
    public String createStory(@Valid Story story, BindingResult bindingResult, @RequestParam("goalId") Integer goalId) {
        if (bindingResult.hasErrors()) {
            return "createStory";
        }

        Goal goal = goalService.showGoalById(goalId);
        story.setGoal(goal);
        story.setStatus(Status.TO_DO);

        storyService.createStory(story);

        return MAIN_PAGE;
    }

    @GetMapping("/all-backlogs")
    public ModelAndView allBacklogs() {
        ModelAndView modelAndView = new ModelAndView();
        Goal goal = new Goal();
        int recordsPerPage = (int) backlogService.showNumberOfRows();

        List<Backlog> backlogs = backlogService.showAllBacklogs(0, recordsPerPage);

        modelAndView.addObject("goal", goal);
        modelAndView.addObject("backlogs", backlogs);
        modelAndView.setViewName("createGoal");

        return modelAndView;
    }

    @GetMapping("/all-goals")
    public ModelAndView allGoals() {
        ModelAndView modelAndView = new ModelAndView();
        Story story = new Story();
        int recordsPerPage = (int) goalService.showNumberOfRows();

        List<Goal> goals = goalService.showAllGoals(0, recordsPerPage);

        modelAndView.addObject("story", story);
        modelAndView.addObject("goals", goals);
        modelAndView.setViewName("createStory");

        return modelAndView;
    }
}
