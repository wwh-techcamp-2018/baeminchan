package codesquad.user;

import codesquad.user.dto.UserDto;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class UserDtoValidationTest {

    private static Validator validator;

    private static UserDto userDto;


    @Before
    public void setUp() throws Exception {
        userDto = UserDto.builder()
                .name("tester")
                .password("dhtp1@3$")
                .confirmPassword("dhtp1@3$")
                .email("test@test.com")
                .phone("010-1234-4545")
                .build();
    }

    @BeforeClass
    public static void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void valid_phone_number() {
        Set<ConstraintViolation<UserDto>> constraintViolations = validator.validate(userDto);
        assertThat(constraintViolations.size(), is(0));
    }

    @Test
    public void invalid_phone_number() {
        userDto.setPhone("abc-1234-4545");
        Set<ConstraintViolation<UserDto>> constraintViolations = validator.validate(userDto);
        assertThat(constraintViolations.size(), is(1));
    }

    @Test
    public void valid_email() {
        Set<ConstraintViolation<UserDto>> constraintViolations = validator.validate(userDto);
        assertThat(constraintViolations.size(), is(0));
    }

    @Test
    public void invalid_email() {
        userDto.setEmail("cjswotl6274@testcom");
        Set<ConstraintViolation<UserDto>> constraintViolations = validator.validate(userDto);
        assertThat(constraintViolations.size(), is(1));
    }

    @Test
    public void valid_password() {
        Set<ConstraintViolation<UserDto>> constraintViolations = validator.validate(userDto);
        assertThat(constraintViolations.size(), is(0));
    }

    @Test
    public void invalid_password() {
        userDto.setPassword("dhtp123");
        Set<ConstraintViolation<UserDto>> constraintViolations = validator.validate(userDto);
        assertThat(constraintViolations.size(), is(1));
    }

    @Test
    public void valid_name() {
        Set<ConstraintViolation<UserDto>> constraintViolations = validator.validate(userDto);
        assertThat(constraintViolations.size(), is(0));

    }

    @Test
    public void invalid_name() {
        userDto.setName("석윤짱1@");
        Set<ConstraintViolation<UserDto>> constraintViolations = validator.validate(userDto);
        assertThat(constraintViolations.size(), is(1));

    }
}
