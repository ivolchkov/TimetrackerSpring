package ua.epam.timetracker.Timetracker.Spring.service.backlog;

import ua.epam.timetracker.Timetracker.Spring.domain.backlog.Backlog;

import java.util.List;

public interface BacklogService {
    boolean createBacklog(Backlog backlog);

    Backlog showBacklogById(Integer id);

    List<Backlog> showAllBacklogs(Integer currentPage, Integer recordsPerPage);

    Integer showNumberOfRows();
}
