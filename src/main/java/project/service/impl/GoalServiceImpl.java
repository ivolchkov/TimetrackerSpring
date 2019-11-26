package project.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import project.domain.Goal;
import project.entity.GoalEntity;
import project.exception.EntityNotFoundException;
import project.exception.InvalidEntityCreation;
import project.exception.InvalidPaginatingException;
import project.repository.GoalRepository;
import project.service.GoalService;
import project.service.mapper.GoalMapper;


import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class GoalServiceImpl implements GoalService {
    private final GoalRepository goalRepository;
    private final GoalMapper mapper;

    @Override
    public Goal createGoal(Goal goal) {
        if (Objects.isNull(goal) ) {
            log.warn("Goal is not valid");
            throw new InvalidEntityCreation("Goal is not valid");
        }

        GoalEntity entity = goalRepository.save(mapper.mapGoalToGoalEntity(goal));

        return mapper.mapGoalEntityToGoal(entity);
    }

    @Override
    public Goal showGoalById(Integer id) {
        if (Objects.nonNull(id)) {
            Optional<GoalEntity> entity = goalRepository.findById(id);

            if ( entity.isPresent() ) {
                return mapper.mapGoalEntityToGoal(entity.get());
            }

            log.warn("There is no goal by this id");
            throw new EntityNotFoundException("There is no goal by this id");
        }
        log.warn("There is no goal by this id");
        throw new EntityNotFoundException("There is no goal by this id");
    }

    @Override
    public List<Goal> showAllGoals(int currentPage, int recordsPerPage) {
        if ( currentPage < 0 || recordsPerPage < 0 ) {
            log.error("Invalid number of current page or records per page");
            throw new InvalidPaginatingException("Invalid number of current page or records per page");
        }

        PageRequest pageRequest = PageRequest.of(currentPage, recordsPerPage);
        Page<GoalEntity> result = goalRepository.findAll(pageRequest);

        return result.isEmpty() ? Collections.emptyList()
                : result.stream()
                .map(mapper::mapGoalEntityToGoal)
                .collect(Collectors.toList());
    }

    @Override
    public long showNumberOfRows() {
        return goalRepository.count();
    }
}
