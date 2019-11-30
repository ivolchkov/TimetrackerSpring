package ua.com.timetracker.service.mapper;

import org.junit.Test;
import ua.com.timetracker.domain.Backlog;
import ua.com.timetracker.domain.Goal;
import ua.com.timetracker.entity.BacklogEntity;
import ua.com.timetracker.entity.GoalEntity;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class GoalMapperTest {
    private static final GoalEntity GOAL_ENTITY = new GoalEntity("Test", new BacklogEntity(1));

    private static final Goal GOAL = new Goal(1, "Test", Backlog.builder().id(1).build());

    private static GoalMapper goalMapper = new GoalMapper();

    @Test
    public void mapGoalToGoalEntityShouldMapToEntity() {
        GoalEntity actual = goalMapper.mapGoalToGoalEntity(GOAL);

        assertThat(actual.getName(), is(GOAL_ENTITY.getName()));
        assertThat(actual.getBacklog(), is(GOAL_ENTITY.getBacklog()));
    }

    @Test
    public void mapGoalEntityToGoalShouldMapToDomain() {
        GOAL_ENTITY.setId(1);
        Goal actual = goalMapper.mapGoalEntityToGoal(GOAL_ENTITY);

        assertThat(actual.getId(), is(GOAL.getId()));
        assertThat(actual.getName(), is(GOAL.getName()));
        assertThat(actual.getBacklog(), is(GOAL.getBacklog()));
    }

    @Test
    public void mapGoalToGoalEntityShouldReturnNull() {
        GoalEntity actual = goalMapper.mapGoalToGoalEntity(null);

        assertThat(actual, is(nullValue()));
    }

    @Test
    public void mapGoalEntityToGoalShouldReturnNull() {
        Goal actual = goalMapper.mapGoalEntityToGoal(null);

        assertThat(actual, is(nullValue()));
    }
}