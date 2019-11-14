package ua.epam.timetracker.Timetracker.Spring.entity.story;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.epam.timetracker.Timetracker.Spring.domain.story.Status;
import ua.epam.timetracker.Timetracker.Spring.entity.goal.GoalEntity;
import ua.epam.timetracker.Timetracker.Spring.entity.sprint.SprintEntity;
import ua.epam.timetracker.Timetracker.Spring.entity.user.UserEntity;

import javax.persistence.*;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@Entity
@Table(name = "stories")
public class StoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "story_id")
    private Integer id;

    @Column(name = "story_name")
    private String name;

    @Column(name = "story_spent_time")
    private LocalTime spentTime;

    @Column(name = "story_status")
    private Status status;

    @Column(name = "story_description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goal_id")
    private GoalEntity goal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sprint_id")
    private SprintEntity sprint;
}
