package project.service.mapper;

import org.springframework.stereotype.Component;
import project.domain.Backlog;
import project.entity.BacklogEntity;

import java.util.Objects;

@Component
public class BacklogMapper {
    public BacklogEntity mapBacklogToBacklogEntity(Backlog domain) {
        return Objects.isNull(domain) ? null : new BacklogEntity(domain.getProjectName(),
                domain.getDescription());
    }

    public Backlog mapBacklogEntityToBacklog(BacklogEntity entity) {
        return Objects.isNull(entity) ? null
                : Backlog.builder()
                .id(entity.getId())
                .projectName(entity.getProjectName())
                .description(entity.getDescription())
                .build();
    }
}
