package project.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "backlogs")
public class BacklogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "backlog_id")
    private Integer id;

    @Column(name = "backlog_project_name", nullable = false, length = 45)
    private String projectName;

    @Column(name = "backlog_description", length = 1000)
    private String description;

    @OneToMany(mappedBy = "backlog", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GoalEntity> goals;

    @OneToMany(mappedBy = "backlog", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserEntity> users;

    public BacklogEntity(String projectName, String description) {
        this.projectName = projectName;
        this.description = description;
    }

    public BacklogEntity(Integer id) {
        this.id = id;
    }
}
