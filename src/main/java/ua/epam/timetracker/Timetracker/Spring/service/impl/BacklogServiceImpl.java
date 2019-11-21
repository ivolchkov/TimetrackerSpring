package ua.epam.timetracker.Timetracker.Spring.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ua.epam.timetracker.Timetracker.Spring.domain.Backlog;
import ua.epam.timetracker.Timetracker.Spring.entity.BacklogEntity;
import ua.epam.timetracker.Timetracker.Spring.exception.EntityNotFoundException;
import ua.epam.timetracker.Timetracker.Spring.exception.InvalidEntityCreation;
import ua.epam.timetracker.Timetracker.Spring.exception.InvalidPaginatingException;
import ua.epam.timetracker.Timetracker.Spring.repository.BacklogRepository;
import ua.epam.timetracker.Timetracker.Spring.service.BacklogService;
import ua.epam.timetracker.Timetracker.Spring.service.mapper.BacklogMapper;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class BacklogServiceImpl implements BacklogService {
    private final BacklogRepository backlogRepository;
    private final BacklogMapper mapper;

    @Override
    public Backlog createBacklog(Backlog backlog) {
        if (Objects.isNull(backlog)) {
            log.warn("Backlog is not valid");
            throw new InvalidEntityCreation("Backlog is not valid");
        }

        BacklogEntity entity = backlogRepository.save(mapper.mapBacklogToBacklogEntity(backlog));

        return mapper.mapBacklogEntityToBacklog(entity);
    }

    @Override
    public Backlog showBacklogById(Integer id) {
        if (Objects.nonNull(id)) {
            Optional<BacklogEntity> entity = backlogRepository.findById(id);

            if ( entity.isPresent() ) {
                return mapper.mapBacklogEntityToBacklog(entity.get());
            }

            log.warn("There is no backlog by this id");
            throw new EntityNotFoundException("There is no backlog by this id");
        }
        log.warn("There is no backlog by this id");
        throw new EntityNotFoundException("There is no backlog by this id");
    }

    @Override
    public List<Backlog> showAllBacklogs(Integer currentPage, Integer recordsPerPage) {
        if (currentPage < 0 || recordsPerPage < 0) {
            log.error("Invalid number of current page or records per page");
            throw new InvalidPaginatingException("Invalid number of current page or records per page");
        }

        PageRequest pageRequest = PageRequest.of(currentPage, recordsPerPage);
        Page<BacklogEntity> result = backlogRepository.findAll(pageRequest);

        return result.isEmpty() ? Collections.emptyList()
                : result.stream()
                .map(mapper::mapBacklogEntityToBacklog)
                .collect(Collectors.toList());
    }

    @Override
    public long showNumberOfRows() {
        return backlogRepository.count();
    }
}
