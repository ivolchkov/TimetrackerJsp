package com.ua.timetracking.service.validator;

import org.apache.log4j.Logger;
import com.ua.timetracking.domain.User;
import com.ua.timetracking.exception.InvalidRegistrationException;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator implements Validator<User> {
    private static final Logger LOGGER = Logger.getLogger(UserValidator.class);

    private static final String PASSWORD_REGEX = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,}";
    private static final String EMAIL_REGEX = "(\\w{2,})@(\\w+\\.)([a-z]{2,5})";
    private static final String NAME_REGEX = "([A-Z])([a-z]{1,12})|([А-Я]([a-я]{1,12}))";
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    private static final Pattern NAME_PATTERN = Pattern.compile(NAME_REGEX);

    public void validate(User user) {
        if (user == null) {
            LOGGER.warn("User is not valid");
            throw new InvalidRegistrationException("User is not valid");
        }

        validateByParam(user.getName(), NAME_PATTERN, "Incorrect name");
        validateByParam(user.getSurname(), NAME_PATTERN,"Incorrect surname");
        validateByParam(user.getEmail(), EMAIL_PATTERN,"Incorrect e-mail");
        validateByParam(user.getPassword(), PASSWORD_PATTERN,"Incorrect password");
    }

    private void validateByParam(String param, Pattern pattern, String errorMessage) {
        Matcher matcher = pattern.matcher(param);

        if (!matcher.find()) {
            LOGGER.warn(errorMessage);
            throw new InvalidRegistrationException(errorMessage);
        }
    }

}
