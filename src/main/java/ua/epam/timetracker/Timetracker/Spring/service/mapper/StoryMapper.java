package ua.epam.timetracker.Timetracker.Spring.service.mapper;

import org.springframework.stereotype.Component;
import ua.epam.timetracker.Timetracker.Spring.domain.goal.Goal;
import ua.epam.timetracker.Timetracker.Spring.domain.sprint.Sprint;
import ua.epam.timetracker.Timetracker.Spring.domain.story.Story;
import ua.epam.timetracker.Timetracker.Spring.domain.user.User;
import ua.epam.timetracker.Timetracker.Spring.entity.goal.GoalEntity;
import ua.epam.timetracker.Timetracker.Spring.entity.sprint.SprintEntity;
import ua.epam.timetracker.Timetracker.Spring.entity.story.StoryEntity;
import ua.epam.timetracker.Timetracker.Spring.entity.user.UserEntity;

@Component
public class StoryMapper {
    public StoryEntity mapStoryToStoryEntity(Story domain) {
        return StoryEntity.builder()
                .name(domain.getName())
                .spentTime(domain.getSpentTime())
                .description(domain.getDescription())
                .status(domain.getStatus())
                .goal(GoalEntity.builder()
                        .id(domain.getGoal().getId())
                        .build())
                .build();
    }

    public StoryEntity mapStoryToStoryEntity(Story domain, User user) {
        return StoryEntity.builder()
                .id(domain.getId())
                .user(UserEntity.builder()
                        .id(user.getId())
                        .build())
                .build();
    }

    public StoryEntity mapStoryToStoryEntity(Story domain, Sprint sprint) {
        return StoryEntity.builder()
                .id(domain.getId())
                .sprint(SprintEntity.builder()
                        .id(sprint.getId())
                        .build())
                .build();
    }

    public Story mapStoryEntityToStory(StoryEntity entity) {
        return Story.builder()
                .id(entity.getId())
                .name(entity.getName())
                .spentTime(entity.getSpentTime())
                .description(entity.getDescription())
                .status(entity.getStatus())
                .goal(Goal.builder()
                        .id(entity.getGoal().getId())
                        .build())
                .build();
    }
}