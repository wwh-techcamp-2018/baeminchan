package codesquad.service;

import codesquad.domain.RoleRepository;
import codesquad.domain.User;
import codesquad.domain.UserRepository;
import codesquad.dto.UserDto;
import codesquad.exception.ErrorResponse;
import codesquad.exception.NotMatchException;
import codesquad.exception.UnAuthenticationException;
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

public class UserServiceTest extends AcceptanceTest {
    private static final Logger log = LoggerFactory.getLogger(UserServiceTest.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserService userService;

    private User otherUser;
    private UserDto newUser;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Before
    public void setUp() throws Exception {
//        otherUser = new User("wngk@wngk.com", "12341234", "박주하", "01087562544", roleRepository.findByAuthority(Authority.NORMAL).orElseThrow(IllegalArgumentException::new));
        newUser = new UserDto("javajigi@java.com", "1234qwer!", "자바지기", "010-4090-8370", "1234qwer!");
    }

    @Test
    public void login() throws UnAuthenticationException {
        ResponseEntity<Void> response = template().postForEntity("/api/users/login", defaultUser(), Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FOUND);
        assertThat(response.getHeaders().getLocation().getPath()).startsWith("/");
    }

    @Test
    public void create() throws NotMatchException {
        ResponseEntity<Void> response = template().postForEntity("/api/users", newUser, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    /**
     * Todo
     * 회원가입에 대한 테스트코드
     *
     * - 서버
     * 4. error message 파일 만들기
     * 5. 유효성체크 테스트 코드 별도의 파일로 분리
     *
     * Done
     *  1. SLF4J logging 설정
     *  2. 예외처리 - 메시지랑 status code 보내기
     *  2-1. 예외처리 메시지 확인 테스트
     *  3. 비밀번호 암호화 BCrypt
     *
     * - 클라이언트
     * 2. 전송 데이터 포맷 맞추는 함수 구현(ex. 전화번호)
     * 3. response 처리
     *
     * Done
     * 1. AJAX 구현
     *
     */

    @Test
    public void create_비밀번호_불일치() throws NotMatchException {
        newUser.setRePassword("1234qwer!!!");

        ResponseEntity<ErrorResponse> response = template().postForEntity("/api/users", newUser, ErrorResponse.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        log.debug("debug message : {}", response.getBody().getMessage());
    }

    @Test
    public void create_정규식_예외() throws NotMatchException {
        newUser.setUserId("gusdk");

        ResponseEntity<ErrorResponse> response = template().postForEntity("/api/users", newUser, ErrorResponse.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        log.debug("\n\ndebug message : {}\n\n", response.getBody().getMessage());
    }
}