package project.service.mapper;

import org.springframework.stereotype.Component;
import project.domain.Backlog;
import project.domain.Goal;
import project.entity.BacklogEntity;
import project.entity.GoalEntity;

import java.util.Objects;
import java.util.Optional;

@Component
public class GoalMapper {
    public GoalEntity mapGoalToGoalEntity(Goal domain) {
        return Objects.isNull(domain) ? null : new GoalEntity(domain.getName(),
                new BacklogEntity(getBacklogId(domain)));
    }

    public Goal mapGoalEntityToGoal(GoalEntity entity) {
        return Objects.isNull(entity) ? null
                : Goal.builder()
                .id(entity.getId())
                .name(entity.getName())
                .backlog(Backlog.builder()
                        .id(getBacklogEntityId(entity))
                        .build())
                .build();
    }

    private static Integer getBacklogId(Goal domain) {
        return Optional.ofNullable(domain.getBacklog())
                .map(Backlog::getId)
                .orElse(null);
    }

    private static Integer getBacklogEntityId(GoalEntity entity) {
        return Optional.ofNullable(entity.getBacklog())
                .map(BacklogEntity::getId)
                .orElse(null);
    }
}
