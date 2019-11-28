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
import ua.com.timetracker.domain.Sprint;
import ua.com.timetracker.entity.SprintEntity;
import ua.com.timetracker.exception.InvalidEntityCreation;
import ua.com.timetracker.exception.InvalidPaginatingException;
import ua.com.timetracker.repository.SprintRepository;
import ua.com.timetracker.service.SprintService;
import ua.com.timetracker.service.mapper.SprintMapper;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {SprintServiceImpl.class})
public class SprintServiceImplTest {
    private static final Sprint SPRINT = Sprint.builder().id(1).build();
    private static final SprintEntity SPRINT_ENTITY = new SprintEntity(1);
    private static final Page<SprintEntity> SPRINT_ENTITIES = new PageImpl<>(Arrays.asList(
            SPRINT_ENTITY, SPRINT_ENTITY));
    private static final List<Sprint> SPRINTS = Arrays.asList(SPRINT, SPRINT);

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @MockBean
    private SprintRepository repository;

    @MockBean
    private SprintMapper mapper;

    @Autowired
    private SprintService service;

    @After
    public void resetMock() {
        reset(repository, mapper);
    }

    @Test
    public void createSprintShouldCreateSprint() {
        when(mapper.mapSprintToSprintEntity(any(Sprint.class))).thenReturn(SPRINT_ENTITY);
        when(repository.save(any(SprintEntity.class))).thenReturn(SPRINT_ENTITY);
        when(mapper.mapSprintEntityToSprint(any(SprintEntity.class))).thenReturn(SPRINT);

        Sprint actual = service.createSprint(SPRINT);

        verify(repository).save(any(SprintEntity.class));
        verify(mapper).mapSprintEntityToSprint(any(SprintEntity.class));
        verify(mapper).mapSprintToSprintEntity(any(Sprint.class));

        assertThat(actual, is(SPRINT));
    }

    @Test
    public void createSprintShouldThrowInvalidEntityCreationWithNullSprint() {
        exception.expect(InvalidEntityCreation.class);
        exception.expectMessage("Sprint is not valid");

        service.createSprint(null);
    }

    @Test
    public void showAllSprintsShouldShowAllSprints() {
        when(repository.findAll(any(PageRequest.class))).thenReturn(SPRINT_ENTITIES);
        when(mapper.mapSprintEntityToSprint(any(SprintEntity.class))).thenReturn(SPRINT);

        List<Sprint> actual = service.showAllSprints(1, 10);

        verify(repository).findAll(any(PageRequest.class));
        verify(mapper, times(2)).mapSprintEntityToSprint(any(SprintEntity.class));

        assertThat(actual, is(SPRINTS));
    }

    @Test
    public void showAllSprintsShouldReturnEmptyList() {
        when(repository.findAll(any(PageRequest.class))).thenReturn(Page.empty());

        List<Sprint> actual = service.showAllSprints(1, 10);

        verify(repository).findAll(any(PageRequest.class));

        assertThat(actual, is(Collections.emptyList()));
    }

    @Test
    public void showAllSprintsShouldThrowInvalidPaginatingException() {
        exception.expect(InvalidPaginatingException.class);
        exception.expectMessage("Invalid number of current page or records per page");

        service.showAllSprints(-1, 1);
    }

    @Test
    public void showNumbersOfRowsShouldReturnCount() {
        when(repository.count()).thenReturn(10L);

        long actual = service.showNumberOfRows();

        verify(repository).count();

        assertThat(actual, is(10L));
    }
}