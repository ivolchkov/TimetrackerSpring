package ua.com.timetracker.service.mapper;

import org.junit.Test;
import ua.com.timetracker.domain.*;
import ua.com.timetracker.entity.GoalEntity;
import ua.com.timetracker.entity.SprintEntity;
import ua.com.timetracker.entity.StoryEntity;
import ua.com.timetracker.entity.UserEntity;

import java.time.LocalTime;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class StoryMapperTest {
    private static final User USER = User.builder().id(1).build();

    private static final Sprint SPRINT = Sprint.builder().id(1).build();

    private static final StoryEntity STORY_ENTITY = getStoryEntity();

    private static final StoryEntity ENTITY_WITH_USER = new StoryEntity(1, new UserEntity(1));

    private static final StoryEntity ENTITY_WITH_SPRINT = new StoryEntity(1, new SprintEntity(1));

    private static final Story DOMAIN = getStory();

    private final StoryMapper storyMapper = new StoryMapper();

    @Test
    public void mapStoryToStoryEntityShouldMapToEntity() {
        StoryEntity actual = storyMapper.mapStoryToStoryEntity(DOMAIN);

        assertThat(actual.getName(), is(STORY_ENTITY.getName()));
        assertThat(actual.getSpentTime(), is(STORY_ENTITY.getSpentTime()));
        assertThat(actual.getDescription(), is(STORY_ENTITY.getDescription()));
        assertThat(actual.getStatus(), is(STORY_ENTITY.getStatus()));
        assertThat(actual.getGoal(), is(STORY_ENTITY.getGoal()));
    }

    @Test
    public void mapStoryEntityToStoryShouldMapToDomain() {
        STORY_ENTITY.setId(1);
        Story actual = storyMapper.mapStoryEntityToStory(STORY_ENTITY);

        assertThat(actual.getId(), is(DOMAIN.getId()));
        assertThat(actual.getName(), is(DOMAIN.getName()));
        assertThat(actual.getSpentTime(), is(DOMAIN.getSpentTime()));
        assertThat(actual.getDescription(), is(DOMAIN.getDescription()));
        assertThat(actual.getStatus(), is(DOMAIN.getStatus()));
        assertThat(actual.getGoal(), is(DOMAIN.getGoal()));
    }

    @Test
    public void mapStoryToStoryEntityShouldMapToEntityWithUser() {
        StoryEntity actual = storyMapper.mapStoryToStoryEntity(DOMAIN, USER);

        assertThat(actual.getId(), is(ENTITY_WITH_USER.getId()));
        assertThat(actual.getUser(), is(ENTITY_WITH_USER.getUser()));
    }

    @Test
    public void mapStoryToStoryEntityShouldMapToEntityWithSprint() {
        StoryEntity actual = storyMapper.mapStoryToStoryEntity(DOMAIN, SPRINT);

        assertThat(actual.getId(), is(ENTITY_WITH_SPRINT.getId()));
        assertThat(actual.getSprint(), is(ENTITY_WITH_SPRINT.getSprint()));
    }

    @Test
    public void mapStoryToStoryEntityShouldReturnNull() {
        StoryEntity actual = storyMapper.mapStoryToStoryEntity(null);

        assertThat(actual, is(nullValue()));
    }

    @Test
    public void mapStoryEntityToStoryShouldReturnNull() {
        Story actual = storyMapper.mapStoryEntityToStory(null);

        assertThat(actual, is(nullValue()));
    }

    @Test
    public void mapStoryToStoryEntityShouldReturnNullWithUser() {
        StoryEntity actual = storyMapper.mapStoryToStoryEntity(null, USER);

        assertThat(actual, is(nullValue()));
    }

    @Test
    public void mapStoryEntityToStoryShouldReturnNullWithSprint() {
        StoryEntity actual = storyMapper.mapStoryToStoryEntity(null, SPRINT);

        assertThat(actual, is(nullValue()));
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