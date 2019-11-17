package ua.epam.timetracker.Timetracker.Spring.service;


import ua.epam.timetracker.Timetracker.Spring.domain.Sprint;
import ua.epam.timetracker.Timetracker.Spring.domain.Status;
import ua.epam.timetracker.Timetracker.Spring.domain.Story;
import ua.epam.timetracker.Timetracker.Spring.domain.User;

import java.util.List;

public interface StoryService {
    Story createStory(Story story);

    List<Story> showStoryByStatus(Status status, Integer currentPage, Integer recordsPerPage);

    List<Story> showStoryByGoal(Integer goalId, Integer currentPage, Integer recordsPerPage);

    List<Story> showStoryByUser(Integer userId, Integer currentPage, Integer recordsPerPage);

    List<Story> showStoryBySprint(Integer sprintId, Integer currentPage, Integer recordsPerPage);

    List<Story> showAllStories(Integer currentPage, Integer recordsPerPage);

    List<Story> showStoriesWithoutUser(Integer currentPage, Integer recordsPerPage);

    long showNumberOfRows();

    long showNumberOfRowsWithoutUser();

    long showNumberOfRowsByUserId(Integer id);

    void addStoryToUser(Story story, User user);

    void addStoryToSprint(Story story, Sprint sprint);
}
