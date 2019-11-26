package project.service.mapper;

import org.junit.Test;
import project.domain.Sprint;
import project.entity.SprintEntity;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class SprintMapperTest {
    private static final SprintEntity ENTITY = new SprintEntity("Test", LocalDate.MIN,
            LocalDate.MAX, "Test description");

    private static final Sprint DOMAIN = getSprint();

    private static final SprintMapper MAPPER = new SprintMapper();

    @Test
    public void mapSprintToSprintEntityShouldMapToEntity() {
        SprintEntity actual = MAPPER.mapSprintToSprintEntity(DOMAIN);

        assertThat(actual.getName(), equalTo(ENTITY.getName()));
        assertThat(actual.getStart(), equalTo(ENTITY.getStart()));
        assertThat(actual.getEnd(), equalTo(ENTITY.getEnd()));
        assertThat(actual.getDescription(), equalTo(ENTITY.getDescription()));

    }

    @Test
    public void mapSprintEntityToSprintShouldMapToDomain() {
        ENTITY.setId(1);
        Sprint actual = MAPPER.mapSprintEntityToSprint(ENTITY);

        assertThat(actual.getName(), equalTo(DOMAIN.getName()));
        assertThat(actual.getStart(), equalTo(DOMAIN.getStart()));
        assertThat(actual.getEnd(), equalTo(DOMAIN.getEnd()));
        assertThat(actual.getDescription(), equalTo(DOMAIN.getDescription()));
    }

    @Test
    public void mapSprintToSprintEntityShouldReturnNull() {
        SprintEntity actual = MAPPER.mapSprintToSprintEntity(null);

        assertThat(actual, equalTo(null));
    }

    @Test
    public void mapSprintEntityToSprintShouldReturnNull() {
        Sprint actual = MAPPER.mapSprintEntityToSprint(null);

        assertThat(actual, equalTo(null));
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