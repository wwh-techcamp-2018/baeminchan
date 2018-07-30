package codesquad.web;

import codesquad.dto.UserDto;
import codesquad.exception.ErrorResponse;
import codesquad.validate.ValidationErrorsResponse;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import support.test.AcceptanceTest;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiUserAcceptanceTest extends AcceptanceTest {
    private static final Logger log = LoggerFactory.getLogger(ApiUserAcceptanceTest.class);

    private UserDto defaultUser;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Before
    public void setUp() {
        defaultUser = new UserDto("javajigi@java.com", "1234qwer!", "자바지기", "010-4090-8370", "1234qwer!");
    }

    @Test
    public void 회원_등록() {
        UserDto newUser = new UserDto("sanjigi@java.com", "1234qwer!@", "산지기", "010-1234-1234", "1234qwer!@");
        ResponseEntity<Void> response = template().postForEntity("/api/users", newUser, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void 회원가입_일차_이차_비밀번호_불일치() {
        defaultUser.setRePassword("1234qwer!!!");

        ResponseEntity<ErrorResponse> response = template().postForEntity("/api/users", defaultUser, ErrorResponse.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void 회원가입_정규식_예외() {
        defaultUser.setUserId("gusdk");

        ResponseEntity<ValidationErrorsResponse> response = template().postForEntity("/api/users", defaultUser, ValidationErrorsResponse.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void 로그인_성공() {
        UserDto newUser = new UserDto("javajigi@java.com", "1234qwer!", "자바지기", "010-4090-8370", "1234qwer!");;
        template().postForEntity("/api/users", newUser, Void.class);

        ResponseEntity<Void> response = template().postForEntity("/api/users/login", newUser, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void 로그인_실패() {
        UserDto newUser = new UserDto("javajigi@java.com", "1234qwer!", "자바지기", "010-4090-8370", "1234qwer!");
        template().postForEntity("/api/users", newUser, Void.class);

        newUser.setPassword("4321rewq!");
        ResponseEntity<ErrorResponse> response = template().postForEntity("/api/users/login", newUser, ErrorResponse.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
}