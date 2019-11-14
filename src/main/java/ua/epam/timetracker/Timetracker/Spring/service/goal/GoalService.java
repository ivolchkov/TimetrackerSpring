package ua.epam.timetracker.Timetracker.Spring.service.goal;

import ua.epam.timetracker.Timetracker.Spring.domain.goal.Goal;

import java.util.List;

public interface GoalService {
    boolean createGoal(Goal goal);

    Goal showGoalById(Integer id);

    List<Goal> showAllGoals(Integer currentPage, Integer recordsPerPage);

    Integer showNumberOfRows();
}
