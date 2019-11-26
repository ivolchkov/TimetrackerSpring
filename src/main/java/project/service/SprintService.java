package project.service;

import project.domain.Sprint;

import java.util.List;

public interface SprintService {
    Sprint createSprint(Sprint sprint);

    List<Sprint> showAllSprints(int currentPage, int recordsPerPage);

    long showNumberOfRows();
}
