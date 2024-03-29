package ua.com.timetracker.service.mapper;


import org.springframework.stereotype.Component;
import ua.com.timetracker.domain.Sprint;
import ua.com.timetracker.entity.SprintEntity;

import java.util.Objects;

@Component
public class SprintMapper {
    public SprintEntity mapSprintToSprintEntity(Sprint domain) {
        return Objects.isNull(domain) ? null :
                new SprintEntity(domain.getName(), domain.getStart(), domain.getEnd(), domain.getDescription());
    }

    public Sprint mapSprintEntityToSprint(SprintEntity entity) {
        return Objects.isNull(entity) ? null :
                Sprint.builder()
                        .id(entity.getId())
                        .name(entity.getName())
                        .start(entity.getStart())
                        .end(entity.getEnd())
                        .description(entity.getDescription())
                        .build();
    }
}
