package project.service.mapper;

import org.springframework.stereotype.Component;
import project.domain.Goal;
import project.domain.Sprint;
import project.domain.Story;
import project.domain.User;
import project.entity.GoalEntity;
import project.entity.SprintEntity;
import project.entity.StoryEntity;
import project.entity.UserEntity;

import java.util.Objects;
import java.util.Optional;

@Component
public class StoryMapper {
    public StoryEntity mapStoryToStoryEntity(Story domain) {
        return Objects.isNull(domain) ? null
                : new StoryEntity(domain.getName(), domain.getSpentTime(), domain.getStatus(),
                domain.getDescription(), new GoalEntity(getGoalId(domain)));
    }

    public StoryEntity mapStoryToStoryEntity(Story domain, User user) {
        return Objects.isNull(domain) || Objects.isNull(user) ? null
                : new StoryEntity(domain.getId(), new UserEntity(user.getId()));
    }

    public StoryEntity mapStoryToStoryEntity(Story domain, Sprint sprint) {
        return Objects.isNull(domain) || Objects.isNull(sprint) ? null
                : new StoryEntity(domain.getId(), new SprintEntity(sprint.getId()));
    }

    public Story mapStoryEntityToStory(StoryEntity entity) {
        return Objects.isNull(entity) ? null
                : Story.builder()
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
