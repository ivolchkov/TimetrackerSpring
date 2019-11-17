package ua.epam.timetracker.Timetracker.Spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.epam.timetracker.Timetracker.Spring.entity.SprintEntity;

@Repository
public interface SprintRepository extends JpaRepository<SprintEntity, Integer> {
}
