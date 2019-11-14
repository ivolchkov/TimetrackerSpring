package ua.epam.timetracker.Timetracker.Spring.service.story;


import ua.epam.timetracker.Timetracker.Spring.domain.sprint.Sprint;
import ua.epam.timetracker.Timetracker.Spring.domain.story.Status;
import ua.epam.timetracker.Timetracker.Spring.domain.story.Story;
import ua.epam.timetracker.Timetracker.Spring.domain.user.User;

import java.util.List;

public interface StoryService {
    boolean createStory(Story story);

    List<Story> showStoryByStatus(Status status, Integer currentPage, Integer recordsPerPage);

    List<Story> showStoryByGoal(Integer goalId, Integer currentPage, Integer recordsPerPage);

    List<Story> showStoryByUser(Integer userId, Integer currentPage, Integer recordsPerPage);

    List<Story> showStoryBySprint(Integer sprintId, Integer currentPage, Integer recordsPerPage);

    List<Story> showAllStories(Integer currentPage, Integer recordsPerPage);

    List<Story> showStoriesWithoutUser(Integer currentPage, Integer recordsPerPage);

    Integer showNumberOfRows();

    Integer showNumberOfRowsWithoutUser();

    Integer showNumberOfRowsByUserId(Integer id);

    void addStoryToUser(Story story, User user);

    void addStoryToSprint(Story story, Sprint sprint);
}
