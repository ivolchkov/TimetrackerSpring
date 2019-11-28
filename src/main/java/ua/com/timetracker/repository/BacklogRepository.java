package ua.com.timetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.timetracker.entity.BacklogEntity;

@Repository
public interface BacklogRepository extends JpaRepository<BacklogEntity, Integer> {
}
