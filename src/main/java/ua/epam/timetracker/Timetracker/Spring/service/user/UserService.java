package ua.epam.timetracker.Timetracker.Spring.service.user;
import ua.epam.timetracker.Timetracker.Spring.domain.backlog.Backlog;
import ua.epam.timetracker.Timetracker.Spring.domain.user.User;

import java.util.List;

public interface UserService {
    boolean register(User user);

    User login(String email, String password);

    List<User> findTeam(Integer backlogId, Integer currentPage, Integer recordsPerPage);

    List<User> findAll(Integer currentPage, Integer recordsPerPage);

    void addProjectToUser(User user, Backlog backlog);

    Integer showNumberOfRows();
}
