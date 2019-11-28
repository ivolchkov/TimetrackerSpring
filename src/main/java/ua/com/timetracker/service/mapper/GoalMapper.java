package ua.com.timetracker.service.mapper;

import org.springframework.stereotype.Component;
import ua.com.timetracker.domain.Backlog;
import ua.com.timetracker.domain.Goal;
import ua.com.timetracker.entity.BacklogEntity;
import ua.com.timetracker.entity.GoalEntity;

import java.util.Objects;
import java.util.Optional;

@Component
public class GoalMapper {
    public GoalEntity mapGoalToGoalEntity(Goal domain) {
        return Objects.isNull(domain) ? null : new GoalEntity(domain.getName(),
                new BacklogEntity(getBacklogId(domain)));
    }

    public Goal mapGoalEntityToGoal(GoalEntity entity) {
        return Objects.isNull(entity) ? null :
                Goal.builder()
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
