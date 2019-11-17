package ua.epam.timetracker.Timetracker.Spring.service;
import ua.epam.timetracker.Timetracker.Spring.domain.User;

import java.util.List;

public interface UserService {
    User register(User user);

    User login(String email, String password);

    List<User> findAll(Integer currentPage, Integer recordsPerPage);

    long showNumberOfRows();
}
