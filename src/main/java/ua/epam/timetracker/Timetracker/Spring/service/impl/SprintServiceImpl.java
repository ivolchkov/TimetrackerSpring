package ua.epam.timetracker.Timetracker.Spring.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ua.epam.timetracker.Timetracker.Spring.domain.Sprint;
import ua.epam.timetracker.Timetracker.Spring.entity.SprintEntity;
import ua.epam.timetracker.Timetracker.Spring.exception.InvalidEntityCreation;
import ua.epam.timetracker.Timetracker.Spring.exception.InvalidPaginatingException;
import ua.epam.timetracker.Timetracker.Spring.repository.SprintRepository;
import ua.epam.timetracker.Timetracker.Spring.service.SprintService;
import ua.epam.timetracker.Timetracker.Spring.service.mapper.SprintMapper;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SprintServiceImpl implements SprintService {
    private static final Logger LOGGER = Logger.getLogger(SprintServiceImpl.class);

    private final SprintRepository sprintRepository;
    private final SprintMapper mapper;

    @Autowired
    public SprintServiceImpl(SprintRepository sprintRepository, SprintMapper mapper) {
        this.sprintRepository = sprintRepository;
        this.mapper = mapper;
    }

    @Override
    public Sprint createSprint(Sprint sprint) {
        if (Objects.isNull(sprint) ) {
            LOGGER.warn("Sprint is not valid");
            throw new InvalidEntityCreation("Sprint is not valid");
        }

        SprintEntity entity = sprintRepository.save(mapper.mapSprintToSprintEntity(sprint));

        return mapper.mapSprintEntityToSprint(entity);
    }

    @Override
    public List<Sprint> showAllSprints(Integer currentPage, Integer recordsPerPage) {
        if ( currentPage <= 0 || recordsPerPage <= 0 ) {
            LOGGER.error("Invalid number of current page or records per page");
            throw new InvalidPaginatingException("Invalid number of current page or records per page");
        }

        PageRequest pageRequest = PageRequest.of(currentPage, recordsPerPage);
        Page<SprintEntity> result = sprintRepository.findAll(pageRequest);

        return result.isEmpty() ? Collections.emptyList()
                : result.stream()
                .map(mapper::mapSprintEntityToSprint)
                .collect(Collectors.toList());
    }

    @Override
    public long showNumberOfRows() {
        return sprintRepository.count();
    }
}
