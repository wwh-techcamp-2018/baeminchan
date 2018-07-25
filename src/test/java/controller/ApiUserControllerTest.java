package controller;

import codesquad.dto.UserDto;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import support.test.AcceptanceTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiUserControllerTest extends AcceptanceTest {

    private static final Logger log = LoggerFactory.getLogger(ApiUserControllerTest.class);
    private static Validator validator;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Before
    public void setUp() throws Exception {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void 회원가입_정상() throws Exception {
        UserDto newUser = new UserDto("javajigi", "gmail.com", "testt@est134", "testt@est134", "재성", "010-1111-2222");
        Set<ConstraintViolation<UserDto>> constraintViolations = validator.validate(newUser);
        assertThat(constraintViolations.isEmpty()).isTrue();
    }

    @Test
    public void 로그인_성공() throws Exception {
        Map<String, String> newUser = new HashMap<>();
        newUser.put("userId", "javajigi");
        newUser.put("password",  "test33##");

        ResponseEntity<Void> response = template().postForEntity("/api/users/login", newUser, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FOUND);
    }

    @Test
    public void 로그인_존재하지않는아이디() throws Exception {
        Map<String, String> newUser = new HashMap<>();
        newUser.put("userId", "promise");
        newUser.put("password",  "test33##");

        ResponseEntity<Void> response = template().postForEntity("/api/users/login", newUser, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }

    @Test
    public void 로그인_비밀번호틀림() throws Exception {
        Map<String, String> newUser = new HashMap<>();
        newUser.put("userId", "javajigi");
        newUser.put("password",  "test32##");

        ResponseEntity<Void> response = template().postForEntity("/api/users/login", newUser, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }

    @Test
    public void 회원가입_아이디부적절() throws Exception {
        UserDto newUser = new UserDto("javajigi@", "gmail.com", "testt@est134", "testt@est134", "재성", "010-1111-2222");
        Set<ConstraintViolation<UserDto>> constraintViolations = validator.validate(newUser);
        for (ConstraintViolation<UserDto> constraintViolation : constraintViolations) {
            log.info("test error msg : {}", constraintViolation.getMessage());
        }
        assertThat(constraintViolations.size()).isEqualTo(1);
    }

    @Test
    public void 회원가입_이메일_도메인_부적절() throws Exception {
        UserDto newUser = new UserDto("javajigi", "asdfadsf", "testt@est134", "testt@est134", "재성", "010-1111-2222");
        Set<ConstraintViolation<UserDto>> constraintViolations = validator.validate(newUser);
        for (ConstraintViolation<UserDto> constraintViolation : constraintViolations) {
            log.info("test error msg : {}", constraintViolation.getMessage());
        }
        assertThat(constraintViolations.size()).isEqualTo(2);
    }

    @Test
    public void 회원가입_비밀번호_불일치() throws Exception {
        UserDto newUser = new UserDto("javajigi", "gmail.com", "testt@est134", "tes@ttest3332", "재성", "010-1111-2222");
        Set<ConstraintViolation<UserDto>> constraintViolations = validator.validate(newUser);
        for (ConstraintViolation<UserDto> constraintViolation : constraintViolations) {
            log.info("test error msg : {}", constraintViolation.getMessage());
        }
        assertThat(constraintViolations.size()).isEqualTo(1);
    }

    @Test
    public void 회원가입_이름_부적절() throws Exception {
        UserDto newUser = new UserDto("javajigi", "gmail.com", "testt@ttest3332", "testt@ttest3332", "aaaa", "010-1111-2222");
        Set<ConstraintViolation<UserDto>> constraintViolations = validator.validate(newUser);
        for (ConstraintViolation<UserDto> constraintViolation : constraintViolations) {
            log.info("test error msg : {}", constraintViolation.getMessage());
        }
        assertThat(constraintViolations.size()).isEqualTo(1);
    }

    @Test
    public void 회원가입_핸드폰번호_부적절1() throws Exception {
        UserDto newUser = new UserDto("javajigi", "gmail.com", "tes@ttest3332", "tes@ttest3332", "재성", "999-1111-2222");
        Set<ConstraintViolation<UserDto>> constraintViolations = validator.validate(newUser);
        for (ConstraintViolation<UserDto> constraintViolation : constraintViolations) {
            log.info("test error msg : {}", constraintViolation.getMessage());
        }
        assertThat(constraintViolations.size()).isEqualTo(1);
    }

    @Test
    public void 회원가입_핸드폰번호_부적절2() throws Exception {
        UserDto newUser = new UserDto("javajigi", "gmail.com", "tes@ttest3332", "tes@ttest3332", "재성", "012-1111-2222");
        Set<ConstraintViolation<UserDto>> constraintViolations = validator.validate(newUser);
        for (ConstraintViolation<UserDto> constraintViolation : constraintViolations) {
            log.info("test error msg : {}", constraintViolation.getMessage());
        }
        assertThat(constraintViolations.size()).isEqualTo(1);
    }
}