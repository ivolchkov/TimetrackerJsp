package project.service.user;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import project.domain.user.User;
import project.entity.user.UserEntity;
import project.exception.AlreadyRegisteredException;
import project.exception.InvalidRegistrationException;
import project.exception.UserNotFoundException;
import project.repository.userDao.UserDao;
import project.service.encoder.PasswordEncoder;
import project.service.mapper.UserMapper;
import project.service.validator.UserValidator;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserEntityServiceImplTest {
    private static UserEntity entity;
    private static User user;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Mock
    private UserDao repository;
    @Mock
    private UserMapper mapper;
    @Mock
    private UserValidator validator;
    @Mock
    private PasswordEncoder encoder;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeClass
    public static void initUser() {
        entity= UserEntity.builder()
                .withId(1)
                .withName("Igor")
                .withSurname("Volchkov")
                .withPassword("ENCODED")
                .withEmail("igorik@gmail.com")
                .build();
        user= User.builder()
                .withName("Igor")
                .withSurname("Volchkov")
                .withPassword("Babushka3529")
                .withEmail("igorik@gmail.com")
                .build();
    }

    @After
    public void resetMock() {
        reset(repository);
        reset(validator);
        reset(encoder);
    }

    @Test
    public void shouldRegisterUser() {
        when(repository.save(any(UserEntity.class))).thenReturn(true);
        when(mapper.mapUserToUserEntity(any(User.class))).thenReturn(entity);
        when(encoder.encode(any(String.class))).thenReturn(Optional.of(entity.getPassword()));
        boolean actual = userService.register(user);

        assertTrue(actual);
    }

    @Test
    public void shouldThrowRuntimeExceptionWhenRegisterUser() {
        exception.expect(AlreadyRegisteredException.class);
        exception.expectMessage("User is already registered by this e-mail");

        when(repository.findByEmail(any(String.class))).thenReturn(Optional.ofNullable(entity));


        userService.register(user);
    }

    @Test
    public void shouldThrowInvalidRegistrationExceptionWhenRegisterNullUser() {
        exception.expect(InvalidRegistrationException.class);

        doThrow(InvalidRegistrationException.class).when(validator).validate(null);
        userService.register(null);
    }

    @Test
    public void shouldLoginUser() {
        when(repository.findByEmail("igorik@gmail.com")).thenReturn(Optional.of(entity));
        when(encoder.encode("Babushka3529")).thenReturn(Optional.of(entity.getPassword()));
        when(mapper.mapUserEntityToUser(any(UserEntity.class))).thenReturn(user);

        User actual = userService.login("igorik@gmail.com", "Babushka3529");

        assertEquals(user, actual);
    }

    @Test
    public void shouldThrowUserNotFoundExceptionWithIncorrectPassword() {
        exception.expect(UserNotFoundException.class);
        exception.expectMessage("Incorrect password");

        when(encoder.encode(any(String.class))).thenReturn(Optional.of("test"));
        when(repository.findByEmail("igorik@gmail.com")).thenReturn(Optional.ofNullable(entity));

        userService.login("igorik@gmail.com", "test");
    }

    @Test
    public void shouldThrowUserNotFoundExceptionWithIncorrectEmail() {
        exception.expect(UserNotFoundException.class);
        exception.expectMessage("There is no user with this e-mail");
        when(encoder.encode(any(String.class))).thenReturn(Optional.of("test"));
        when(repository.findByEmail(any(String.class))).thenReturn(Optional.empty());

        userService.login("igorik@gmail.com", "test");
    }

    @Test
    public void shouldReturnTeam() {
        List<User> expected = Collections.singletonList(user);
        List<UserEntity> entities = Collections.singletonList(entity);

        when(repository.findByBacklog(any(Integer.class))).thenReturn(entities);
        when(mapper.mapUserEntityToUser(entity)).thenReturn(user);
        List<User> actual = userService.findTeam(3);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnEmptyListWhenThereIsNoTeam() {
        List<User> expected = Collections.emptyList();

        when(repository.findByBacklog(any(Integer.class))).thenReturn(Collections.emptyList());
        List<User> actual = userService.findTeam(3);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnAllUsers() {
        List<User> expected = Collections.singletonList(user);
        List<UserEntity> entities = Collections.singletonList(entity);

        when(repository.findAll()).thenReturn(entities);
        when(mapper.mapUserEntityToUser(entity)).thenReturn(user);
        List<User> actual = userService.findAll();

        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnEmptyListWhenThereIsNoUsers() {
        List<User> expected = Collections.emptyList();

        when(repository.findAll()).thenReturn(Collections.emptyList());
        List<User> actual = userService.findAll();

        assertEquals(expected, actual);
    }
}