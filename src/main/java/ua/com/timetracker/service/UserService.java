package ua.com.timetracker.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ua.com.timetracker.domain.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    User register(User user);

    List<User> findAll(int currentPage, int recordsPerPage);

    long showNumberOfRows();
}
