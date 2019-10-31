package project.service.user;

import org.apache.log4j.Logger;
import project.domain.backlog.Backlog;
import project.domain.goal.Goal;
import project.domain.user.User;
import project.entity.user.UserEntity;
import project.exception.AlreadyRegisteredException;
import project.exception.InvalidEncodingException;
import project.exception.InvalidEntityUpdating;
import project.exception.UserNotFoundException;
import project.repository.userDao.UserDao;
import project.service.encoder.PasswordEncoder;
import project.service.mapper.UserMapper;
import project.service.validator.UserValidator;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);

    private final UserDao userDao;
    private final PasswordEncoder encoder;
    private final UserMapper mapper;
    private final UserValidator validator;

    public UserServiceImpl(UserDao userDao, PasswordEncoder encoder, UserMapper mapper, UserValidator validator) {
        this.userDao = userDao;
        this.encoder = encoder;
        this.mapper = mapper;
        this.validator = validator;
    }

    @Override
    public boolean register(User user) {
        validator.validate(user);

        if (userDao.findByEmail(user.getEmail()).isPresent()) {
            LOGGER.warn("User is already registered by this e-mail");
            throw new AlreadyRegisteredException("User is already registered by this e-mail");
        }

        String encoded = encoder.encode(user.getPassword()).
                orElseThrow(() -> new InvalidEncodingException("Encode process exception"));
        User userWithEncodedPass = new User(user, encoded);

        return userDao.save(mapper.mapUserToUserEntity(userWithEncodedPass));
    }

    @Override
    public User login(String email, String password) {
        String encodedPassword = encoder.encode(password).
                orElseThrow(() -> new InvalidEncodingException("Encode process exception"));
        Optional<UserEntity> entity = userDao.findByEmail(email);

        if (!entity.isPresent()) {
            LOGGER.warn("There is no user with this e-mail");
            throw new UserNotFoundException("There is no user with this e-mail");
        } else {
            if (entity.get().getPassword().equals(encodedPassword)) {
                return mapper.mapUserEntityToUser(entity.get());
            } else {
                LOGGER.warn("Incorrect password");
                throw new UserNotFoundException("Incorrect password");
            }
        }
    }

    @Override
    public List<User> findTeam(Integer backlogId) {
        List<UserEntity> result = userDao.findByBacklog(backlogId);

        return result.isEmpty() ? Collections.emptyList()
                : result.stream()
                .map(mapper::mapUserEntityToUser)
                .collect(Collectors.toList());
    }

    @Override
    public List<User> findAll() {
        List<UserEntity> result = userDao.findAll();

        return result.isEmpty() ? Collections.emptyList()
                : result.stream()
                .map(mapper::mapUserEntityToUser)
                .collect(Collectors.toList());
    }

    @Override
    public void addProjectToUser(User user, Backlog backlog) {
        if (Objects.isNull(user) || Objects.isNull(backlog) ) {
            LOGGER.warn("Invalid user updating");
            throw new InvalidEntityUpdating("Invalid user updating");
        }

        userDao.updateBacklogId(mapper.mapUserToUserEntity(user, backlog));
    }
}
