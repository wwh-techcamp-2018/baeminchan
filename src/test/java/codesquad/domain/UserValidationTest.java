package codesquad.domain;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;


public class UserValidationTest {
    private static final Logger log = LoggerFactory.getLogger(UserValidationTest.class);

    private static Validator validator;

    private User user;

    @BeforeClass
    public static void setupClass() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Before
    public void setup() throws Exception {
        user = new User("aa@aa.aa", "password", "aabbcc", "00-00-00", UserPermissions.NORMAL);
    }

    @Test
    public void emailPatternNotMatch() throws Exception {
        user = new User("aaaa", "password", "aabbcc", "00-00-00", UserPermissions.NORMAL);
        assertThat(logConstraints(validator.validate(user)).size()).isEqualTo(1);
    }

    @Test
    public void emailPatternMatch() throws Exception {
        user = new User("aa@aaaa", "password", "aabbcc", "00-00-00", UserPermissions.NORMAL);
        assertThat(logConstraints(validator.validate(user)).size()).isEqualTo(0);
    }

    @Test
    public void phoneNumberPatternNotMatch() throws Exception {
        user = new User("aa@aaaa", "password", "aabbcc", "00-0000", UserPermissions.NORMAL);
        assertThat(logConstraints(validator.validate(user)).size()).isEqualTo(1);
    }

    @Test
    public void nameSizeNotMatch() throws Exception {
        user = new User("aa@aaaa", "password", "", "00-00-00", UserPermissions.NORMAL);
        assertThat(logConstraints(validator.validate(user)).size()).isEqualTo(1);
    }

    private Set<ConstraintViolation<User>> logConstraints (Set<ConstraintViolation<User>> constraintViolations) {
        for (ConstraintViolation<User> constraintViolation : constraintViolations) {
            log.debug("validation error message : {}", constraintViolation.getMessage());
            log.debug("validation error invalidValue : {}", constraintViolation.getInvalidValue());

        }
        return constraintViolations;
    }

}