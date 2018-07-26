package controller;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import support.test.AcceptanceTest;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiUserControllerTest extends AcceptanceTest {

    private static final Logger log = LoggerFactory.getLogger(ApiUserControllerTest.class);

    // Message source 설정이 어디있나요?
    // 에러메세지를 MessageSource(messages.properties)와 같은곳에서 관리하면 어떨까?
    // Acceptance test있으면 좋겠다.

    private Map<String, String> defaultUser = new HashMap<>();

    @Before
    public void setUp() throws Exception {
        defaultUser.put("userId", "javajigi@naver.com");
        defaultUser.put("password", "test33##");
    }

    @Test
    public void 로그인_성공() throws Exception {

        ResponseEntity<Void> response = template().postForEntity("/api/users/login", defaultUser, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FOUND);
    }

    @Test
    public void 로그인_존재하지않는아이디() throws Exception {
        Map<String, String> newUser = defaultUser;
        newUser.put("userId", "promise");

        ResponseEntity<Void> response = template().postForEntity("/api/users/login", newUser, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }

    @Test
    public void 로그인_비밀번호틀림() throws Exception {
        Map<String, String> newUser = defaultUser;
        newUser.put("password", "test32##");

        ResponseEntity<Void> response = template().postForEntity("/api/users/login", newUser, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }
}