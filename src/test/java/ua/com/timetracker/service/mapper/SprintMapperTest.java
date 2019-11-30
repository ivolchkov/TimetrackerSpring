package ua.com.timetracker.service.mapper;

import org.junit.Test;
import ua.com.timetracker.domain.Sprint;
import ua.com.timetracker.entity.SprintEntity;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class SprintMapperTest {
    private static final SprintEntity SPRINT_ENTITY = new SprintEntity("Test", LocalDate.MIN,
            LocalDate.MAX, "Test description");

    private static final Sprint DOMAIN = getSprint();

    private final SprintMapper sprintMapper = new SprintMapper();

    @Test
    public void mapSprintToSprintEntityShouldMapToEntity() {
        SprintEntity actual = sprintMapper.mapSprintToSprintEntity(DOMAIN);

        assertThat(actual.getName(), is(SPRINT_ENTITY.getName()));
        assertThat(actual.getStart(), is(SPRINT_ENTITY.getStart()));
        assertThat(actual.getEnd(), is(SPRINT_ENTITY.getEnd()));
        assertThat(actual.getDescription(), is(SPRINT_ENTITY.getDescription()));

    }

    @Test
    public void mapSprintEntityToSprintShouldMapToDomain() {
        SPRINT_ENTITY.setId(1);
        Sprint actual = sprintMapper.mapSprintEntityToSprint(SPRINT_ENTITY);

        assertThat(actual.getName(), is(DOMAIN.getName()));
        assertThat(actual.getStart(), is(DOMAIN.getStart()));
        assertThat(actual.getEnd(), is(DOMAIN.getEnd()));
        assertThat(actual.getDescription(), is(DOMAIN.getDescription()));
    }

    @Test
    public void mapSprintToSprintEntityShouldReturnNull() {
        SprintEntity actual = sprintMapper.mapSprintToSprintEntity(null);

        assertThat(actual, is(nullValue()));
    }

    @Test
    public void mapSprintEntityToSprintShouldReturnNull() {
        Sprint actual = sprintMapper.mapSprintEntityToSprint(null);

        assertThat(actual, is(nullValue()));
    }

    private static Sprint getSprint() {
        return Sprint.builder()
                .id(1)
                .name("Test")
                .start(LocalDate.MIN)
                .end(LocalDate.MAX)
                .description("Test description")
                .build();
    }
}