package ua.com.timetracker.service;


import ua.com.timetracker.domain.Goal;

import java.util.List;

public interface GoalService {
    Goal createGoal(Goal goal);

    Goal showGoalById(Integer id);

    List<Goal> showAllGoals(int currentPage, int recordsPerPage);

    long showNumberOfRows();
}
