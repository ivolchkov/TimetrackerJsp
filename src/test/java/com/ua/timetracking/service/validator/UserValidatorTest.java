package com.ua.timetracking.service.validator;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import com.ua.timetracking.domain.User;
import com.ua.timetracking.exception.InvalidRegistrationException;

public class UserValidatorTest {
    private static final UserValidator VALIDATOR = new UserValidator();;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void validateShouldThrowInvalidRegistrationExceptionValidatingNullUser() {
        exception.expect(InvalidRegistrationException.class);
        exception.expectMessage("User is not valid");

        VALIDATOR.validate(null);
    }

    @Test
    public void validateShouldThrowInvalidRegistrationExceptionValidatingInvalidName() {
        User student = User.builder().withName("игорь").build();
        exception.expect(InvalidRegistrationException.class);
        exception.expectMessage("Incorrect name");

        VALIDATOR.validate(student);
    }

    @Test
    public void validateShouldThrowInvalidRegistrationExceptionValidatingInvalidSurname() {
        User student = User.builder().withName("Ihor").
                withSurname("volchkov").
                build();
        exception.expect(InvalidRegistrationException.class);
        exception.expectMessage("Incorrect surname");

        VALIDATOR.validate(student);
    }

    @Test
    public void validateShouldThrowInvalidRegistrationExceptionValidatingInvalidEmail() {
        User student = User.builder().withName("Ihor").
                withSurname("Volchkov").
                withEmail("test.oshibka.com").
                build();
        exception.expect(InvalidRegistrationException.class);
        exception.expectMessage("Incorrect e-mail");

        VALIDATOR.validate(student);
    }

    @Test
    public void validateShouldThrowInvalidRegistrationExceptionValidatingInvalidPassword() {
        User student = User.builder().withName("Ihor").
                withSurname("Volchkov").
                withEmail("test@gmail.com").
                withPassword("pGlohoyparol").
                build();
        exception.expect(InvalidRegistrationException.class);
        exception.expectMessage("Incorrect password");

        VALIDATOR.validate(student);
    }
}