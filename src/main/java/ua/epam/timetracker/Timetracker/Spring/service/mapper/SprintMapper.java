package ua.epam.timetracker.Timetracker.Spring.service.mapper;


import org.springframework.stereotype.Component;
import ua.epam.timetracker.Timetracker.Spring.domain.sprint.Sprint;
import ua.epam.timetracker.Timetracker.Spring.entity.sprint.SprintEntity;

@Component
public class SprintMapper {
    public SprintEntity mapSprintToSprintEntity(Sprint domain) {
//        return SprintEntity.builder()
//                .name(domain.getName())
//                .start(domain.getStart())
//                .end(domain.getEnd())
//                .description(domain.getDescription())
//                .build();
        return new SprintEntity(domain.getName(), domain.getStart(), domain.getEnd(), domain.getDescription());
    }

    public Sprint mapSprintEntityToSprint(SprintEntity entity) {
        return Sprint.builder()
                .id(entity.getId())
                .name(entity.getName())
                .start(entity.getStart())
                .end(entity.getEnd())
                .description(entity.getDescription())
                .build();
    }
}
