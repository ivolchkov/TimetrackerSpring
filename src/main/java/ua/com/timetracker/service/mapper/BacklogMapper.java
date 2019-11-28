package ua.com.timetracker.service.mapper;

import org.springframework.stereotype.Component;
import ua.com.timetracker.domain.Backlog;
import ua.com.timetracker.entity.BacklogEntity;

import java.util.Objects;

@Component
public class BacklogMapper {
    public BacklogEntity mapBacklogToBacklogEntity(Backlog domain) {
        return Objects.isNull(domain) ? null : new BacklogEntity(domain.getProjectName(),
                domain.getDescription());
    }

    public Backlog mapBacklogEntityToBacklog(BacklogEntity entity) {
        return Objects.isNull(entity) ? null :
                Backlog.builder()
                        .id(entity.getId())
                        .projectName(entity.getProjectName())
                        .description(entity.getDescription())
                        .build();
    }
}
