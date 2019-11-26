package project.service.mapper;

import org.junit.Test;
import project.domain.Backlog;
import project.entity.BacklogEntity;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class BacklogMapperTest {
    private static final BacklogEntity ENTITY = new BacklogEntity("Test", "Test description");

    private static final Backlog DOMAIN = new Backlog(1, "Test", "Test description");

    private static final BacklogMapper MAPPER = new BacklogMapper();

    @Test
    public void mapBacklogToBacklogEntityShouldMapToEntity() {
        BacklogEntity actual = MAPPER.mapBacklogToBacklogEntity(DOMAIN);

        assertThat(actual.getProjectName(), equalTo(ENTITY.getProjectName()));
        assertThat(actual.getDescription(), equalTo(ENTITY.getDescription()));
    }

    @Test
    public void mapBacklogEntityToBacklogShouldMapToDomain() {
        ENTITY.setId(1);
        Backlog actual = MAPPER.mapBacklogEntityToBacklog(ENTITY);

        assertThat(actual.getId(), equalTo(DOMAIN.getId()));
        assertThat(actual.getProjectName(), equalTo(DOMAIN.getProjectName()));
        assertThat(actual.getDescription(), equalTo(DOMAIN.getDescription()));
    }

    @Test
    public void mapBacklogToBacklogEntityShouldReturnNull() {
        BacklogEntity actual = MAPPER.mapBacklogToBacklogEntity(null);

        assertThat(actual, equalTo(null));
    }

    @Test
    public void mapBacklogEntityToBacklogShouldReturnNull() {
        Backlog actual = MAPPER.mapBacklogEntityToBacklog(null);

        assertThat(actual, equalTo(null));
    }
}