package ua.epam.timetracker.Timetracker.Spring.service.mapper;

import org.springframework.stereotype.Component;
import ua.epam.timetracker.Timetracker.Spring.domain.Backlog;
import ua.epam.timetracker.Timetracker.Spring.domain.Goal;
import ua.epam.timetracker.Timetracker.Spring.entity.BacklogEntity;
import ua.epam.timetracker.Timetracker.Spring.entity.GoalEntity;

@Component
public class GoalMapper {
    public GoalEntity mapGoalToGoalEntity(Goal domain) {
//        return GoalEntity.builder()
//                .name(domain.getName())
//                .backlog(BacklogEntity.builder()
//                        .id(domain.getBacklog().getId())
//                        .build())
//                .build();
        return new GoalEntity(domain.getName(),new BacklogEntity(domain.getBacklog().getId()));
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
