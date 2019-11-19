package ua.epam.timetracker.Timetracker.Spring.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import ua.epam.timetracker.Timetracker.Spring.domain.Role;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "user_name", nullable = false, length = 45)
    private String name;

    @Column(name = "user_surname", nullable = false, length = 45)
    private String surname;

    @Column(name = "user_email", nullable = false, unique = true, length = 320)
    private String email;

    @Column(name = "user_password", nullable = false, length = 100)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role", nullable = false, length = 45)
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "backlog_id")
    private BacklogEntity backlog;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StoryEntity> stories;

    public UserEntity(String name, String surname, String email, String password, Role role) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public UserEntity(Integer id, BacklogEntity backlog) {
        this.id = id;
        this.backlog = backlog;
    }

    public UserEntity(Integer id) {
        this.id = id;
    }
}
