package project.service.mapper;

import org.junit.Test;
import project.domain.*;
import project.entity.GoalEntity;
import project.entity.SprintEntity;
import project.entity.StoryEntity;
import project.entity.UserEntity;

import java.time.LocalTime;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class StoryMapperTest {
    private static final User USER = User.builder().id(1).build();

    private static final Sprint SPRINT = Sprint.builder().id(1).build();

    private static final StoryEntity ENTITY = getStoryEntity();

    private static final StoryEntity ENTITY_WITH_USER = new StoryEntity(1, new UserEntity(1));

    private static final StoryEntity ENTITY_WITH_SPRINT = new StoryEntity(1, new SprintEntity(1));

    private static final Story DOMAIN = getStory();

    private static final StoryMapper MAPPER = new StoryMapper();

    @Test
    public void mapStoryToStoryEntityShouldMapToEntity() {
        StoryEntity actual = MAPPER.mapStoryToStoryEntity(DOMAIN);

        assertThat(actual.getName(), equalTo(ENTITY.getName()));
        assertThat(actual.getSpentTime(), equalTo(ENTITY.getSpentTime()));
        assertThat(actual.getDescription(), equalTo(ENTITY.getDescription()));
        assertThat(actual.getStatus(), equalTo(ENTITY.getStatus()));
        assertThat(actual.getGoal(), equalTo(ENTITY.getGoal()));
    }

    @Test
    public void mapStoryEntityToStoryShouldMapToDomain() {
        ENTITY.setId(1);
        Story actual = MAPPER.mapStoryEntityToStory(ENTITY);

        assertThat(actual.getId(), equalTo(DOMAIN.getId()));
        assertThat(actual.getName(), equalTo(DOMAIN.getName()));
        assertThat(actual.getSpentTime(), equalTo(DOMAIN.getSpentTime()));
        assertThat(actual.getDescription(), equalTo(DOMAIN.getDescription()));
        assertThat(actual.getStatus(), equalTo(DOMAIN.getStatus()));
        assertThat(actual.getGoal(), equalTo(DOMAIN.getGoal()));
    }

    @Test
    public void mapStoryToStoryEntityShouldMapToEntityWithUser() {
        StoryEntity actual = MAPPER.mapStoryToStoryEntity(DOMAIN, USER);

        assertThat(actual.getId(), equalTo(ENTITY_WITH_USER.getId()));
        assertThat(actual.getUser(), equalTo(ENTITY_WITH_USER.getUser()));
    }

    @Test
    public void mapStoryToStoryEntityShouldMapToEntityWithSprint() {
        StoryEntity actual = MAPPER.mapStoryToStoryEntity(DOMAIN, SPRINT);

        assertThat(actual.getId(), equalTo(ENTITY_WITH_SPRINT.getId()));
        assertThat(actual.getSprint(), equalTo(ENTITY_WITH_SPRINT.getSprint()));
    }

    @Test
    public void mapStoryToStoryEntityShouldReturnNull() {
        StoryEntity actual = MAPPER.mapStoryToStoryEntity(null);

        assertThat(actual, equalTo(null));
    }

    @Test
    public void mapStoryEntityToStoryShouldReturnNull() {
        Story actual = MAPPER.mapStoryEntityToStory(null);

        assertThat(actual, equalTo(null));
    }

    @Test
    public void mapStoryToStoryEntityShouldReturnNullWithUser() {
        StoryEntity actual = MAPPER.mapStoryToStoryEntity(null, USER);

        assertThat(actual, equalTo(null));
    }

    @Test
    public void mapStoryEntityToStoryShouldReturnNullWithSprint() {
        StoryEntity actual = MAPPER.mapStoryToStoryEntity(null, SPRINT);

        assertThat(actual, equalTo(null));
    }

    private static StoryEntity getStoryEntity() {
        return new StoryEntity("Test", LocalTime.MAX, Status.DONE, "Test description", new GoalEntity(1));
    }

    private static Story getStory() {
        return Story.builder()
                .id(1)
                .name("Test")
                .spentTime(LocalTime.MAX)
                .description("Test description")
                .status(Status.DONE)
                .goal(Goal.builder().id(1).build())
                .build();
    }
}