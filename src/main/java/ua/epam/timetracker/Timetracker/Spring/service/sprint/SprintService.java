package ua.epam.timetracker.Timetracker.Spring.service.sprint;

import ua.epam.timetracker.Timetracker.Spring.domain.sprint.Sprint;

import java.util.List;

public interface SprintService {
    boolean createSprint(Sprint sprint);

    List<Sprint> showAllSprints(Integer currentPage, Integer recordsPerPage);

    Integer showNumberOfRows();
}
