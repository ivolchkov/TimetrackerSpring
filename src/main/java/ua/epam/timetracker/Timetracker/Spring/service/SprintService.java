package ua.epam.timetracker.Timetracker.Spring.service;

import ua.epam.timetracker.Timetracker.Spring.domain.Sprint;

import java.util.List;

public interface SprintService {
    Sprint createSprint(Sprint sprint);

    List<Sprint> showAllSprints(Integer currentPage, Integer recordsPerPage);

    long showNumberOfRows();
}
