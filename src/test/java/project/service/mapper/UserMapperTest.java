package project.service.mapper;

import org.junit.Test;
import project.domain.Role;
import project.domain.User;
import project.entity.UserEntity;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class UserMapperTest {
    private static final UserEntity ENTITY = getUserEntity();

    private static final User DOMAIN = getUser();

    private static final UserMapper MAPPER = new UserMapper();

    @Test
    public void mapUserToUserEntityShouldMapToEntity() {
        UserEntity actual = MAPPER.mapUserToUserEntity(DOMAIN);

        assertThat(actual.getName(), equalTo(ENTITY.getName()));
        assertThat(actual.getSurname(), equalTo(ENTITY.getSurname()));
        assertThat(actual.getEmail(), equalTo(ENTITY.getEmail()));
        assertThat(actual.getPassword(), equalTo(ENTITY.getPassword()));
        assertThat(actual.getRole(), equalTo(ENTITY.getRole()));
    }

    @Test
    public void mapUserEntityToUserShouldMapToDomain() {
        ENTITY.setId(1);
        User actual = MAPPER.mapUserEntityToUser(ENTITY);

        assertThat(actual.getId(), equalTo(DOMAIN.getId()));
        assertThat(actual.getName(), equalTo(DOMAIN.getName()));
        assertThat(actual.getSurname(), equalTo(DOMAIN.getSurname()));
        assertThat(actual.getEmail(), equalTo(DOMAIN.getEmail()));
        assertThat(actual.getPassword(), equalTo(DOMAIN.getPassword()));
        assertThat(actual.getRole(), equalTo(DOMAIN.getRole()));
    }

    @Test
    public void mapUserToUserEntityShouldReturnNull() {
        UserEntity actual = MAPPER.mapUserToUserEntity(null);

        assertThat(actual, equalTo(null));
    }

    @Test
    public void mapUserEntityToUserShouldReturnNull() {
        User actual = MAPPER.mapUserEntityToUser(null);

        assertThat(actual, equalTo(null));
    }

    private static UserEntity getUserEntity() {
        return new UserEntity("Igor", "Volchkov", "igorik@gmail.com", "Babushka3529", Role.DEVELOPER);
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