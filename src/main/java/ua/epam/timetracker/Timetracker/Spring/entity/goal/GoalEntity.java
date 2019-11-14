package ua.epam.timetracker.Timetracker.Spring.entity.goal;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.epam.timetracker.Timetracker.Spring.entity.backlog.BacklogEntity;
import ua.epam.timetracker.Timetracker.Spring.entity.story.StoryEntity;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@Entity
@Table(name = "goals")
public class GoalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "goal_id")
    private Integer id;

    @Column(name = "goal_name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "backlog_id")
    private BacklogEntity backlog;

    @OneToMany(mappedBy = "goal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StoryEntity> stories;
}
