package controller;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import support.test.AcceptanceTest;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiUserControllerTest extends AcceptanceTest {

    private static final Logger log = LoggerFactory.getLogger(ApiUserControllerTest.class);

    // 클라이언트에서 빈칸 확인하기
    // MessageSource 빈 설정을 분리한 이유는? 설정이 어디있나요?
    // User의 setter, getter를 왠만하면 없애자
    // 에러메세지를 MessageSource(messages.properties)와 같은곳에서 관리하면 어떨까?
    // Test에서 UserDto중복 많은데 어떻게 개선할까?
    // Acceptance test있으면 좋겠다.

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void 로그인_성공() throws Exception {
        Map<String, String> newUser = new HashMap<>();
        newUser.put("userId", "javajigi@naver.com");
        newUser.put("password", "test33##");

        ResponseEntity<Void> response = template().postForEntity("/api/users/login", newUser, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FOUND);
    }

    @Test
    public void 로그인_존재하지않는아이디() throws Exception {
        Map<String, String> newUser = new HashMap<>();
        newUser.put("userId", "promise");
        newUser.put("password", "test33##");

        ResponseEntity<Void> response = template().postForEntity("/api/users/login", newUser, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }

    @Test
    public void 로그인_비밀번호틀림() throws Exception {
        Map<String, String> newUser = new HashMap<>();
        newUser.put("userId", "javajigi");
        newUser.put("password", "test32##");

        ResponseEntity<Void> response = template().postForEntity("/api/users/login", newUser, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }

}