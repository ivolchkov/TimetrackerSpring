package project.service.mapper;

import org.junit.Test;
import project.domain.Backlog;
import project.domain.Goal;
import project.entity.BacklogEntity;
import project.entity.GoalEntity;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class GoalMapperTest {
    private static final GoalEntity ENTITY = new GoalEntity("Test", new BacklogEntity(1));

    private static final Goal DOMAIN = new Goal(1, "Test", Backlog.builder().id(1).build());

    private static final GoalMapper MAPPER = new GoalMapper();

    @Test
    public void mapGoalToGoalEntityShouldMapToEntity() {
        GoalEntity actual = MAPPER.mapGoalToGoalEntity(DOMAIN);

        assertThat(actual.getName(), equalTo(ENTITY.getName()));
        assertThat(actual.getBacklog(), equalTo(ENTITY.getBacklog()));
    }

    @Test
    public void mapGoalEntityToGoalShouldMapToDomain() {
        ENTITY.setId(1);
        Goal actual = MAPPER.mapGoalEntityToGoal(ENTITY);

        assertThat(actual.getId(), equalTo(DOMAIN.getId()));
        assertThat(actual.getName(), equalTo(DOMAIN.getName()));
        assertThat(actual.getBacklog(), equalTo(DOMAIN.getBacklog()));
    }

    @Test
    public void mapGoalToGoalEntityShouldReturnNull() {
        GoalEntity actual = MAPPER.mapGoalToGoalEntity(null);

        assertThat(actual, equalTo(null));
    }

    @Test
    public void mapGoalEntityToGoalShouldReturnNull() {
        Goal actual = MAPPER.mapGoalEntityToGoal(null);

        assertThat(actual, equalTo(null));
    }
}