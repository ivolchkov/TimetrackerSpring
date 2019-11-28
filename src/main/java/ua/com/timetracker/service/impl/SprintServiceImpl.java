package ua.com.timetracker.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ua.com.timetracker.domain.Sprint;
import ua.com.timetracker.entity.SprintEntity;
import ua.com.timetracker.exception.InvalidEntityCreation;
import ua.com.timetracker.exception.InvalidPaginatingException;
import ua.com.timetracker.repository.SprintRepository;
import ua.com.timetracker.service.SprintService;
import ua.com.timetracker.service.mapper.SprintMapper;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Log4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class SprintServiceImpl implements SprintService {
    private final SprintRepository sprintRepository;
    private final SprintMapper mapper;

    @Override
    public Sprint createSprint(Sprint sprint) {
        if (Objects.isNull(sprint)) {
            log.warn("Sprint is not valid");
            throw new InvalidEntityCreation("Sprint is not valid");
        }

        SprintEntity entity = sprintRepository.save(mapper.mapSprintToSprintEntity(sprint));

        return mapper.mapSprintEntityToSprint(entity);
    }

    @Override
    public List<Sprint> showAllSprints(int currentPage, int recordsPerPage) {
        if (currentPage < 0 || recordsPerPage < 0) {
            log.error("Invalid number of current page or records per page");
            throw new InvalidPaginatingException("Invalid number of current page or records per page");
        }

        PageRequest pageRequest = PageRequest.of(currentPage, recordsPerPage);
        Page<SprintEntity> result = sprintRepository.findAll(pageRequest);

        return result.isEmpty() ? Collections.emptyList() :
                result.stream()
                        .map(mapper::mapSprintEntityToSprint)
                        .collect(Collectors.toList());
    }

    @Override
    public long showNumberOfRows() {
        return sprintRepository.count();
    }
}
