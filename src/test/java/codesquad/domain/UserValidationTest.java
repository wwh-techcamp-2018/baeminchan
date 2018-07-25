package codesquad.domain;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class UserValidationTest {
    private static final Logger log = LoggerFactory.getLogger(UserValidationTest.class);
    private static Validator validator;
    private UserDTO user;
    private Set annotationSet;

    @Before
    public void setUp() throws Exception {
        annotationSet = new HashSet();
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
        user = new UserDTO("javajigi@tech.com", "12345678", "12345678", "javajigi", "010-1234-5678");
    }

    @Test
    public void passes() throws Exception {
        validator.validate(user);
    }


    private void logValidationInfo(UserDTO user, Set annotationSet) {
        Set<ConstraintViolation<UserDTO>> constraintViolations = validator.validate(user);
        for (ConstraintViolation<UserDTO> constraintViolation : constraintViolations) {
            assertThat(annotationSet.contains(constraintViolation.getConstraintDescriptor().getAnnotation().annotationType()), is(true));
            log.debug("validation error message : {}", constraintViolation.getMessage());
            log.debug("validation error invalidValue : {}", constraintViolation.getInvalidValue());
        }
    }

    @Test
    public void emailNotBlank() {
        user.setEmail("");
        annotationSet.add(NotBlank.class);
        annotationSet.add(Pattern.class);
        logValidationInfo(user, annotationSet);
    }

    @Test
    public void emailInvalidValue() {
        user.setEmail("abcabc.com");
        annotationSet.add(Pattern.class);
        logValidationInfo(user, annotationSet);
    }

    @Test
    public void nameNotBlank() {
        user.setName("");
        annotationSet.add(Size.class);
        annotationSet.add(NotBlank.class);
        logValidationInfo(user, annotationSet);

    }

    @Test
    public void phoneNumberNotBlank() {
        user.setPhoneNumber("");
        annotationSet.add(Pattern.class);
        annotationSet.add(Size.class);
        annotationSet.add(NotBlank.class);
        logValidationInfo(user, annotationSet);
    }

}