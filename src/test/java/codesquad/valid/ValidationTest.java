package codesquad.valid;

import codesquad.domain.UserTest;
import codesquad.dto.user.JoinUserDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class ValidationTest {
    private static Validator validator;
    private JoinUserDto joinUserDto;

    @BeforeClass
    public static void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Before
    public void setUp() throws Exception {
        joinUserDto = UserTest.createDefaultUser();
    }

    @Test
    public void userValidationTest_성공() {
        Set<ConstraintViolation<JoinUserDto>> constraintViolcations = validator.validate(joinUserDto);
        assertThat(constraintViolcations.size()).isEqualTo(0);
    }

    @Test
    public void userValidationTest_실패_email_id() {
        joinUserDto.setEmail("@naver.com");
        Set<ConstraintViolation<JoinUserDto>> constraintViolcations = validator.validate(joinUserDto);
        assertThat(constraintViolcations.size()).isEqualTo(1);
    }

    @Test
    public void userValidationTest_실패_email_domain() {
        joinUserDto.setEmail("inter@");
        Set<ConstraintViolation<JoinUserDto>> constraintViolcations = validator.validate(joinUserDto);
        assertThat(constraintViolcations.size()).isEqualTo(1);
    }

    @Test
    public void userValidationTest_실패_password_8미만() {
        joinUserDto.setPassword("asd1!");
        Set<ConstraintViolation<JoinUserDto>> constraintViolcations = validator.validate(joinUserDto);
        assertThat(constraintViolcations.size()).isEqualTo(2);
    }

    @Test
    public void userValidationTest_실패_password_12초과() {
        joinUserDto.setPassword("asd1!asdf11!!");
        Set<ConstraintViolation<JoinUserDto>> constraintViolcations = validator.validate(joinUserDto);
        assertThat(constraintViolcations.size()).isEqualTo(2);
    }

    @Test
    public void userValidationTest_실패_password_숫자없이() {
        joinUserDto.setPassword("asdf!@#$");
        Set<ConstraintViolation<JoinUserDto>> constraintViolcations = validator.validate(joinUserDto);
        assertThat(constraintViolcations.size()).isEqualTo(2);
    }

    @Test
    public void userValidationTest_실패_password_영문없이() {
        joinUserDto.setPassword("1234!@#$");
        Set<ConstraintViolation<JoinUserDto>> constraintViolcations = validator.validate(joinUserDto);
        assertThat(constraintViolcations.size()).isEqualTo(2);
    }

    @Test
    public void userValidationTest_실패_password_특문없이() {
        joinUserDto.setPassword("1234asda");
        Set<ConstraintViolation<JoinUserDto>> constraintViolcations = validator.validate(joinUserDto);
        assertThat(constraintViolcations.size()).isEqualTo(2);
    }

    @Test
    public void userValidationTest_실패_name_2자미만() {
        joinUserDto.setName("1");
        Set<ConstraintViolation<JoinUserDto>> constraintViolcations = validator.validate(joinUserDto);
        assertThat(constraintViolcations.size()).isEqualTo(1);
    }

    @Test
    public void userValidationTest_실패_phoneNo_10미만() {
        joinUserDto.setPhoneNo("010123456");
        Set<ConstraintViolation<JoinUserDto>> constraintViolcations = validator.validate(joinUserDto);
        assertThat(constraintViolcations.size()).isEqualTo(1);
    }

    @Test
    public void userValidationTest_실패_phoneNo_12초과() {
        joinUserDto.setPhoneNo("0101234567891");
        Set<ConstraintViolation<JoinUserDto>> constraintViolcations = validator.validate(joinUserDto);
        assertThat(constraintViolcations.size()).isEqualTo(1);
    }


    @Test
    public void userValidationTest_실패_phoneNo_패턴오류() {
        joinUserDto.setPhoneNo("01212345678");
        Set<ConstraintViolation<JoinUserDto>> constraintViolcations = validator.validate(joinUserDto);
        assertThat(constraintViolcations.size()).isEqualTo(1);
    }
}
