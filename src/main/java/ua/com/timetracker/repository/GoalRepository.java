package ua.com.timetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.timetracker.entity.GoalEntity;

@Repository
public interface GoalRepository extends JpaRepository<GoalEntity, Integer> {
}
