package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.entity.SprintEntity;

@Repository
public interface SprintRepository extends JpaRepository<SprintEntity, Integer> {
}
