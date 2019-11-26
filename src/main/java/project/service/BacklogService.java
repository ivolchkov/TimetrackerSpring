package project.service;

import project.domain.Backlog;

import java.util.List;

public interface BacklogService {
    Backlog createBacklog(Backlog backlog);

    Backlog showBacklogById(Integer id);

    List<Backlog> showAllBacklogs(int currentPage, int recordsPerPage);

    long showNumberOfRows();
}
