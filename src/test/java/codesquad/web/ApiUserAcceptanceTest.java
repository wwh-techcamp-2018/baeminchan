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
    public void setUp() throws Exception {
        defaultUser = new UserDto("javajigi@java.com", "1234qwer!", "자바지기", "010-4090-8370", "1234qwer!");
        template().postForEntity("/api/users", defaultUser, Void.class);
    }

    /**
     * Todo
     * 회원가입에 대한 테스트코드
     *
     * - 서버
     * 4. error message 파일 만들기
     *
     *
     * Done
     *  1. SLF4J logging 설정
     *  2. 예외처리 - 메시지랑 status code 보내기
     *  2-1. 예외처리 메시지 확인 테스트
     *  3. 비밀번호 암호화 BCrypt
     *
     *  5. 유효성체크 테스트 코드 별도의 파일로 분리
     *
     * - 클라이언트
     *
     * Done
     * 1. AJAX 구현
     * 2. 전송 데이터 포맷 맞추는 함수 구현(ex. 전화번호)
     * 3. response 처리
     *
     */

    @Test
    public void create() {
        UserDto newUser = new UserDto("sanjigi@java.com", "1234qwer!@", "산지기", "010-1234-1234", "1234qwer!@");
        ResponseEntity<Void> response = template().postForEntity("/api/users", newUser, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void create_비밀번호_불일치() {
        defaultUser.setRePassword("1234qwer!!!");

        ResponseEntity<ErrorResponse> response = template().postForEntity("/api/users", defaultUser, ErrorResponse.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void create_정규식_예외() {
        defaultUser.setUserId("gusdk");

        ResponseEntity<ValidationErrorsResponse> response = template().postForEntity("/api/users", defaultUser, ValidationErrorsResponse.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    /**
     * Todo
     *
     * 로그인에 대한 테스트코드
     * - 서버
     * 2. @LoginUser 추가
     *
     * Done
     * 1. 로그인 가능, 불가능 테스트 코드
     *
     * - 클라이언트
     *
     * Done
     * 1. AJAX 구현
     * 2. response 처리
     * 3. 상단 로그인, 회원가입 메뉴 안보이게 하기
     *
     */

    @Test
    public void login_성공() {
        ResponseEntity<Void> response = template().postForEntity("/api/users/login", defaultUser, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void login_실패() {
        defaultUser.setPassword("4321rewq!");
        ResponseEntity<ErrorResponse> response = template().postForEntity("/api/users/login", defaultUser, ErrorResponse.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
}