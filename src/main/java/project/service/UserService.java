package project.service;

import project.domain.User;

import java.util.List;

public interface UserService {
    User register(User user);

    User login(String email, String password);

    List<User> findAll(int currentPage, int recordsPerPage);

    long showNumberOfRows();
}
