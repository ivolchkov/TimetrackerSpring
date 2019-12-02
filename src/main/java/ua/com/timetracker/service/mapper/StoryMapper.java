package ua.com.timetracker.service.mapper;

import org.springframework.stereotype.Component;
import ua.com.timetracker.domain.Goal;
import ua.com.timetracker.domain.Story;
import ua.com.timetracker.domain.User;
import ua.com.timetracker.entity.GoalEntity;
import ua.com.timetracker.entity.StoryEntity;
import ua.com.timetracker.entity.UserEntity;

import java.util.Objects;
import java.util.Optional;

@Component
public class StoryMapper {
    public StoryEntity mapStoryToStoryEntity(Story domain) {
        return Objects.isNull(domain) ? null :
                new StoryEntity(domain.getName(), domain.getSpentTime(), domain.getStatus(),
                        domain.getDescription(), new GoalEntity(getGoalId(domain)));
    }

    public StoryEntity mapStoryToStoryEntity(Story domain, User user) {
        return Objects.isNull(domain) || Objects.isNull(user) ? null :
                new StoryEntity(domain.getId(), new UserEntity(user.getId()));
    }

    public Story mapStoryEntityToStory(StoryEntity entity) {
        return Objects.isNull(entity) ? null :
                Story.builder()
                        .id(entity.getId())
                        .name(entity.getName())
                        .spentTime(entity.getSpentTime())
                        .description(entity.getDescription())
                        .status(entity.getStatus())
                        .goal(Goal.builder()
                                .id(getGoalEntityId(entity))
                                .build())
                        .build();
    }

    private static Integer getGoalId(Story domain) {
        return Optional.ofNullable(domain.getGoal())
                .map(Goal::getId)
                .orElse(null);
    }

    private static Integer getGoalEntityId(StoryEntity entity) {
        return Optional.ofNullable(entity.getGoal())
                .map(GoalEntity::getId)
                .orElse(null);
    }
}
