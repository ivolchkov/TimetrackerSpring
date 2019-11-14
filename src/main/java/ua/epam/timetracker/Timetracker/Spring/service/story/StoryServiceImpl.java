package ua.epam.timetracker.Timetracker.Spring.service.story;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.epam.timetracker.Timetracker.Spring.domain.sprint.Sprint;
import ua.epam.timetracker.Timetracker.Spring.domain.story.Status;
import ua.epam.timetracker.Timetracker.Spring.domain.story.Story;
import ua.epam.timetracker.Timetracker.Spring.domain.user.User;
import ua.epam.timetracker.Timetracker.Spring.entity.story.StoryEntity;
import ua.epam.timetracker.Timetracker.Spring.exception.InvalidEntityCreation;
import ua.epam.timetracker.Timetracker.Spring.exception.InvalidEntityUpdating;
import ua.epam.timetracker.Timetracker.Spring.exception.InvalidPaginatingException;
import ua.epam.timetracker.Timetracker.Spring.service.mapper.StoryMapper;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class StoryServiceImpl implements StoryService {
    private static final Logger LOGGER = Logger.getLogger(StoryServiceImpl.class);

    //    private final StoryDao storyDao;
    private final StoryMapper mapper;

    //    public StoryServiceImpl(StoryDao storyDao, StoryMapper mapper) {
//        this.storyDao = storyDao;
//        this.mapper = mapper;
//    }
    @Autowired
    public StoryServiceImpl(StoryMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public boolean createStory(Story story) {
//        if (Objects.isNull(story) ) {
//            LOGGER.warn("Story is not valid");
//            throw new InvalidEntityCreation("Story is not valid");
//        }
//
//        return storyDao.save(mapper.mapStoryToStoryEntity(story));
        return true;
    }

    @Override
    public List<Story> showStoryByStatus(Status status, Integer currentPage, Integer recordsPerPage) {
//        validateParam(status);
//        paginatingValidation(currentPage, recordsPerPage);
//
//        Integer offset = currentPage * recordsPerPage - recordsPerPage;
//
//        return storyDao.findByStatus(status, offset, recordsPerPage).stream()
//                .map(mapper::mapStoryEntityToStory)
//                .collect(Collectors.toList());
        return null;
    }

    @Override
    public List<Story> showStoryByGoal(Integer goalId, Integer currentPage, Integer recordsPerPage) {
//        validateParam(goalId);
//        paginatingValidation(currentPage, recordsPerPage);
//
//        Integer offset = currentPage * recordsPerPage - recordsPerPage;
//
//        return storyDao.findByGoal(goalId, offset, recordsPerPage).stream()
//                .map(mapper::mapStoryEntityToStory)
//                .collect(Collectors.toList());
        return null;
    }

    @Override
    public List<Story> showStoryByUser(Integer userId, Integer currentPage, Integer recordsPerPage) {
//        validateParam(userId);
//        paginatingValidation(currentPage, recordsPerPage);
//
//        Integer offset = currentPage * recordsPerPage - recordsPerPage;
//
//        return storyDao.findByUser(userId, offset, recordsPerPage).stream()
//                .map(mapper::mapStoryEntityToStory)
//                .collect(Collectors.toList());
        return null;
    }

    @Override
    public List<Story> showStoryBySprint(Integer sprintId, Integer currentPage, Integer recordsPerPage) {
//        validateParam(sprintId);
//        paginatingValidation(currentPage, recordsPerPage);
//
//        Integer offset = currentPage * recordsPerPage - recordsPerPage;
//
//        return storyDao.findBySprint(sprintId, offset, recordsPerPage).stream()
//                .map(mapper::mapStoryEntityToStory)
//                .collect(Collectors.toList());
        return null;
    }

    @Override
    public List<Story> showAllStories(Integer currentPage, Integer recordsPerPage) {
//        paginatingValidation(currentPage, recordsPerPage);
//
//        Integer offset = currentPage * recordsPerPage - recordsPerPage;
//        List<StoryEntity> result = storyDao.findAll(offset, recordsPerPage);
//
//        return listMapping(result);
        return null;
    }

    @Override
    public List<Story> showStoriesWithoutUser(Integer currentPage, Integer recordsPerPage) {
//        paginatingValidation(currentPage, recordsPerPage);
//
//        Integer offset = currentPage * recordsPerPage - recordsPerPage;
//        List<StoryEntity> result = storyDao.findWithoutUser(offset, recordsPerPage);
//
//        return listMapping(result);
        return null;
    }

    @Override
    public Integer showNumberOfRows() {

//    return storyDao.findAmountOfRows();
        return 10;
    }

    @Override
    public Integer showNumberOfRowsWithoutUser() {

//    return storyDao.findAmountOfRowsWithoutUser();
        return 10;
    }

    @Override
    public Integer showNumberOfRowsByUserId(Integer id) {

//    return storyDao.findAmountOfRowsByUser(id);
        return 10;
    }

    @Override
    public void addStoryToUser(Story story, User user) {
//        validateUpdateParam(story, user);
//
//        storyDao.updateUserId(mapper.mapStoryToStoryEntity(story, user));
    }

    @Override
    public void addStoryToSprint(Story story, Sprint sprint) {
//        validateUpdateParam(story, sprint);
//
//        storyDao.updateSprintId(mapper.mapStoryToStoryEntity(story, sprint));
    }

    private <T> void validateParam(T param) {
        if (Objects.isNull(param)) {
            LOGGER.warn("Parameter is not valid");
            throw new IllegalArgumentException("Parameter is not valid");
        }
    }

    private void paginatingValidation(Integer currentPage, Integer recordsPerPage) {
        if (currentPage == 0 || recordsPerPage == 0) {
            LOGGER.error("Invalid number of current page or records per page");
            throw new InvalidPaginatingException("Invalid number of current page or records per page");
        }
    }

    private <T> void validateUpdateParam(Story story, T param) {
        if (Objects.isNull(story) || Objects.isNull(param)) {
            LOGGER.warn("Invalid story updating");
            throw new InvalidEntityUpdating("Invalid story updating");
        }
    }

    private List<Story> listMapping(List<StoryEntity> result) {
        return result.isEmpty() ? Collections.emptyList()
                : result.stream()
                .map(mapper::mapStoryEntityToStory)
                .collect(Collectors.toList());
    }
}
