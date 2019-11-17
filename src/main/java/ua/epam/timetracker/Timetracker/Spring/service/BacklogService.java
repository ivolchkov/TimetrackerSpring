package ua.epam.timetracker.Timetracker.Spring.service;

import ua.epam.timetracker.Timetracker.Spring.domain.Backlog;

import java.util.List;

public interface BacklogService {
    Backlog createBacklog(Backlog backlog);

    Backlog showBacklogById(Integer id);

    List<Backlog> showAllBacklogs(Integer currentPage, Integer recordsPerPage);

    long showNumberOfRows();
}
