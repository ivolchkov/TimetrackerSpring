package ua.epam.timetracker.Timetracker.Spring.service.mapper;

import org.springframework.stereotype.Component;
import ua.epam.timetracker.Timetracker.Spring.domain.Backlog;
import ua.epam.timetracker.Timetracker.Spring.entity.BacklogEntity;

@Component
public class BacklogMapper {
    public BacklogEntity mapBacklogToBacklogEntity(Backlog domain) {
//        return BacklogEntity.builder()
//                .projectName(domain.getProjectName())
//                .description(domain.getDescription())
//                .build();
        return new BacklogEntity(domain.getProjectName(),domain.getDescription());
    }

    public Backlog mapBacklogEntityToBacklog(BacklogEntity entity) {
//        return new Backlog(entity.getId(), entity.getProjectName(), entity.getDescription());
        return Backlog.builder()
                .id(entity.getId())
                .projectName(entity.getProjectName())
                .description(entity.getDescription())
                .build();
    }
}
