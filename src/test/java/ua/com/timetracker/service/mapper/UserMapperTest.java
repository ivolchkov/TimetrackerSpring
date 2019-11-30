package ua.com.timetracker.service.mapper;

import org.junit.Test;
import ua.com.timetracker.domain.Role;
import ua.com.timetracker.domain.User;
import ua.com.timetracker.entity.UserEntity;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserMapperTest {
    private static final UserEntity USER_ENTITY = getUserEntity();

    private static final User DOMAIN = getUser();

    private final UserMapper userMapper = new UserMapper();

    @Test
    public void mapUserToUserEntityShouldMapToEntity() {
        UserEntity actual = userMapper.mapUserToUserEntity(DOMAIN);

        assertThat(actual.getName(), is(USER_ENTITY.getName()));
        assertThat(actual.getSurname(), is(USER_ENTITY.getSurname()));
        assertThat(actual.getEmail(), is(USER_ENTITY.getEmail()));
        assertThat(actual.getPassword(), is(USER_ENTITY.getPassword()));
        assertThat(actual.getRole(), is(USER_ENTITY.getRole()));
    }

    @Test
    public void mapUserEntityToUserShouldMapToDomain() {
        USER_ENTITY.setId(1);
        User actual = userMapper.mapUserEntityToUser(USER_ENTITY);

        assertThat(actual.getId(), is(DOMAIN.getId()));
        assertThat(actual.getName(), is(DOMAIN.getName()));
        assertThat(actual.getSurname(), is(DOMAIN.getSurname()));
        assertThat(actual.getEmail(), is(DOMAIN.getEmail()));
        assertThat(actual.getPassword(), is(DOMAIN.getPassword()));
        assertThat(actual.getRole(), is(DOMAIN.getRole()));
    }

    @Test
    public void mapUserToUserEntityShouldReturnNull() {
        UserEntity actual = userMapper.mapUserToUserEntity(null);

        assertThat(actual, is(nullValue()));
    }

    @Test
    public void mapUserEntityToUserShouldReturnNull() {
        User actual = userMapper.mapUserEntityToUser(null);

        assertThat(actual, is(nullValue()));
    }

    private static UserEntity getUserEntity() {
        return new UserEntity("Igor", "Volchkov",
                "igorik@gmail.com", "Babushka3529", Role.DEVELOPER);
    }

    private static User getUser() {
        return User.builder()
                .id(1)
                .name("Igor")
                .surname("Volchkov")
                .password("Babushka3529")
                .email("igorik@gmail.com")
                .role(Role.DEVELOPER)
                .build();
    }
}