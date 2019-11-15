package ua.epam.timetracker.Timetracker.Spring.entity.sprint;

import lombok.Data;
import lombok.NoArgsConstructor;
import ua.epam.timetracker.Timetracker.Spring.entity.story.StoryEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "sprints")
public class SprintEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sprint_id")
    private Integer id;

    @Column(name = "sprint_name", nullable = false, length = 45)
    private String name;

    @Column(name = "sprint_start", nullable = false)
    private LocalDate start;

    @Column(name = "sprint_end", nullable = false)
    private LocalDate end;

    @Column(name = "sprint_description", length = 1000)
    private String description;

    @OneToMany(mappedBy = "sprint", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StoryEntity> stories;

    public SprintEntity(String name, LocalDate start, LocalDate end, String description) {
        this.name = name;
        this.start = start;
        this.end = end;
        this.description = description;
    }

    public SprintEntity(Integer id) {
        this.id = id;
    }
}
