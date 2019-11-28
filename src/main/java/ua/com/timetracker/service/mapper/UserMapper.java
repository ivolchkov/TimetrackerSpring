package ua.com.timetracker.service.mapper;

import org.springframework.stereotype.Component;
import ua.com.timetracker.domain.User;
import ua.com.timetracker.entity.UserEntity;

import java.util.Objects;


@Component
public class UserMapper {
    public UserEntity mapUserToUserEntity(User domain) {
        return Objects.isNull(domain) ? null
                : new UserEntity(domain.getName(), domain.getSurname(),
                domain.getEmail(), domain.getPassword(), domain.getRole());
    }


    public User mapUserEntityToUser(UserEntity entity) {
        return Objects.isNull(entity) ? null :
                User.builder()
                        .id(entity.getId())
                        .name(entity.getName())
                        .surname(entity.getSurname())
                        .email(entity.getEmail())
                        .password(entity.getPassword())
                        .role(entity.getRole())
                        .build();
    }
}
