package ua.com.timetracker.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.com.timetracker.domain.Story;
import ua.com.timetracker.domain.User;
import ua.com.timetracker.service.StoryService;

import java.util.List;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@Controller("/developer")
public class DeveloperController implements PaginationUtility {
    private final StoryService storyService;

    @GetMapping(value = {"/developer-service"})
    public String main() {
        return "developer-service";
    }

    @GetMapping("/free-stories")
    public ModelAndView freeStories(@RequestParam Integer currentPage, @RequestParam Integer recordsPerPage) {
        ModelAndView modelAndView = new ModelAndView();

        paginatingValidation(currentPage, recordsPerPage);
        List<Story> stories = storyService.showStoriesWithoutUser(currentPage-1, recordsPerPage);

        modelAndView.addObject("stories", stories);

        paginating(currentPage, recordsPerPage, modelAndView, "freeStories",
                storyService.showNumberOfRowsWithoutUser());

        return modelAndView;
    }

    @GetMapping("/developer-stories")
    public ModelAndView developerStories(@RequestParam Integer id, @RequestParam Integer currentPage,
                                         @RequestParam Integer recordsPerPage) {
        ModelAndView modelAndView = new ModelAndView();

        paginatingValidation(currentPage, recordsPerPage);
        List<Story> stories = storyService.showStoryByUser(id, currentPage-1, recordsPerPage);

        modelAndView.addObject("stories", stories);

        paginating(currentPage, recordsPerPage, modelAndView,
                "showDeveloperStories", storyService.showNumberOfRowsByUserId(id));

        return modelAndView;
    }

    @PostMapping("/add-story")
    public ModelAndView addStory(@RequestParam Integer userId, @RequestParam Integer storyId,
                                 @RequestParam Integer currentPage, @RequestParam Integer recordsPerPage) {
        ModelAndView modelAndView = new ModelAndView();
        User user = User.builder().id(userId).build();
        Story story = Story.builder().id(storyId).build();

        storyService.addStoryToUser(story, user);

        paginatingValidation(currentPage, recordsPerPage);
        List<Story> stories = storyService.showStoriesWithoutUser(currentPage-1, recordsPerPage);

        modelAndView.addObject("stories", stories);

        paginating(currentPage, recordsPerPage, modelAndView,
                "redirect:/free-stories", storyService.showNumberOfRowsWithoutUser());

        return modelAndView;
    }
}
