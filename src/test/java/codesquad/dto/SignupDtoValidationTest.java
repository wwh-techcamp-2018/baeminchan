package codesquad.dto;

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

public class SignupDtoValidationTest {
    private static final Logger log = LoggerFactory.getLogger(SignupDtoValidationTest.class);

    private static Validator validator;

    private SignupDto.SignupDtoBuilder builder;

    @BeforeClass
    public static void setupClass() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Before
    public void setup() {
        builder = new SignupDto.SignupDtoBuilder()
                .withEmail("aa@aa.aa")
                .withPassword("password")
                .withPasswordConfirm("password")
                .withName("aabbcc")
                .withPhoneNumber("010-0101-0011");
    }

    @Test
    public void emailPatternNotMatch() throws Exception {
        assertThat(logConstraints(validator.validate(builder.withEmail("aaaa").build())).size()).isEqualTo(1);
    }

    @Test
    public void emailPatternMatch() throws Exception {
        assertThat(logConstraints(validator.validate(builder.withEmail("aa@aa.com").build())).size()).isEqualTo(0);
    }

    @Test
    public void phoneNumberPatternNotMatch() throws Exception {
        assertThat(logConstraints(validator.validate(builder.withPhoneNumber("001a-0aa0a").build())).size()).isEqualTo(1);
    }

    @Test
    public void nameSizeNotMatch() throws Exception {
        assertThat(logConstraints(validator.validate(builder.withName("").build())).size()).isEqualTo(1);
    }



    private Set<ConstraintViolation<SignupDto>> logConstraints (Set<ConstraintViolation<SignupDto>> constraintViolations) {
        for (ConstraintViolation<SignupDto> constraintViolation : constraintViolations) {
            log.debug("validation error message : {}", constraintViolation.getMessage());
            log.debug("validation error invalidValue : {}", constraintViolation.getInvalidValue());

        }
        return constraintViolations;
    }



}