package ua.com.timetracker.service.impl;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ua.com.timetracker.domain.Status;
import ua.com.timetracker.domain.Story;
import ua.com.timetracker.entity.StoryEntity;
import ua.com.timetracker.exception.InvalidEntityCreation;
import ua.com.timetracker.exception.InvalidEntityUpdating;
import ua.com.timetracker.exception.InvalidPaginatingException;
import ua.com.timetracker.repository.StoryRepository;
import ua.com.timetracker.service.StoryService;
import ua.com.timetracker.service.mapper.StoryMapper;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {StoryServiceImpl.class})
public class StoryServiceImplTest {
    private static final Story STORY = Story.builder().id(1).status(Status.TO_DO).build();
    private static final StoryEntity STORY_ENTITY = new StoryEntity();
    private static final Page<StoryEntity> STORY_ENTITIES = new PageImpl<>(Arrays.asList(
            STORY_ENTITY, STORY_ENTITY));
    private static final List<Story> STORIES = Arrays.asList(STORY, STORY);

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @MockBean
    private StoryRepository repository;

    @MockBean
    private StoryMapper mapper;

    @Autowired
    private StoryService service;

    @After
    public void resetMock() {
        reset(repository, mapper);
    }

    @Test
    public void createStoryShouldCreateStory() {
        when(mapper.mapStoryToStoryEntity(any(Story.class))).thenReturn(STORY_ENTITY);
        when(repository.save(any(StoryEntity.class))).thenReturn(STORY_ENTITY);
        when(mapper.mapStoryEntityToStory(any(StoryEntity.class))).thenReturn(STORY);

        Story actual = service.createStory(STORY);

        verify(repository).save(any(StoryEntity.class));
        verify(mapper).mapStoryEntityToStory(any(StoryEntity.class));
        verify(mapper).mapStoryToStoryEntity(any(Story.class));

        assertThat(actual, is(STORY));
    }

    @Test
    public void createStoryShouldThrowInvalidEntityCreationWithNullStory() {
        exception.expect(InvalidEntityCreation.class);
        exception.expectMessage("Story is not valid");

        service.createStory(null);
    }

    @Test
    public void showStoryByUserShouldShowAllStoriesByUser() {
        when(repository.findByUserId(any(Integer.class), any(PageRequest.class))).thenReturn(STORY_ENTITIES);
        when(mapper.mapStoryEntityToStory(any(StoryEntity.class))).thenReturn(STORY);

        List<Story> actual = service.showStoryByUser(1, 1, 1);

        verify(repository).findByUserId(any(Integer.class), any(PageRequest.class));
        verify(mapper, times(2)).mapStoryEntityToStory(any(StoryEntity.class));

        assertThat(actual, is(STORIES));
    }

    @Test
    public void showStoryByUserShouldReturnEmptyListSearchingByUser() {
        when(repository.findByUserId(any(Integer.class), any(PageRequest.class))).thenReturn(Page.empty());

        List<Story> actual = service.showStoryByUser(1,1,1);

        verify(repository).findByUserId(any(Integer.class), any(PageRequest.class));

        assertThat(actual, is(Collections.emptyList()));
    }

    @Test
    public void showStoryByUserShouldThrowIllegalArgumentExceptionWithNullUserId() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("User id is not valid");

        service.showStoryByUser(null,1,1);
    }

    @Test
    public void addStoryToUserShouldThrowIllegalArgumentExceptionAddingStoryToUser() {
        exception.expect(InvalidEntityUpdating.class);
        exception.expectMessage("Invalid story updating");

        service.addStoryToUser(STORY, null);
    }

    @Test
    public void showAllStoriesShouldShowAllStories() {
        when(repository.findAll(any(PageRequest.class))).thenReturn(STORY_ENTITIES);
        when(mapper.mapStoryEntityToStory(any(StoryEntity.class))).thenReturn(STORY);

        List<Story> actual = service.showAllStories(1, 10);

        verify(repository).findAll(any(PageRequest.class));
        verify(mapper, times(2)).mapStoryEntityToStory(any(StoryEntity.class));

        assertThat(actual, is(STORIES));
    }

    @Test
    public void showAllStoriesShouldReturnEmptyList() {
        when(repository.findAll(any(PageRequest.class))).thenReturn(Page.empty());

        List<Story> actual = service.showAllStories(1, 10);

        verify(repository).findAll(any(PageRequest.class));

        assertThat(actual, is(Collections.emptyList()));
    }

    @Test
    public void showAllStoriesShouldThrowInvalidPaginatingException() {
        exception.expect(InvalidPaginatingException.class);
        exception.expectMessage("Invalid number of current page or records per page");

        service.showAllStories(-1, 1);
    }

    @Test
    public void showStoriesWithoutUserShouldShowAllStories() {
        when(repository.findByUserIdIsNull(any(PageRequest.class))).thenReturn(STORY_ENTITIES);
        when(mapper.mapStoryEntityToStory(any(StoryEntity.class))).thenReturn(STORY);

        List<Story> actual = service.showStoriesWithoutUser(1 , 10);

        verify(repository).findByUserIdIsNull(any(PageRequest.class));
        verify(mapper, times(2)).mapStoryEntityToStory(any(StoryEntity.class));

        assertThat(actual, is(STORIES));
    }

    @Test
    public void showStoriesWithoutUserShouldReturnEmptyList() {
        when(repository.findByUserIdIsNull(any(PageRequest.class))).thenReturn(Page.empty());

        List<Story> actual = service.showStoriesWithoutUser(1 , 10);

        verify(repository).findByUserIdIsNull(any(PageRequest.class));

        assertThat(actual, is(Collections.emptyList()));
    }

    @Test
    public void showStoriesWithoutUserShouldThrowInvalidPaginatingException() {
        exception.expect(InvalidPaginatingException.class);
        exception.expectMessage("Invalid number of current page or records per page");

        service.showStoriesWithoutUser(-1 ,1);
    }

    @Test
    public void showNumbersOfRowsShouldReturnCount() {
        when(repository.count()).thenReturn(10L);

        long actual = service.showNumberOfRows();

        verify(repository).count();

        assertThat(actual, is(10L));
    }
}