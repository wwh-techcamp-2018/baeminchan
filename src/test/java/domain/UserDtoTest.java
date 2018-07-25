package domain;

import codesquad.dto.UserDto;
import controller.ApiUserControllerTest;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class UserDtoTest {

    private static final Logger log = LoggerFactory.getLogger(ApiUserControllerTest.class);
    private static Validator validator;

    @Before
    public void setUp() throws Exception {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void UserDto_정상() throws Exception {
        UserDto newUser = new UserDto("javajigi", "gmail.com", "testt@est134", "testt@est134", "재성", "010-1111-2222");
        Set<ConstraintViolation<UserDto>> constraintViolations = validator.validate(newUser);
        assertThat(constraintViolations.isEmpty()).isTrue();
    }

    @Test
    public void UserDto_아이디부적절() throws Exception {
        UserDto newUser = new UserDto("javajigi@", "gmail.com", "testt@est134", "testt@est134", "재성", "010-1111-2222");
        Set<ConstraintViolation<UserDto>> constraintViolations = validator.validate(newUser);
        for (ConstraintViolation<UserDto> constraintViolation : constraintViolations) {
            log.info("test error msg : {}", constraintViolation.getMessage());
        }
        assertThat(constraintViolations.size()).isEqualTo(1);
    }

    @Test
    public void UserDto_이메일_도메인_부적절() throws Exception {
        UserDto newUser = new UserDto("javajigi", "asdfadsf", "testt@est134", "testt@est134", "재성", "010-1111-2222");
        Set<ConstraintViolation<UserDto>> constraintViolations = validator.validate(newUser);
        for (ConstraintViolation<UserDto> constraintViolation : constraintViolations) {
            log.info("test error msg : {}", constraintViolation.getMessage());
        }
        assertThat(constraintViolations.size()).isEqualTo(2);
    }

    @Test
    public void UserDto_비밀번호_불일치() throws Exception {
        UserDto newUser = new UserDto("javajigi", "gmail.com", "testt@est134", "tes@ttest3332", "재성", "010-1111-2222");
        Set<ConstraintViolation<UserDto>> constraintViolations = validator.validate(newUser);
        for (ConstraintViolation<UserDto> constraintViolation : constraintViolations) {
            log.info("test error msg : {}", constraintViolation.getMessage());
        }
        assertThat(constraintViolations.size()).isEqualTo(1);
    }

    @Test
    public void UserDto_이름_부적절() throws Exception {
        UserDto newUser = new UserDto("javajigi", "gmail.com", "testt@ttest3332", "testt@ttest3332", "aaaa", "010-1111-2222");
        Set<ConstraintViolation<UserDto>> constraintViolations = validator.validate(newUser);
        for (ConstraintViolation<UserDto> constraintViolation : constraintViolations) {
            log.info("test error msg : {}", constraintViolation.getMessage());
        }
        assertThat(constraintViolations.size()).isEqualTo(1);
    }

    @Test
    public void UserDto_핸드폰번호_부적절1() throws Exception {
        UserDto newUser = new UserDto("javajigi", "gmail.com", "tes@ttest3332", "tes@ttest3332", "재성", "999-1111-2222");
        Set<ConstraintViolation<UserDto>> constraintViolations = validator.validate(newUser);
        for (ConstraintViolation<UserDto> constraintViolation : constraintViolations) {
            log.info("test error msg : {}", constraintViolation.getMessage());
        }
        assertThat(constraintViolations.size()).isEqualTo(1);
    }

    @Test
    public void UserDto_핸드폰번호_부적절2() throws Exception {
        UserDto newUser = new UserDto("javajigi", "gmail.com", "tes@ttest3332", "tes@ttest3332", "재성", "012-1111-2222");
        Set<ConstraintViolation<UserDto>> constraintViolations = validator.validate(newUser);
        for (ConstraintViolation<UserDto> constraintViolation : constraintViolations) {
            log.info("test error msg : {}", constraintViolation.getMessage());
        }
        assertThat(constraintViolations.size()).isEqualTo(1);
    }
}
