package ua.epam.timetracker.Timetracker.Spring.entity.goal;

import lombok.Data;
import lombok.NoArgsConstructor;
import ua.epam.timetracker.Timetracker.Spring.entity.backlog.BacklogEntity;
import ua.epam.timetracker.Timetracker.Spring.entity.story.StoryEntity;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "goals")
public class GoalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "goal_id")
    private Integer id;

    @Column(name = "goal_name", nullable = false, length = 45)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "backlog_id", nullable = false)
    private BacklogEntity backlog;

    @OneToMany(mappedBy = "goal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StoryEntity> stories;

    public GoalEntity(String name, BacklogEntity backlogEntity) {
        this.name = name;
        this.backlog = backlogEntity;
    }

    public GoalEntity(Integer id) {
        this.id = id;
    }
}
