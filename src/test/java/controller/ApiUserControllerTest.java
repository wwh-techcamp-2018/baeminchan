package controller;

import codesquad.domain.User;
import codesquad.dto.UserDto;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import support.test.AcceptanceTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiUserControllerTest extends AcceptanceTest {
    private static Validator validator;

    @Before
    public void setUp() throws Exception {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void 회원가입_정상() throws Exception {
        UserDto newUser = new UserDto("javajigi", "gmail.com", "test222##", "재성", "010-1111-2222");
        ResponseEntity<Void> response = template().postForEntity("/api/users", newUser, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void 회원가입_아이디비정상() throws Exception {
        UserDto newUser = new UserDto("javajigi@", "gmail.com", "test222##","test222##", "재성", "010-1111-2222");
        ResponseEntity<Void> response = template().postForEntity("/api/users", newUser, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void 회원가입_비밀번호비정상() throws Exception {
        UserDto newUser = new UserDto("javajigi", "gmail.com", "testt@est134","tes@ttest3332", "재성", "010-1111-2222");
        ResponseEntity<Void> response = template().postForEntity("/api/users", newUser, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }
    @Test
    public void 회원가입_비밀번호비정상() throws Exception {
        UserDto newUser = new UserDto("javajigi", "gmail.com", "testtest","testtest2", "재성", "010-1111-2222");
        Set<ConstraintViolation<UserDto>> constraintViolations = validator.validate(newUser);
        System.out.println("size : " + constraintViolations.size());
        assertThat(constraintViolations.size()).isEqualTo(1);
    }
    @Test
    public void 회원가입_이름비정상() throws Exception {
        UserDto newUser = new UserDto("javajigi", "gmail.com", "test222##", "쏭", "010-1111-2222");
        ResponseEntity<Void> response = template().postForEntity("/api/users", newUser, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void 회원가입_핸드폰번호비정상() throws Exception {
        UserDto newUser = new UserDto("javajigi", "gmail.com", "test222##", "재성", "010-1111-22223");
        ResponseEntity<Void> response = template().postForEntity("/api/users", newUser, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }
}