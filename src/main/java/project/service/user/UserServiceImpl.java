package project.service.user;

import org.apache.log4j.Logger;
import project.domain.story.Story;
import project.domain.user.User;
import project.exception.AlreadyRegisteredException;
import project.exception.InvalidEncodingException;
import project.exception.UserNotFoundException;
import project.repository.storyDao.StoryDao;
import project.repository.userDao.UserDao;
import project.service.encoder.PasswordEncoder;
import project.service.validator.UserValidator;

import java.util.Objects;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);

    private final UserDao userRepository;
    private final StoryDao storyRepository;
    private final PasswordEncoder encoder;
    private final UserValidator validator;

    public UserServiceImpl(UserDao userRepository, StoryDao storyRepository, PasswordEncoder encoder, UserValidator validator) {
        this.userRepository = userRepository;
        this.storyRepository = storyRepository;
        this.encoder = encoder;
        this.validator = validator;
    }

    @Override
    public boolean register(User user) {
        validator.validate(user);

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            LOGGER.warn("User is already registered by this e-mail");
            throw new AlreadyRegisteredException("User is already registered by this e-mail");
        }

        String encoded = encoder.encode(user.getPassword()).
                orElseThrow(() -> new InvalidEncodingException("Encode process exception"));
        User userWithEncodedPass = new User(user, encoded);

        if (Objects.nonNull(user.getStories())) {
            for (Story story : user.getStories()) {
                if (!storyRepository.save(story)) {
                    LOGGER.warn("Invalid story adding!");
                    return false;
                }
            }
        }

        return userRepository.save(userWithEncodedPass);
    }

    @Override
    public boolean login(String email, String password) {
        String encodedPassword = encoder.encode(password).
                orElseThrow(() -> new InvalidEncodingException("Encode process exception"));
        Optional<User> user = userRepository.findByEmail(email);

        if (!user.isPresent()) {
            LOGGER.warn("There is no user with this e-mail");
            throw new UserNotFoundException("There is no user with this e-mail");
        } else {
            if (user.get().getPassword().equals(encodedPassword)) {
                return true;
            } else {
                LOGGER.warn("Incorrect password");
                throw new UserNotFoundException("Incorrect password");
            }
        }
    }
}
