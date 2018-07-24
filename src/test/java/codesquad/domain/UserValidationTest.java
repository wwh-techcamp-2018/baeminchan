package codesquad.domain;

import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class UserValidationTest {

    private static Validator validator;

    @BeforeClass
    public static void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void valid_phone_number() {
        User user = User.builder()
                .name("tester")
                .password("dhtp1@3$")
                .email("test@test.com")
                .phone("010-1234-4545")
                .build();

        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
        assertThat(constraintViolations.size(), is(0));
    }

    @Test
    public void invalid_phone_number() {
        User user = User.builder()
                .name("tester")
                .password("dhtp1@3$")
                .email("test@test.com")
                .phone("abc-1234-4545")
                .build();

        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
        assertThat(constraintViolations.size(), is(1));
    }

    @Test
    public void valid_email() {
        User user = User.builder()
                .name("tester")
                .password("dhtp1@3$")
                .email("cjswotl6274@test.com")
                .phone("010-1234-4545")
                .build();

        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
        assertThat(constraintViolations.size(), is(0));
    }

    @Test
    public void invalid_email() {
        User user = User.builder()
                .name("tester")
                .password("dhtp1@3$")
                .email("cjswotl6274@testcom")
                .phone("010-1234-4545")
                .build();

        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
        assertThat(constraintViolations.size(), is(1));
    }

    @Test
    public void valid_password() {
        User user = User.builder()
                .name("tester")
                .password("dhtp1@3$")
                .email("cjswotl6274@test.com")
                .phone("010-1234-4545")
                .build();

        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
        assertThat(constraintViolations.size(), is(0));
    }

    @Test
    public void invalid_password() {
        User user = User.builder()
                .name("tester")
                .password("dhtp123")
                .email("cjswotl6274@test.com")
                .phone("010-1234-4545")
                .build();

        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
        assertThat(constraintViolations.size(), is(1));
    }

    @Test
    public void valid_name() {
        User user = User.builder()
                .name("석윤짱")
                .password("dhtp1@3$")
                .email("cjswotl6274@test.com")
                .phone("010-1234-4545")
                .build();

        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
        assertThat(constraintViolations.size(), is(0));

    }

    @Test
    public void invalid_name() {
        User user = User.builder()
                .name("석윤짱1@")
                .password("dhtp1@3$")
                .email("cjswotl6274@test.com")
                .phone("010-1234-4545")
                .build();

        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
        assertThat(constraintViolations.size(), is(1));

    }
}
