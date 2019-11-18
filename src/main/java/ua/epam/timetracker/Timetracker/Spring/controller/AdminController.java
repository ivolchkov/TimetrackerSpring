package ua.epam.timetracker.Timetracker.Spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import ua.epam.timetracker.Timetracker.Spring.service.*;

@Controller("/admin")
public class AdminController {
    private final BacklogService backlogService;
    private final GoalService goalService;
    private final StoryService storyService;
    private final SprintService sprintService;
    private final UserService userService;

    @Autowired
    public AdminController(BacklogService backlogService, GoalService goalService, StoryService storyService,
                           SprintService sprintService, UserService userService) {
        this.backlogService = backlogService;
        this.goalService = goalService;
        this.storyService = storyService;
        this.sprintService = sprintService;
        this.userService = userService;
    }

    @GetMapping(value = {"/service"})
    public String main() {
        return "admin-service";
    }

    @GetMapping("/showGoals")
    public String goals() {
        return "showGoals";
    }

//    @GetMapping("/showProjects")
//    public String projects() {
//        return "showProjects";
//    }

    @GetMapping("/showStories")
    public String stories() {
        return "showStories";
    }

    @GetMapping("/showUsers")
    public String users() {
        return "showUsers";
    }

    @GetMapping("/showProjects")
    public ModelAndView projects() {
        ModelAndView modelAndView = new ModelAndView();

        return modelAndView;
    }
}
