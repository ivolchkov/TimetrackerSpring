package ua.epam.timetracker.Timetracker.Spring.service.mapper;

import org.springframework.stereotype.Component;
import ua.epam.timetracker.Timetracker.Spring.domain.backlog.Backlog;
import ua.epam.timetracker.Timetracker.Spring.domain.goal.Goal;
import ua.epam.timetracker.Timetracker.Spring.entity.backlog.BacklogEntity;
import ua.epam.timetracker.Timetracker.Spring.entity.goal.GoalEntity;

@Component
public class GoalMapper {
    public GoalEntity mapGoalToGoalEntity(Goal domain) {
        return GoalEntity.builder()
                .name(domain.getName())
                .backlog(BacklogEntity.builder()
                        .id(domain.getBacklog().getId())
                        .build())
                .build();
    }

    public Goal mapGoalEntityToGoal(GoalEntity entity) {
//        return new Goal(entity.getId(), entity.getName(), new Backlog(entity.getBacklog().getId()));
        return Goal.builder()
                .name(entity.getName())
                .backlog(Backlog.builder()
                        .id(entity.getBacklog().getId())
                        .build())
                .build();
    }
}
