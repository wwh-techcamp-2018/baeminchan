package codesquad.user.dto;

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
        builder = UserSignupDtoTest.validDtoBuilder();
    }

    @Test
    public void validDto() {
        assertConstraintViolations(builder.build(), 0);
    }

    @Test
    public void password_only_alphabet() {
        assertConstraintViolations(createDtoPasswordPairBy("password"), 2);
    }

    @Test
    public void password_only_integer() {
        assertConstraintViolations(createDtoPasswordPairBy("12345678"), 2);
    }

    @Test
    public void password_under_min_size() {
        assertConstraintViolations(createDtoPasswordPairBy("pass123"), 2);
    }

    @Test
    public void password_over_max_size() {
        assertConstraintViolations(createDtoPasswordPairBy("password123456789"), 2);
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
                .name("스무글자가넘는이름을테스트하고있습니다람쥐")
                .build();
        assertConstraintViolations(dto, 1);
    }

    private UserSignupDto createDtoPasswordPairBy(String password) {
        return builder
                .password(password)
                .passwordCheck(password)
                .build();
    }

    private void assertConstraintViolations(UserSignupDto dto, int size) {
        Set<ConstraintViolation<UserSignupDto>> constraintViolations = validator.validate(dto);
        assertThat(constraintViolations.size(), is(size));
    }

}
