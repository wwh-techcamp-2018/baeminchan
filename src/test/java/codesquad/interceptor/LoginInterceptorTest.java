package codesquad.interceptor;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import support.test.AcceptanceTest;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginInterceptorTest extends AcceptanceTest {
    private static final Logger log = LoggerFactory.getLogger(LoginInterceptorTest.class);

    @Test
    public void 비로그인_사용자() {
        ResponseEntity<String> response = template().getForEntity("/admin", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void 로그인_사용자() {
        ResponseEntity<String> response = basicAuthTemplate(defaultUser()).getForEntity("/admin", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}