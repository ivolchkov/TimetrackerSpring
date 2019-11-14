package ua.epam.timetracker.Timetracker.Spring.service.goal;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.epam.timetracker.Timetracker.Spring.domain.goal.Goal;
import ua.epam.timetracker.Timetracker.Spring.entity.goal.GoalEntity;
import ua.epam.timetracker.Timetracker.Spring.exception.EntityNotFoundException;
import ua.epam.timetracker.Timetracker.Spring.exception.InvalidPaginatingException;
import ua.epam.timetracker.Timetracker.Spring.service.mapper.GoalMapper;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GoalServiceImpl implements GoalService {
    private static final Logger LOGGER = Logger.getLogger(GoalServiceImpl.class);

//    private final GoalDao goalDao;
    private final GoalMapper mapper;

//    public GoalServiceImpl(GoalDao goalDao, GoalMapper mapper) {
//        this.goalDao = goalDao;
//        this.mapper = mapper;
//    }
    @Autowired
    public GoalServiceImpl(GoalMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public boolean createGoal(Goal goal) {
//        if (Objects.isNull(goal) ) {
//            LOGGER.warn("Goal is not valid");
//            throw new InvalidEntityCreation("Goal is not valid");
//        }
//
//        return goalDao.save(mapper.mapGoalToGoalEntity(goal));
        return true;
    }

    @Override
    public Goal showGoalById(Integer id) {
//        if (Objects.nonNull(id)) {
//            Optional<GoalEntity> entity = goalDao.findById(id);
//
//            if ( entity.isPresent() ) {
//                return mapper.mapGoalEntityToGoal(entity.get());
//            }
//
//            LOGGER.warn("There is no goal by this id");
//            throw new EntityNotFoundException("There is no goal by this id");
//        }
//        LOGGER.warn("There is no goal by this id");
//        throw new EntityNotFoundException("There is no goal by this id");
        return null;
    }

    @Override
    public List<Goal> showAllGoals(Integer currentPage, Integer recordsPerPage) {
//        if ( currentPage == 0 || recordsPerPage == 0 ) {
//            LOGGER.error("Invalid number of current page or records per page");
//            throw new InvalidPaginatingException("Invalid number of current page or records per page");
//        }
//
//        Integer offset = currentPage * recordsPerPage - recordsPerPage;
//        List<GoalEntity> result = goalDao.findAll(offset, recordsPerPage);
//
//        return result.isEmpty() ? Collections.emptyList()
//                : result.stream()
//                .map(mapper::mapGoalEntityToGoal)
//                .collect(Collectors.toList());
        return null;
    }

    @Override
    public Integer showNumberOfRows() {
//
//        return goalDao.findAmountOfRows();
        return 30;
    }
}
