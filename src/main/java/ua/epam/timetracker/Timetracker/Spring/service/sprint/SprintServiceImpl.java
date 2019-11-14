package ua.epam.timetracker.Timetracker.Spring.service.sprint;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.epam.timetracker.Timetracker.Spring.domain.sprint.Sprint;
import ua.epam.timetracker.Timetracker.Spring.entity.sprint.SprintEntity;
import ua.epam.timetracker.Timetracker.Spring.exception.InvalidEntityCreation;
import ua.epam.timetracker.Timetracker.Spring.exception.InvalidPaginatingException;
import ua.epam.timetracker.Timetracker.Spring.service.mapper.SprintMapper;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SprintServiceImpl implements SprintService {
    private static final Logger LOGGER = Logger.getLogger(SprintServiceImpl.class);

//    private final SprintDao sprintDao;
    private final SprintMapper mapper;

//    public SprintServiceImpl(SprintDao sprintDao, SprintMapper mapper) {
//        this.sprintDao = sprintDao;
//        this.mapper = mapper;
//    }
    @Autowired
    public SprintServiceImpl(SprintMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public boolean createSprint(Sprint sprint) {
//        if (Objects.isNull(sprint) ) {
//            LOGGER.warn("Sprint is not valid");
//            throw new InvalidEntityCreation("Sprint is not valid");
//        }
//
//        return sprintDao.save(mapper.mapSprintToSprintEntity(sprint));
        return true;
    }

    @Override
    public List<Sprint> showAllSprints(Integer currentPage, Integer recordsPerPage) {
//        if ( currentPage == 0 || recordsPerPage == 0 ) {
//            LOGGER.error("Invalid number of current page or records per page");
//            throw new InvalidPaginatingException("Invalid number of current page or records per page");
//        }
//
//        Integer offset = currentPage * recordsPerPage - recordsPerPage;
//        List<SprintEntity> result = sprintDao.findAll(offset, recordsPerPage);
//
//        return result.isEmpty() ? Collections.emptyList()
//                : result.stream()
//                .map(mapper::mapSprintEntityToSprint)
//                .collect(Collectors.toList());
        return null;
    }

    @Override
    public Integer showNumberOfRows() {

//        return sprintDao.findAmountOfRows();
        return null;
    }
}
