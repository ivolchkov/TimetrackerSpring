package ua.epam.timetracker.Timetracker.Spring.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.epam.timetracker.Timetracker.Spring.domain.*;
import ua.epam.timetracker.Timetracker.Spring.service.*;

import java.util.List;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@Controller("/admin")
public class AdminController extends AbstractPaginatingController {
    private final BacklogService backlogService;
    private final GoalService goalService;
    private final StoryService storyService;
    private final SprintService sprintService;
    private final UserService userService;

    @GetMapping(value = {"/admin-service"})
    public String main() {
        return "admin-service";
    }

    @GetMapping("/backlogs")
    public ModelAndView projects(@RequestParam Integer currentPage, @RequestParam Integer recordsPerPage) {
        ModelAndView modelAndView = new ModelAndView();

        paginatingValidation(currentPage, recordsPerPage);
        List<Backlog> backlogs = backlogService.showAllBacklogs(currentPage-1, recordsPerPage);

        modelAndView.addObject("backlogs", backlogs);

        paginating(currentPage, recordsPerPage, modelAndView, "backlogs", "showProjects", backlogService.showNumberOfRows());

        return modelAndView;
    }

    @GetMapping("/goals")
    public ModelAndView goals(@RequestParam Integer currentPage, @RequestParam Integer recordsPerPage) {
        ModelAndView modelAndView = new ModelAndView();

        paginatingValidation(currentPage, recordsPerPage);
        List<Goal> goals = goalService.showAllGoals(currentPage-1, recordsPerPage);

        modelAndView.addObject("goals", goals);

        paginating(currentPage, recordsPerPage, modelAndView, "goals", "showGoals", goalService.showNumberOfRows());

        return modelAndView;
    }

    @GetMapping("/stories")
    public ModelAndView stories(@RequestParam Integer currentPage, @RequestParam Integer recordsPerPage) {
        ModelAndView modelAndView = new ModelAndView();

        paginatingValidation(currentPage, recordsPerPage);
        List<Story> stories = storyService.showAllStories(currentPage-1, recordsPerPage);

        modelAndView.addObject("stories", stories);

        paginating(currentPage, recordsPerPage, modelAndView, "stories", "showStories", storyService.showNumberOfRows());

        return modelAndView;
    }

    @GetMapping("/users")
    public ModelAndView users(@RequestParam Integer currentPage, @RequestParam Integer recordsPerPage) {
        ModelAndView modelAndView = new ModelAndView();

        paginatingValidation(currentPage, recordsPerPage);
        List<User> users = userService.findAll(currentPage-1, recordsPerPage);

        modelAndView.addObject("users", users);

        paginating(currentPage, recordsPerPage, modelAndView, "users", "showUsers", userService.showNumberOfRows());

        return modelAndView;
    }

    @GetMapping("/sprints")
    public ModelAndView sprint(@RequestParam Integer currentPage, @RequestParam Integer recordsPerPage) {
        ModelAndView modelAndView = new ModelAndView();

        paginatingValidation(currentPage, recordsPerPage);
        List<Sprint> sprints = sprintService.showAllSprints(currentPage-1, recordsPerPage);

        modelAndView.addObject("sprints", sprints);

        paginating(currentPage, recordsPerPage, modelAndView, "sprints", "showSprints", sprintService.showNumberOfRows());

        return modelAndView;
    }
}
