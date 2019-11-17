package ua.epam.timetracker.Timetracker.Spring.service;

import ua.epam.timetracker.Timetracker.Spring.domain.Goal;

import java.util.List;

public interface GoalService {
    Goal createGoal(Goal goal);

    Goal showGoalById(Integer id);

    List<Goal> showAllGoals(Integer currentPage, Integer recordsPerPage);

    long showNumberOfRows();
}
