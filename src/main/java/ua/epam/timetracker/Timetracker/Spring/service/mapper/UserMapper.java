package ua.epam.timetracker.Timetracker.Spring.service.mapper;

import org.springframework.stereotype.Component;
import ua.epam.timetracker.Timetracker.Spring.domain.backlog.Backlog;
import ua.epam.timetracker.Timetracker.Spring.domain.user.User;
import ua.epam.timetracker.Timetracker.Spring.entity.backlog.BacklogEntity;
import ua.epam.timetracker.Timetracker.Spring.entity.user.UserEntity;

@Component
public class UserMapper {
    public UserEntity mapUserToUserEntity(User domain) {
        return UserEntity.builder()
                .name(domain.getName())
                .surname(domain.getSurname())
                .email(domain.getEmail())
                .password(domain.getPassword())
                .role(domain.getRole())
                .build();
    }

    public UserEntity mapUserToUserEntity(User domain, Backlog backlog) {
        return UserEntity.builder()
                .id(domain.getId())
                .backlog(BacklogEntity.builder()
                        .id(backlog.getId())
                        .build())
                .build();
    }

    public User mapUserEntityToUser(UserEntity entity) {
        return User.builder()
                .id(entity.getId())
                .name(entity.getName())
                .surname(entity.getSurname())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .role(entity.getRole())
                .build();
    }
}
