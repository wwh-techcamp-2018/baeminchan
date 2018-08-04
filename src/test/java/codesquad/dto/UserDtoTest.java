package codesquad.dto;

import codesquad.controller.ApiUserControllerTest;
import codesquad.dto.UserDto;
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
    private UserDto defaultUser;

    @Before
    public void setUp() throws Exception {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        defaultUser = new UserDto()
                .setUserId("javajigi@gmail.com")
                .setPassword("testt@est134")
                .setPassword2("testt@est134")
                .setName("재성")
                .setPhoneNumber("010-1111-2222");
    }

    @Test
    public void 정상() throws Exception {
        Set<ConstraintViolation<UserDto>> constraintViolations = validator.validate(defaultUser);
        assertThat(constraintViolations.isEmpty()).isTrue();
    }

    @Test
    public void 아이디부적절() throws Exception {
        // 코드 라인을 줄일수 있지만 가독성을 위해 newUser를 선언하여 실행하였습니다.
        UserDto newUser = defaultUser
                .setUserId("javajigi@@gmail.com");
        Set<ConstraintViolation<UserDto>> constraintViolations = validator.validate(newUser);
        for (ConstraintViolation<UserDto> constraintViolation : constraintViolations) {
            log.debug("Test error msg : {}", constraintViolation.getMessage());
        }
        assertThat(constraintViolations.size()).isEqualTo(1);
    }

    @Test
    public void 이메일_도메인_부적절() throws Exception {
        UserDto newUser = defaultUser
                .setUserId("javajigi@asdfasdf");
        Set<ConstraintViolation<UserDto>> constraintViolations = validator.validate(newUser);
        for (ConstraintViolation<UserDto> constraintViolation : constraintViolations) {
            log.debug("Test error msg : {}", constraintViolation.getMessage());
        }
        assertThat(constraintViolations.size()).isEqualTo(1);
    }

    @Test
    public void 비밀번호_불일치() throws Exception {
        UserDto newUser = defaultUser
                .setPassword2("tes@ttest3332");
        Set<ConstraintViolation<UserDto>> constraintViolations = validator.validate(newUser);
        for (ConstraintViolation<UserDto> constraintViolation : constraintViolations) {
            log.debug("Test error msg : {}", constraintViolation.getMessage());
        }
        assertThat(constraintViolations.size()).isEqualTo(1);
    }

    @Test
    public void 이름_부적절() throws Exception {
        UserDto newUser = defaultUser
                .setName("aaaa");
        Set<ConstraintViolation<UserDto>> constraintViolations = validator.validate(newUser);
        for (ConstraintViolation<UserDto> constraintViolation : constraintViolations) {
            log.debug("Test error msg : {}", constraintViolation.getMessage());
        }
        assertThat(constraintViolations.size()).isEqualTo(1);
    }

    @Test
    public void 핸드폰번호_부적절1() throws Exception {
        UserDto newUser = defaultUser
                .setPhoneNumber("999-1111-2222");
        Set<ConstraintViolation<UserDto>> constraintViolations = validator.validate(newUser);
        for (ConstraintViolation<UserDto> constraintViolation : constraintViolations) {
            log.debug("Test error msg : {}", constraintViolation.getMessage());
        }
        assertThat(constraintViolations.size()).isEqualTo(1);
    }

    @Test
    public void 핸드폰번호_부적절2() throws Exception {
        UserDto newUser = defaultUser
                .setPhoneNumber("012-1111-2222");
        Set<ConstraintViolation<UserDto>> constraintViolations = validator.validate(newUser);
        for (ConstraintViolation<UserDto> constraintViolation : constraintViolations) {
            log.debug("Test error msg : {}", constraintViolation.getMessage());
        }
        assertThat(constraintViolations.size()).isEqualTo(1);
    }
}