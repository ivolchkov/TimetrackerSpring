package ua.com.timetracker.service;


import ua.com.timetracker.domain.Story;
import ua.com.timetracker.domain.User;

import java.util.List;

public interface StoryService {
    Story createStory(Story story);

    List<Story> showStoryByUser(Integer userId, int currentPage, int recordsPerPage);

    List<Story> showAllStories(int currentPage, int recordsPerPage);

    List<Story> showStoriesWithoutUser(int currentPage, int recordsPerPage);

    long showNumberOfRows();

    long showNumberOfRowsWithoutUser();

    long showNumberOfRowsByUserId(Integer id);

    void addStoryToUser(Story story, User user);
}
