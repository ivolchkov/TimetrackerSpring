package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.entity.BacklogEntity;

@Repository
public interface BacklogRepository extends JpaRepository<BacklogEntity, Integer> {
}
