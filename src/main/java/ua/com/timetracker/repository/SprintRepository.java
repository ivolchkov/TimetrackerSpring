package ua.com.timetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.timetracker.entity.SprintEntity;

@Repository
public interface SprintRepository extends JpaRepository<SprintEntity, Integer> {
}
