package ua.epam.timetracker.Timetracker.Spring.entity.backlog;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.epam.timetracker.Timetracker.Spring.entity.goal.GoalEntity;
import ua.epam.timetracker.Timetracker.Spring.entity.user.UserEntity;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@Entity
@Table(name = "backlogs")
public class BacklogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "backlog_id")
    private Integer id;

    @Column(name = "backlog_project_name")
    private String projectName;

    @Column(name = "backlog_description")
    private String description;

    @OneToMany(mappedBy = "backlog", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GoalEntity> goals;

    @OneToMany(mappedBy = "backlog", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserEntity> users;

}
