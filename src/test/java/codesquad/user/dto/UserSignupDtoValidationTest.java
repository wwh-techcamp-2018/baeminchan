package codesquad.user.dto;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.Validator;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class UserSignupDtoValidationTest {
    private static final Logger log = LoggerFactory.getLogger(UserSignupDtoValidationTest.class);
    private static Validator validator;
    private UserSignupDto.UserSignupDtoBuilder builder;

    @BeforeClass
    public static void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Before
    public void setUp() throws Exception {
        builder = UserSignupDto.builder()
                .name("javajigi")
                .password("123456qwerA")
                .passwordCheck("123456qwerA")
                .phoneNumber("010-1234-5678")
                .email("tester@gmail.com");

    }

    @Test
    public void validDto() {
        UserSignupDto dto = builder.build();

        assertConstraintViolations(dto, 0);
    }

    @Test
    public void password_only_alphabet() {
        UserSignupDto dto = builder
                .password("password")
                .passwordCheck("password")
                .build();

        assertConstraintViolations(dto, 2);
    }

    @Test
    public void password_only_integer() {
        UserSignupDto dto = builder
                .password("12345678")
                .passwordCheck("12345678")
                .build();

        assertConstraintViolations(dto, 2);
    }

    @Test
    public void password_under_min_size() {
        UserSignupDto dto = builder
                .password("pass123")
                .passwordCheck("pass123")
                .build();

        assertConstraintViolations(dto, 2);
    }

    @Test
    public void password_over_max_size() {
        UserSignupDto dto = builder
                .password("password123456789")
                .passwordCheck("password123456789")
                .build();

        assertConstraintViolations(dto, 2);
    }

    @Test
    public void email_invalid() {
        UserSignupDto dto = builder
                .email("tester@gmail")
                .build();

        assertConstraintViolations(dto, 1);
    }

    @Test
    public void phoneNumber_invalid() {
        UserSignupDto dto = builder
                .phoneNumber("010-12345678")
                .build();
        assertConstraintViolations(dto, 1);
    }

    @Test
    public void name_invalid() {
        UserSignupDto dto = builder
                .name("안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요")
                .build();
        assertConstraintViolations(dto, 1);
    }

    private void assertConstraintViolations(UserSignupDto dto, int size) {
        Set<ConstraintViolation<UserSignupDto>> constraintViolations = validator.validate(dto);
        for (ConstraintViolation<UserSignupDto> constraintViolation : constraintViolations) {
            log.debug("violation error message : {}", constraintViolation.getMessage());
        }

        assertThat(constraintViolations.size(), is(size));
    }

}
