package project.service.user;

import org.junit.After;
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
import project.exception.InvalidPaginatingException;
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
    private static final UserEntity ENTITY = UserEntity.builder()
            .withId(1)
            .withName("Igor")
            .withSurname("Volchkov")
            .withPassword("ENCODED")
            .withEmail("igorik@gmail.com")
            .build();
    ;
    private static final User USER = User.builder()
            .withName("Igor")
            .withSurname("Volchkov")
            .withPassword("Babushka3529")
            .withEmail("igorik@gmail.com")
            .build();

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

    @After
    public void resetMock() {
        reset(repository, validator, encoder);
    }

    @Test
    public void shouldRegisterUser() {
        when(repository.save(any(UserEntity.class))).thenReturn(true);
        when(mapper.mapUserToUserEntity(any(User.class))).thenReturn(ENTITY);
        when(encoder.encode(any(String.class))).thenReturn(Optional.of(ENTITY.getPassword()));
        boolean actual = userService.register(USER);

        assertTrue(actual);
    }

    @Test
    public void shouldThrowRuntimeExceptionWhenRegisterUser() {
        exception.expect(AlreadyRegisteredException.class);
        exception.expectMessage("User is already registered by this e-mail");

        when(repository.findByEmail(any(String.class))).thenReturn(Optional.ofNullable(ENTITY));


        userService.register(USER);
    }

    @Test
    public void shouldThrowInvalidRegistrationExceptionWhenRegisterNullUser() {
        exception.expect(InvalidRegistrationException.class);

        doThrow(InvalidRegistrationException.class).when(validator).validate(null);
        userService.register(null);
    }

    @Test
    public void shouldLoginUser() {
        when(repository.findByEmail("igorik@gmail.com")).thenReturn(Optional.of(ENTITY));
        when(encoder.encode("Babushka3529")).thenReturn(Optional.of(ENTITY.getPassword()));
        when(mapper.mapUserEntityToUser(any(UserEntity.class))).thenReturn(USER);

        User actual = userService.login("igorik@gmail.com", "Babushka3529");

        assertEquals(USER, actual);
    }

    @Test
    public void shouldThrowUserNotFoundExceptionWithIncorrectPassword() {
        exception.expect(UserNotFoundException.class);
        exception.expectMessage("Incorrect password");

        when(encoder.encode(any(String.class))).thenReturn(Optional.of("test"));
        when(repository.findByEmail("igorik@gmail.com")).thenReturn(Optional.ofNullable(ENTITY));

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
        List<User> expected = Collections.singletonList(USER);
        List<UserEntity> entities = Collections.singletonList(ENTITY);

        when(repository.findByBacklog(any(Integer.class))).thenReturn(entities);
        when(mapper.mapUserEntityToUser(ENTITY)).thenReturn(USER);
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
        List<User> expected = Collections.singletonList(USER);
        List<UserEntity> entities = Collections.singletonList(ENTITY);

        when(repository.findAll(any(Integer.class) , any(Integer.class))).thenReturn(entities);
        when(mapper.mapUserEntityToUser(ENTITY)).thenReturn(USER);
        List<User> actual = userService.findAll(1 , 10);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnEmptyListWhenThereIsNoUsers() {
        List<User> expected = Collections.emptyList();

        when(repository.findAll(any(Integer.class) , any(Integer.class))).thenReturn(Collections.emptyList());
        List<User> actual = userService.findAll(1 , 10);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldThrowInvalidPaginatingException() {
        exception.expect(InvalidPaginatingException.class);
        exception.expectMessage("Invalid number of current page or records per page");

        userService.findAll(0 ,1);
    }
}