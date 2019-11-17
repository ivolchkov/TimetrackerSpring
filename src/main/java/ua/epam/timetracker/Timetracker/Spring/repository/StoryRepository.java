package ua.epam.timetracker.Timetracker.Spring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.epam.timetracker.Timetracker.Spring.domain.Status;
import ua.epam.timetracker.Timetracker.Spring.entity.StoryEntity;

@Repository
public interface StoryRepository extends JpaRepository<StoryEntity, Integer> {
    Page<StoryEntity> findByStatus(Status status, Pageable page);

    Page<StoryEntity> findByGoalId(Integer id, Pageable page);

    Page<StoryEntity> findByUserId(Integer id, Pageable page);

    Page<StoryEntity> findBySprintId(Integer id, Pageable page);

    Page<StoryEntity> findWithoutUserId(Pageable page);

    long countByUserIdIsNotNull();

    long countByUserId(Integer id);

    void updateUserId(StoryEntity story);

    void updateSprintId(StoryEntity story);
}
