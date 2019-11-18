package ua.epam.timetracker.Timetracker.Spring.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ua.epam.timetracker.Timetracker.Spring.domain.Sprint;
import ua.epam.timetracker.Timetracker.Spring.domain.Status;
import ua.epam.timetracker.Timetracker.Spring.domain.Story;
import ua.epam.timetracker.Timetracker.Spring.domain.User;
import ua.epam.timetracker.Timetracker.Spring.entity.StoryEntity;
import ua.epam.timetracker.Timetracker.Spring.exception.InvalidEntityCreation;
import ua.epam.timetracker.Timetracker.Spring.exception.InvalidEntityUpdating;
import ua.epam.timetracker.Timetracker.Spring.exception.InvalidPaginatingException;
import ua.epam.timetracker.Timetracker.Spring.repository.StoryRepository;
import ua.epam.timetracker.Timetracker.Spring.service.StoryService;
import ua.epam.timetracker.Timetracker.Spring.service.mapper.StoryMapper;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class StoryServiceImpl implements StoryService {
    private static final Logger LOGGER = Logger.getLogger(StoryServiceImpl.class);

    private final StoryRepository storyRepository;
    private final StoryMapper mapper;

    @Autowired
    public StoryServiceImpl(StoryRepository storyRepository, StoryMapper mapper) {
        this.storyRepository = storyRepository;
        this.mapper = mapper;
    }

    @Override
    public Story createStory(Story story) {
        if (Objects.isNull(story) ) {
            LOGGER.warn("Story is not valid");
            throw new InvalidEntityCreation("Story is not valid");
        }

        StoryEntity entity = storyRepository.save(mapper.mapStoryToStoryEntity(story));

        return mapper.mapStoryEntityToStory(entity);
    }

    @Override
    public List<Story> showStoryByStatus(Status status, Integer currentPage, Integer recordsPerPage) {
        validateParam(status);
        paginatingValidation(currentPage, recordsPerPage);

        PageRequest pageRequest = PageRequest.of(currentPage, recordsPerPage);

        return storyRepository.findByStatus(status,pageRequest).stream()
                .map(mapper::mapStoryEntityToStory)
                .collect(Collectors.toList());
    }

    @Override
    public List<Story> showStoryByGoal(Integer goalId, Integer currentPage, Integer recordsPerPage) {
        validateParam(goalId);
        paginatingValidation(currentPage, recordsPerPage);

        PageRequest pageRequest = PageRequest.of(currentPage, recordsPerPage);

        return storyRepository.findByGoalId(goalId, pageRequest).stream()
                .map(mapper::mapStoryEntityToStory)
                .collect(Collectors.toList());
    }

    @Override
    public List<Story> showStoryByUser(Integer userId, Integer currentPage, Integer recordsPerPage) {
        validateParam(userId);
        paginatingValidation(currentPage, recordsPerPage);

        PageRequest pageRequest = PageRequest.of(currentPage, recordsPerPage);

        return storyRepository.findByUserId(userId, pageRequest).stream()
                .map(mapper::mapStoryEntityToStory)
                .collect(Collectors.toList());
    }

    @Override
    public List<Story> showStoryBySprint(Integer sprintId, Integer currentPage, Integer recordsPerPage) {
        validateParam(sprintId);
        paginatingValidation(currentPage, recordsPerPage);

        PageRequest pageRequest = PageRequest.of(currentPage, recordsPerPage);

        return storyRepository.findBySprintId(sprintId, pageRequest).stream()
                .map(mapper::mapStoryEntityToStory)
                .collect(Collectors.toList());
    }

    @Override
    public List<Story> showAllStories(Integer currentPage, Integer recordsPerPage) {
        paginatingValidation(currentPage, recordsPerPage);

        PageRequest pageRequest = PageRequest.of(currentPage, recordsPerPage);
        Page<StoryEntity> result = storyRepository.findAll(pageRequest);

        return listMapping(result);
    }

    @Override
    public List<Story> showStoriesWithoutUser(Integer currentPage, Integer recordsPerPage) {
        paginatingValidation(currentPage, recordsPerPage);

        PageRequest pageRequest = PageRequest.of(currentPage, recordsPerPage);
        Page<StoryEntity> result = storyRepository.findByUserIdIsNull(pageRequest);

        return listMapping(result);
    }

    @Override
    public long showNumberOfRows() {
    return storyRepository.count();
    }

    @Override
    public long showNumberOfRowsWithoutUser() {

    return storyRepository.countByUserIdIsNotNull();
    }

    @Override
    public long showNumberOfRowsByUserId(Integer id) {

    return storyRepository.countByUserId(id);
    }

    @Override
    public void addStoryToUser(Story story, User user) {
        validateUpdateParam(story, user);

        StoryEntity entity = mapper.mapStoryToStoryEntity(story, user);

        storyRepository.updateUserId(entity.getUser().getId(), entity.getId());
    }

    @Override
    public void addStoryToSprint(Story story, Sprint sprint) {
        validateUpdateParam(story, sprint);

        StoryEntity entity = mapper.mapStoryToStoryEntity(story, sprint);

        storyRepository.updateSprintId(entity.getSprint().getId(), entity.getId());
    }

    private <T> void validateParam(T param) {
        if (Objects.isNull(param)) {
            LOGGER.warn("Parameter is not valid");
            throw new IllegalArgumentException("Parameter is not valid");
        }
    }

    private void paginatingValidation(Integer currentPage, Integer recordsPerPage) {
        if (currentPage <= 0 || recordsPerPage <= 0) {
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

    private List<Story> listMapping(Page<StoryEntity> result) {
        return result.isEmpty() ? Collections.emptyList()
                : result.stream()
                .map(mapper::mapStoryEntityToStory)
                .collect(Collectors.toList());
    }
}
