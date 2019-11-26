package project.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.domain.Status;
import project.entity.SprintEntity;
import project.entity.StoryEntity;
import project.entity.UserEntity;


@Repository
public interface StoryRepository extends JpaRepository<StoryEntity, Integer> {
    Page<StoryEntity> findByStatus(Status status, Pageable page);

    Page<StoryEntity> findByGoalId(Integer id, Pageable page);

    Page<StoryEntity> findByUserId(Integer id, Pageable page);

    Page<StoryEntity> findBySprintId(Integer id, Pageable page);

    Page<StoryEntity> findByUserIdIsNull(Pageable page);

    long countByUserIdIsNull();

    long countByUserId(Integer id);

    @Modifying
    @Transactional()
    @Query("UPDATE StoryEntity e SET e.user = :user WHERE e.id = :id")
    void updateUserId(@Param("user") UserEntity user, @Param("id") Integer storyId);

    @Modifying
    @Transactional
    @Query("UPDATE StoryEntity e SET e.sprint = :sprint WHERE e.id = :id")
    void updateSprintId(@Param("sprint") SprintEntity sprintId, @Param("id") Integer storyId);
}
