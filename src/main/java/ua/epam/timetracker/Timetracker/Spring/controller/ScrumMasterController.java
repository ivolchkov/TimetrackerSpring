package ua.epam.timetracker.Timetracker.Spring.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.epam.timetracker.Timetracker.Spring.domain.*;
import ua.epam.timetracker.Timetracker.Spring.service.BacklogService;
import ua.epam.timetracker.Timetracker.Spring.service.GoalService;
import ua.epam.timetracker.Timetracker.Spring.service.SprintService;
import ua.epam.timetracker.Timetracker.Spring.service.StoryService;
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
    public String story() {
        return "createProject";
    }

    @GetMapping("/sprint")
    public String sprint() {
        return "createSprint";
    }

    @PostMapping("/create-backlog")
    public String createBacklog(@RequestParam String projectName, @RequestParam String description) {
        Backlog backlog = Backlog.builder().
                projectName(projectName).
                description(description).
                build();

        backlogService.createBacklog(backlog);

        return MAIN_PAGE;
    }

    @PostMapping("/create-goal")
    public String createGoal(@RequestParam String name, @RequestParam Integer backlogId) {
        Backlog backlog = backlogService.showBacklogById(backlogId);
        Goal goal = Goal.builder().
                name(name).
                backlog(backlog).
                build();

        goalService.createGoal(goal);

        return MAIN_PAGE;
    }

    @PostMapping("/create-sprint")
    public String createSprint(@RequestParam String name, @RequestParam String description,
                               @RequestParam @DateTimeFormat(iso = DATE) LocalDate start, @RequestParam @DateTimeFormat(iso = DATE) LocalDate end) {
        Sprint sprint = Sprint.builder()
                .name(name)
                .start(start)
                .end(end)
                .description(description)
                .build();

        sprintService.createSprint(sprint);

        return MAIN_PAGE;
    }

    @PostMapping("/create-story")
    public String createStory(@RequestParam String name, @RequestParam String description,
                              @RequestParam LocalTime spentTime, @RequestParam Integer goalId) {
        Goal goal = goalService.showGoalById(goalId);
        Story story = Story.builder()
                .name(name)
                .spentTime(spentTime)
                .description(description)
                .status(Status.TO_DO)
                .goal(goal)
                .build();

        storyService.createStory(story);

        return MAIN_PAGE;
    }

    @GetMapping("/all-backlogs")
    public ModelAndView allBacklogs() {
        ModelAndView modelAndView = new ModelAndView();
        int recordsPerPage = (int) backlogService.showNumberOfRows();

        List<Backlog> backlogs = backlogService.showAllBacklogs(0, recordsPerPage);

        modelAndView.addObject("backlogs", backlogs);
        modelAndView.setViewName("createGoal");

        return modelAndView;
    }

    @GetMapping("/all-goals")
    public ModelAndView allGoals() {
        ModelAndView modelAndView = new ModelAndView();
        int recordsPerPage = (int) goalService.showNumberOfRows();

        List<Goal> goals = goalService.showAllGoals(0, recordsPerPage);

        modelAndView.addObject("goals", goals);
        modelAndView.setViewName("createStory");

        return modelAndView;
    }
}
