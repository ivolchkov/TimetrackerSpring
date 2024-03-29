package ua.com.timetracker.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ua.com.timetracker.domain.Story;
import ua.com.timetracker.domain.User;
import ua.com.timetracker.entity.StoryEntity;
import ua.com.timetracker.exception.InvalidEntityCreation;
import ua.com.timetracker.exception.InvalidEntityUpdating;
import ua.com.timetracker.exception.InvalidPaginatingException;
import ua.com.timetracker.repository.StoryRepository;
import ua.com.timetracker.service.StoryService;
import ua.com.timetracker.service.mapper.StoryMapper;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Log4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class StoryServiceImpl implements StoryService {
    private final StoryRepository storyRepository;
    private final StoryMapper mapper;

    @Override
    public Story createStory(Story story) {
        if (Objects.isNull(story)) {
            log.warn("Story is not valid");
            throw new InvalidEntityCreation("Story is not valid");
        }

        StoryEntity entity = storyRepository.save(mapper.mapStoryToStoryEntity(story));

        return mapper.mapStoryEntityToStory(entity);
    }

    @Override
    public List<Story> showStoryByUser(Integer userId, int currentPage, int recordsPerPage) {
        if (Objects.isNull(userId)) {
            log.warn("User id is not valid");
            throw new IllegalArgumentException("User id is not valid");
        }
        paginatingValidation(currentPage, recordsPerPage);

        PageRequest pageRequest = PageRequest.of(currentPage, recordsPerPage);
        Page<StoryEntity> result = storyRepository.findByUserId(userId, pageRequest);
        
        return listMapping(result);
    }

    @Override
    public List<Story> showAllStories(int currentPage, int recordsPerPage) {
        paginatingValidation(currentPage, recordsPerPage);

        PageRequest pageRequest = PageRequest.of(currentPage, recordsPerPage);
        Page<StoryEntity> result = storyRepository.findAll(pageRequest);

        return listMapping(result);
    }

    @Override
    public List<Story> showStoriesWithoutUser(int currentPage, int recordsPerPage) {
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

        return storyRepository.countByUserIdIsNull();
    }

    @Override
    public long showNumberOfRowsByUserId(Integer id) {

        return storyRepository.countByUserId(id);
    }

    @Override
    public void addStoryToUser(Story story, User user) {
        if (Objects.isNull(story) || Objects.isNull(user)) {
            log.warn("Invalid story updating");
            throw new InvalidEntityUpdating("Invalid story updating");
        }

        StoryEntity entity = mapper.mapStoryToStoryEntity(story, user);

        storyRepository.updateUserId(entity.getUser(), entity.getId());
    }

    private void paginatingValidation(int currentPage, int recordsPerPage) {
        if (currentPage < 0 || recordsPerPage < 0) {
            log.error("Invalid number of current page or records per page");
            throw new InvalidPaginatingException("Invalid number of current page or records per page");
        }
    }

    private List<Story> listMapping(Page<StoryEntity> result) {
        return result.isEmpty() ? Collections.emptyList() :
                result.stream()
                        .map(mapper::mapStoryEntityToStory)
                        .collect(Collectors.toList());
    }
}
