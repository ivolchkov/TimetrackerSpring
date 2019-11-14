package ua.epam.timetracker.Timetracker.Spring.service.backlog;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.epam.timetracker.Timetracker.Spring.domain.backlog.Backlog;
import ua.epam.timetracker.Timetracker.Spring.entity.backlog.BacklogEntity;
import ua.epam.timetracker.Timetracker.Spring.exception.EntityNotFoundException;
import ua.epam.timetracker.Timetracker.Spring.exception.InvalidPaginatingException;
import ua.epam.timetracker.Timetracker.Spring.service.mapper.BacklogMapper;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BacklogServiceImpl implements BacklogService {
    private static final Logger LOGGER = Logger.getLogger(BacklogServiceImpl.class);

//    private final BacklogDao backlogDao;
    private final BacklogMapper mapper;

//    public BacklogServiceImpl(BacklogDao backlogDao, BacklogMapper mapper) {
//        this.backlogDao = backlogDao;
//        this.mapper = mapper;
//    }
    @Autowired
    public BacklogServiceImpl(BacklogMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public boolean createBacklog(Backlog backlog) {
//        if (Objects.isNull(backlog)) {
//            LOGGER.warn("Backlog is not valid");
//            throw new InvalidEntityCreation("Backlog is not valid");
//        }
//
//        return backlogDao.save(mapper.mapBacklogToBacklogEntity(backlog));
        return true;
    }

    @Override
    public Backlog showBacklogById(Integer id) {
//        if (Objects.nonNull(id)) {
//            Optional<BacklogEntity> entity = backlogDao.findById(id);
//
//            if ( entity.isPresent() ) {
//                return mapper.mapBacklogEntityToBacklog(entity.get());
//            }
//
//            LOGGER.warn("There is no backlog by this id");
//            throw new EntityNotFoundException("There is no backlog by this id");
//        }
//        LOGGER.warn("There is no backlog by this id");
//        throw new EntityNotFoundException("There is no backlog by this id");
        return null;
    }

    @Override
    public List<Backlog> showAllBacklogs(Integer currentPage, Integer recordsPerPage) {
//        if (currentPage == 0 || recordsPerPage == 0) {
//            LOGGER.error("Invalid number of current page or records per page");
//            throw new InvalidPaginatingException("Invalid number of current page or records per page");
//        }
//
//        Integer offset = currentPage * recordsPerPage - recordsPerPage;
//        List<BacklogEntity> result = backlogDao.findAll(offset, recordsPerPage);
//
//        return result.isEmpty() ? Collections.emptyList()
//                : result.stream()
//                .map(mapper::mapBacklogEntityToBacklog)
//                .collect(Collectors.toList());
        return null;
    }

    @Override
    public Integer showNumberOfRows() {
//        return backlogDao.findAmountOfRows();
        return 10;
    }
}
