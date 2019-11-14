package ua.epam.timetracker.Timetracker.Spring.entity.sprint;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.epam.timetracker.Timetracker.Spring.entity.story.StoryEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@Entity
@Table(name = "sprints")
public class SprintEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sprint_id")
    private Integer id;

    @Column(name = "sprint_name")
    private String name;

    @Column(name = "sprint_start")
    private LocalDate start;

    @Column(name = "sprint_end")
    private LocalDate end;

    @Column(name = "sprint_description")
    private String description;

    @OneToMany(mappedBy = "sprint", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StoryEntity> stories;
}
