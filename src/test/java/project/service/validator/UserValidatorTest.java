package project.service.validator;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import project.domain.User;
import project.exception.InvalidRegistrationException;

public class UserValidatorTest {
    private static UserValidator validator;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @BeforeClass
    public static void initUser() {
        validator = new UserValidator();
    }

    @Test
    public void validateShouldThrowInvalidRegistrationExceptionValidatingNullUser() {
        exception.expect(InvalidRegistrationException.class);
        exception.expectMessage("User is not valid");

        validator.validate(null);
    }

    @Test
    public void validateShouldThrowInvalidRegistrationExceptionValidatingInvalidName() {
        User student = User.builder().withName("игорь").build();
        exception.expect(InvalidRegistrationException.class);
        exception.expectMessage("Incorrect name");

        validator.validate(student);
    }

    @Test
    public void validateShouldThrowInvalidRegistrationExceptionValidatingInvalidSurname() {
        User student = User.builder().withName("Ihor").
                withSurname("volchkov").
                build();
        exception.expect(InvalidRegistrationException.class);
        exception.expectMessage("Incorrect surname");

        validator.validate(student);
    }

    @Test
    public void validateShouldThrowInvalidRegistrationExceptionValidatingInvalidEmail() {
        User student = User.builder().withName("Ihor").
                withSurname("Volchkov").
                withEmail("test.oshibka.com").
                build();
        exception.expect(InvalidRegistrationException.class);
        exception.expectMessage("Incorrect e-mail");

        validator.validate(student);
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

        validator.validate(student);
    }
}