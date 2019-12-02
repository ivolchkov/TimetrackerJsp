package com.ua.timetracking.service.impl;

import org.apache.log4j.Logger;
import com.ua.timetracking.domain.User;
import com.ua.timetracking.entity.UserEntity;
import com.ua.timetracking.exception.*;
import com.ua.timetracking.repository.UserDao;
import com.ua.timetracking.service.UserService;
import com.ua.timetracking.service.encoder.PasswordEncoder;
import com.ua.timetracking.service.mapper.UserMapper;
import com.ua.timetracking.service.validator.Validator;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);

    private final UserDao userDao;
    private final PasswordEncoder encoder;
    private final UserMapper mapper;
    private final Validator<User> validator;

    public UserServiceImpl(UserDao userDao, PasswordEncoder encoder, UserMapper mapper, Validator<User> validator) {
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
            throw new EntityNotFoundException("There is no user with this e-mail");
        }
        if (entity.get().getPassword().equals(encodedPassword)) {
            return mapper.mapUserEntityToUser(entity.get());
        }

        LOGGER.warn("Incorrect password");
        throw new EntityNotFoundException("Incorrect password");

    }

    @Override
    public List<User> findAll(Integer currentPage, Integer recordsPerPage) {
        paginatingValidation(currentPage, recordsPerPage);

        Integer offset = currentPage * recordsPerPage - recordsPerPage;
        List<UserEntity> result = userDao.findAll(offset, recordsPerPage);

        return listMapping(result);
    }

    @Override
    public Integer showNumberOfRows() {
        return userDao.findAmountOfRows();
    }

    private List<User> listMapping(List<UserEntity> result) {
        return result.isEmpty() ? Collections.emptyList() :
                result.stream()
                        .map(mapper::mapUserEntityToUser)
                        .collect(Collectors.toList());
    }

    private void paginatingValidation(Integer currentPage, Integer recordsPerPage) {
        if (currentPage <= 0 || recordsPerPage <= 0) {
            LOGGER.error("Invalid number of current page or records per page");
            throw new InvalidPaginatingException("Invalid number of current page or records per page");
        }
    }
}
