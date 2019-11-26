package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.entity.GoalEntity;

@Repository
public interface GoalRepository extends JpaRepository<GoalEntity, Integer> {
}
