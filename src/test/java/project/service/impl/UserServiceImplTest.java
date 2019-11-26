package project.service.impl;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import project.domain.Role;
import project.domain.User;
import project.entity.UserEntity;
import project.exception.AlreadyRegisteredException;
import project.exception.EntityNotFoundException;
import project.exception.InvalidPaginatingException;
import project.exception.InvalidRegistrationException;
import project.repository.UserRepository;
import project.service.mapper.UserMapper;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {UserServiceImpl.class})
public class UserServiceImplTest {
    private static final UserEntity ENTITY = getUserEntity();

    private static final User USER = getUser();

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @MockBean
    private UserRepository repository;

    @MockBean
    private UserMapper mapper;

    @MockBean
    private BCryptPasswordEncoder encoder;

    @Autowired
    private UserServiceImpl userService;

    @After
    public void resetMock() {
        reset(repository, encoder);
    }

    @Test
    public void registerShouldRegisterUser() {
        when(repository.save(any(UserEntity.class))).thenReturn(ENTITY);
        when(mapper.mapUserToUserEntity(any(User.class))).thenReturn(ENTITY);
        when(mapper.mapUserEntityToUser(any(UserEntity.class))).thenReturn(USER);
        when(encoder.encode(anyString())).thenReturn(ENTITY.getPassword());

        User actual = userService.register(USER);

        verify(repository).save(any(UserEntity.class));
        verify(encoder).encode(anyString());
        verify(mapper).mapUserEntityToUser(any(UserEntity.class));
        verify(mapper).mapUserToUserEntity(any(User.class));

        assertThat(actual, equalTo(USER));
    }

    @Test
    public void findByEmailShouldThrowRuntimeExceptionWhenRegisterUser() {
        exception.expect(AlreadyRegisteredException.class);
        exception.expectMessage("User is already registered by this e-mail");

        when(repository.findByEmail(anyString())).thenReturn(Optional.of(ENTITY));

        userService.register(USER);

        verify(repository).findByEmail(anyString());
    }

    @Test
    public void registerShouldThrowInvalidRegistrationExceptionWhenRegisterNullUser() {
        exception.expect(InvalidRegistrationException.class);
        exception.expectMessage("User is not valid");

        userService.register(null);
    }

    @Test
    public void loginShouldLoginUser() {
        when(repository.findByEmail("igorik@gmail.com")).thenReturn(Optional.of(ENTITY));
        when(encoder.matches("Babushka3529", "ENCODED")).thenReturn(true);
        when(mapper.mapUserEntityToUser(any(UserEntity.class))).thenReturn(USER);

        User actual = userService.login("igorik@gmail.com", "Babushka3529");

        verify(repository).findByEmail(anyString());
        verify(encoder).matches(anyString(), anyString());
        verify(mapper).mapUserEntityToUser(any(UserEntity.class));

        assertThat(actual, equalTo(USER));
    }

    @Test
    public void loginShouldThrowUserNotFoundExceptionWithIncorrectPassword() {
        exception.expect(EntityNotFoundException.class);
        exception.expectMessage("Incorrect password");

        when(encoder.encode(anyString())).thenReturn("test");
        when(repository.findByEmail("igorik@gmail.com")).thenReturn(Optional.of(ENTITY));

        userService.login("igorik@gmail.com", "test");

        verify(repository).findByEmail(anyString());
        verify(encoder).encode(anyString());
    }

    @Test
    public void loginShouldThrowUserNotFoundExceptionWithIncorrectEmail() {
        exception.expect(EntityNotFoundException.class);
        exception.expectMessage("There is no user with this e-mail");

        when(encoder.encode(anyString())).thenReturn("test");
        when(repository.findByEmail(anyString())).thenReturn(Optional.empty());

        userService.login("igorik@gmail.com", "test");

        verify(repository).findByEmail(anyString());
        verify(encoder).encode(anyString());
    }

    @Test
    public void findAllShouldReturnAllUsers() {
        List<User> expected = Collections.singletonList(USER);
        Page<UserEntity> entities = new PageImpl<>(Collections.singletonList(ENTITY));

        when(repository.findAll(any(PageRequest.class))).thenReturn(entities);
        when(mapper.mapUserEntityToUser(ENTITY)).thenReturn(USER);

        List<User> actual = userService.findAll(1 , 10);

        verify(repository).findAll(any(PageRequest.class));
        verify(mapper).mapUserEntityToUser(any(UserEntity.class));

        assertThat(actual, equalTo(expected));
    }

    @Test
    public void findAllShouldReturnEmptyListWhenThereIsNoUsers() {
        List<User> expected = Collections.emptyList();

        when(repository.findAll(any(PageRequest.class))).thenReturn(Page.empty());

        List<User> actual = userService.findAll(1 , 10);

        verify(repository).findAll(any(PageRequest.class));

        assertThat(actual, equalTo(expected));
    }

    @Test
    public void findAllShouldThrowInvalidPaginatingException() {
        exception.expect(InvalidPaginatingException.class);
        exception.expectMessage("Invalid number of current page or records per page");

        userService.findAll(-1 ,1);
    }

    @Test
    public void showNumbersOfRowsShouldReturnCount() {
        when(repository.count()).thenReturn(10L);

        long actual = userService.showNumberOfRows();
        verify(repository).count();

        assertThat(actual, equalTo(10L));
    }

    private static UserEntity getUserEntity() {
        return new UserEntity("Igor", "Volchkov", "igorik@gmail.com", "ENCODED", Role.DEVELOPER);
    }

    private static User getUser() {
        return User.builder()
                .name("Igor")
                .surname("Volchkov")
                .password("Babushka3529")
                .email("igorik@gmail.com")
                .role(Role.DEVELOPER)
                .build();
    }
}