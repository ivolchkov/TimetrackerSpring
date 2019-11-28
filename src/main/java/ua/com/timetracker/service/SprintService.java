package ua.com.timetracker.service;

import ua.com.timetracker.domain.Sprint;

import java.util.List;

public interface SprintService {
    Sprint createSprint(Sprint sprint);

    List<Sprint> showAllSprints(int currentPage, int recordsPerPage);

    long showNumberOfRows();
}
