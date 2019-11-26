package project.controller;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import project.domain.*;
import project.service.*;

import java.util.List;


@Log4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Controller("/admin")
public class AdminController implements PaginationUtility {
    private final BacklogService backlogService;
    private final GoalService goalService;
    private final StoryService storyService;
    private final SprintService sprintService;
    private final UserService userService;

    @GetMapping("/admin-service")
    public String main() {
        return "admin-service";
    }

    @GetMapping("/backlogs")
    public ModelAndView projects(@RequestParam Integer currentPage, @RequestParam Integer recordsPerPage) {
        ModelAndView modelAndView = new ModelAndView();

        paginatingValidation(currentPage, recordsPerPage);
        List<Backlog> backlogs = backlogService.showAllBacklogs(currentPage-1, recordsPerPage);

        modelAndView.addObject("backlogs", backlogs);

        paginating(currentPage, recordsPerPage, modelAndView, "showProjects", backlogService.showNumberOfRows());

        return modelAndView;
    }

    @GetMapping("/goals")
    public ModelAndView goals(@RequestParam Integer currentPage, @RequestParam Integer recordsPerPage) {
        ModelAndView modelAndView = new ModelAndView();

        paginatingValidation(currentPage, recordsPerPage);
        List<Goal> goals = goalService.showAllGoals(currentPage-1, recordsPerPage);

        modelAndView.addObject("goals", goals);

        paginating(currentPage, recordsPerPage, modelAndView, "showGoals", goalService.showNumberOfRows());

        return modelAndView;
    }

    @GetMapping("/stories")
    public ModelAndView stories(@RequestParam Integer currentPage, @RequestParam Integer recordsPerPage) {
        ModelAndView modelAndView = new ModelAndView();

        paginatingValidation(currentPage, recordsPerPage);
        List<Story> stories = storyService.showAllStories(currentPage-1, recordsPerPage);

        modelAndView.addObject("stories", stories);

        paginating(currentPage, recordsPerPage, modelAndView, "showStories", storyService.showNumberOfRows());

        return modelAndView;
    }

    @GetMapping("/users")
    public ModelAndView users(@RequestParam Integer currentPage, @RequestParam Integer recordsPerPage) {
        ModelAndView modelAndView = new ModelAndView();

        paginatingValidation(currentPage, recordsPerPage);
        List<User> users = userService.findAll(currentPage-1, recordsPerPage);

        modelAndView.addObject("users", users);

        paginating(currentPage, recordsPerPage, modelAndView, "showUsers", userService.showNumberOfRows());

        return modelAndView;
    }

    @GetMapping("/sprints")
    public ModelAndView sprint(@RequestParam Integer currentPage, @RequestParam Integer recordsPerPage) {
        ModelAndView modelAndView = new ModelAndView();

        paginatingValidation(currentPage, recordsPerPage);
        List<Sprint> sprints = sprintService.showAllSprints(currentPage-1, recordsPerPage);

        modelAndView.addObject("sprints", sprints);

        paginating(currentPage, recordsPerPage, modelAndView, "showSprints", sprintService.showNumberOfRows());

        return modelAndView;
    }
}
