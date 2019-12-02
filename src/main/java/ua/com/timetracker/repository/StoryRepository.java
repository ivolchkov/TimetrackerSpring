package ua.com.timetracker.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.timetracker.entity.StoryEntity;
import ua.com.timetracker.entity.UserEntity;


@Repository
public interface StoryRepository extends JpaRepository<StoryEntity, Integer> {
    Page<StoryEntity> findByUserId(Integer id, Pageable page);

    Page<StoryEntity> findByUserIdIsNull(Pageable page);

    long countByUserIdIsNull();

    long countByUserId(Integer id);

    @Modifying
    @Transactional()
    @Query("UPDATE StoryEntity e SET e.user = :user WHERE e.id = :id")
    void updateUserId(@Param("user") UserEntity user, @Param("id") Integer storyId);
}
