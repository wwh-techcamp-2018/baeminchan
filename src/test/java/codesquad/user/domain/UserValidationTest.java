package codesquad.user.domain;

import codesquad.user.dto.UserDto;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.*;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;


public class UserValidationTest {

    private UserDto user;

    private static Validator validator;
    @Before
    public void setUp() throws Exception {
        user = UserDto.builder()
                .email("a@b.com")
                .name("이혁진")
                .password("gurwls0808#$")
                .confirmPassword("gurwls0808#$")
                .phone("010-1234-5678")
                .build();
    }

    @BeforeClass
    public static void setup() throws Exception {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

    }

    @Test
    public void email_올바른형식() {
        Set<ConstraintViolation<UserDto>> constraintViolations = validator.validate(user);
        assertThat(constraintViolations.size()).isEqualTo(0);

    }

    @Test
    public void email_틀린형식() {
        user.setEmail("ab.com");
        Set<ConstraintViolation<UserDto>> constraintViolations = validator.validate(user);
        assertThat(constraintViolations.size()).isEqualTo(1);
    }

    @Test
    public void name_올바른형식() {
        Set<ConstraintViolation<UserDto>> constraintViolations = validator.validate(user);
        assertThat(constraintViolations.size()).isEqualTo(0);
    }

    @Test
    public void name_틀린형식_최대_길이_초과() {
        user.setName("김수완무거북이과오러아려ㅏ너아쟈포나기가");
        Set<ConstraintViolation<UserDto>> constraintViolations = validator.validate(user);
        assertThat(constraintViolations.size()).isEqualTo(1);
    }

    @Test
    public void name_틀린형식_최소_길이_미만() {
        user.setName("김");
        Set<ConstraintViolation<UserDto>> constraintViolations = validator.validate(user);
        assertThat(constraintViolations.size()).isEqualTo(1);
    }

    @Test
    public void name_틀린형식_숫자포함() {
        user.setName("김1");
        Set<ConstraintViolation<UserDto>> constraintViolations = validator.validate(user);
        assertThat(constraintViolations.size()).isEqualTo(1);
    }

    @Test
    public void name_틀린형식_특수문자포함() {
        user.setName("김$이혁진");
        Set<ConstraintViolation<UserDto>> constraintViolations = validator.validate(user);
        assertThat(constraintViolations.size()).isEqualTo(1);
    }

    @Test
    public void password_올바른형식() {
        Set<ConstraintViolation<UserDto>> constraintViolations = validator.validate(user);
        assertThat(constraintViolations.size()).isEqualTo(0);
    }

    @Test
    public void password_틀린형식_최대_길이_초과() {
        user.setPassword("wlgkfjdkvu!@#231412414213");
        Set<ConstraintViolation<UserDto>> constraintViolations = validator.validate(user);
        assertThat(constraintViolations.size()).isEqualTo(1);
    }

    @Test
    public void password_틀린형식_최소_길이_미만() {
        user.setPassword("w!3");
        Set<ConstraintViolation<UserDto>> constraintViolations = validator.validate(user);
        assertThat(constraintViolations.size()).isEqualTo(1);
    }

    @Test
    public void password_특수문자_없음() {
        user.setPassword("wfhjd3333");
        Set<ConstraintViolation<UserDto>> constraintViolations = validator.validate(user);
        assertThat(constraintViolations.size()).isEqualTo(1);
    }

    @Test
    public void password_숫자_없음() {
        user.setPassword("wfhjd!!!!");
        Set<ConstraintViolation<UserDto>> constraintViolations = validator.validate(user);
        assertThat(constraintViolations.size()).isEqualTo(1);
    }

    @Test
    public void phone_올바른형식() {
        Set<ConstraintViolation<UserDto>> constraintViolations = validator.validate(user);
        assertThat(constraintViolations.size()).isEqualTo(0);
    }

    @Test
    public void phone_틀린_기지국_번호() {
        user.setPhone("000-1234-5678");
        Set<ConstraintViolation<UserDto>> constraintViolations = validator.validate(user);
        assertThat(constraintViolations.size()).isEqualTo(1);
    }

    @Test
    public void phone_숫자가아님() {
        user.setPhone("000-1234-567a");
        Set<ConstraintViolation<UserDto>> constraintViolations = validator.validate(user);
        assertThat(constraintViolations.size()).isEqualTo(1);
    }
}
