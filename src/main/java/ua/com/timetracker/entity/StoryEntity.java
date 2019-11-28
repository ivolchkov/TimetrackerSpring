package ua.com.timetracker.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import ua.com.timetracker.domain.Status;

import javax.persistence.*;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "stories")
public class StoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "story_id")
    private Integer id;

    @Column(name = "story_name", nullable = false, length = 45)
    private String name;

    @Column(name = "story_spent_time", nullable = false)
    private LocalTime spentTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "story_status", nullable = false)
    private Status status;

    @Column(name = "story_description", length = 1000)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goal_id", nullable = false)
    private GoalEntity goal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sprint_id")
    private SprintEntity sprint;

    public StoryEntity(String name, LocalTime spentTime, Status status, String description, GoalEntity goal) {
        this.name = name;
        this.spentTime = spentTime;
        this.status = status;
        this.description = description;
        this.goal = goal;
    }

    public StoryEntity(Integer id, SprintEntity sprint) {
        this.id = id;
        this.sprint = sprint;
    }

    public StoryEntity(Integer id, UserEntity user) {
        this.id = id;
        this.user = user;
    }
}
