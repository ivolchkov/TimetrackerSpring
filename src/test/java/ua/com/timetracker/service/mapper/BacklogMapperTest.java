package ua.com.timetracker.service.mapper;

import org.junit.Test;
import ua.com.timetracker.domain.Backlog;
import ua.com.timetracker.entity.BacklogEntity;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class BacklogMapperTest {
    private static final BacklogEntity BACKLOG_ENTITY = new BacklogEntity("Test", "Test description");

    private static final Backlog DOMAIN = new Backlog(1, "Test", "Test description");

    private final BacklogMapper backlogMapper = new BacklogMapper();

    @Test
    public void mapBacklogToBacklogEntityShouldMapToEntity() {
        BacklogEntity actual = backlogMapper.mapBacklogToBacklogEntity(DOMAIN);

        assertThat(actual.getProjectName(), is(BACKLOG_ENTITY.getProjectName()));
        assertThat(actual.getDescription(), is(BACKLOG_ENTITY.getDescription()));
    }

    @Test
    public void mapBacklogEntityToBacklogShouldMapToDomain() {
        BACKLOG_ENTITY.setId(1);
        Backlog actual = backlogMapper.mapBacklogEntityToBacklog(BACKLOG_ENTITY);

        assertThat(actual.getId(), is(DOMAIN.getId()));
        assertThat(actual.getProjectName(), is(DOMAIN.getProjectName()));
        assertThat(actual.getDescription(), is(DOMAIN.getDescription()));
    }

    @Test
    public void mapBacklogToBacklogEntityShouldReturnNull() {
        BacklogEntity actual = backlogMapper.mapBacklogToBacklogEntity(null);

        assertThat(actual, is(null));
    }

    @Test
    public void mapBacklogEntityToBacklogShouldReturnNull() {
        Backlog actual = backlogMapper.mapBacklogEntityToBacklog(null);

        assertThat(actual, is(null));
    }
}