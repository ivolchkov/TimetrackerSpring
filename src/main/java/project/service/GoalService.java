package project.service;


import project.domain.Goal;

import java.util.List;

public interface GoalService {
    Goal createGoal(Goal goal);

    Goal showGoalById(Integer id);

    List<Goal> showAllGoals(int currentPage, int recordsPerPage);

    long showNumberOfRows();
}
