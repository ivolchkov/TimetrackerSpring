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
import ua.com.timetracker.domain.Goal;
import ua.com.timetracker.entity.GoalEntity;
import ua.com.timetracker.exception.EntityNotFoundException;
import ua.com.timetracker.exception.InvalidEntityCreation;
import ua.com.timetracker.exception.InvalidPaginatingException;
import ua.com.timetracker.repository.GoalRepository;
import ua.com.timetracker.service.GoalService;
import ua.com.timetracker.service.mapper.GoalMapper;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {GoalServiceImpl.class})
public class GoalServiceImplTest {
    private static final Goal GOAL = Goal.builder().id(1).build();
    private static final GoalEntity GOAL_ENTITY = new GoalEntity(1);
    private static final Page<GoalEntity> GOAL_ENTITIES = new PageImpl<>(Arrays.asList(
            GOAL_ENTITY, GOAL_ENTITY));
    private static final List<Goal> GOALS = Arrays.asList(GOAL, GOAL);

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @MockBean
    private GoalRepository repository;

    @MockBean
    private GoalMapper mapper;

    @Autowired
    private GoalService service;

    @After
    public void resetMock() {
        reset(repository, mapper);
    }

    @Test
    public void showGoalByIdShouldReturnGoalById() {
        when(repository.findById(anyInt())).thenReturn(Optional.of(GOAL_ENTITY));
        when(mapper.mapGoalEntityToGoal(any(GoalEntity.class))).thenReturn(GOAL);

        Goal actual = service.showGoalById(1);

        verify(repository).findById(anyInt());
        verify(mapper).mapGoalEntityToGoal(any(GoalEntity.class));

        assertThat(actual, is(GOAL));
    }

    @Test
    public void showGoalByIdShouldThrowEntityNotFoundExceptionWithNullId() {
        exception.expect(EntityNotFoundException.class);
        exception.expectMessage("There is no goal by this id");

        service.showGoalById(null);
    }

    @Test
    public void showGoalByIdShouldThrowEntityNotFoundExceptionWithNoEntity() {
        when(repository.findById(anyInt())).thenReturn(Optional.empty());
        exception.expect(EntityNotFoundException.class);
        exception.expectMessage("There is no goal by this id");

        service.showGoalById(null);

        verify(repository).findById(anyInt());
    }

    @Test
    public void createGoalShouldCreateGoal() {
        when(mapper.mapGoalToGoalEntity(any(Goal.class))).thenReturn(GOAL_ENTITY);
        when(repository.save(any(GoalEntity.class))).thenReturn(GOAL_ENTITY);
        when(mapper.mapGoalEntityToGoal(any(GoalEntity.class))).thenReturn(GOAL);

        Goal actual = service.createGoal(GOAL);

        verify(repository).save(any(GoalEntity.class));
        verify(mapper).mapGoalEntityToGoal(any(GoalEntity.class));
        verify(mapper).mapGoalToGoalEntity(any(Goal.class));

        assertThat(actual, is(GOAL));
    }

    @Test
    public void createGoalShouldThrowInvalidEntityCreationWithNullGoal() {
        exception.expect(InvalidEntityCreation.class);
        exception.expectMessage("Goal is not valid");

        service.createGoal(null);
    }

    @Test
    public void showAllGoalsShouldShowAllGoals() {
        when(repository.findAll(any(PageRequest.class))).thenReturn(GOAL_ENTITIES);
        when(mapper.mapGoalEntityToGoal(any(GoalEntity.class))).thenReturn(GOAL);

        List<Goal> actual = service.showAllGoals(1, 10);

        verify(repository).findAll(any(PageRequest.class));
        verify(mapper, times(2)).mapGoalEntityToGoal(any(GoalEntity.class));

        assertThat(actual, is(GOALS));
    }

    @Test
    public void showAllGoalsShouldReturnEmptyList() {
        when(repository.findAll(any(PageRequest.class))).thenReturn(Page.empty());

        List<Goal> actual = service.showAllGoals(1, 10);

        verify(repository).findAll(any(PageRequest.class));

        assertThat(actual, is(Collections.emptyList()));
    }

    @Test
    public void showAllGoalsShouldThrowInvalidPaginatingException() {
        exception.expect(InvalidPaginatingException.class);
        exception.expectMessage("Invalid number of current page or records per page");

        service.showAllGoals(-1, 1);
    }

    @Test
    public void showNumbersOfRowsShouldReturnCount() {
        when(repository.count()).thenReturn(10L);

        long actual = service.showNumberOfRows();

        verify(repository).count();

        assertThat(actual, is(10L));
    }
}