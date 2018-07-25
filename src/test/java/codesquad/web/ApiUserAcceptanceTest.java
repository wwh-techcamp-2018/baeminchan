package codesquad.web;

import codesquad.domain.User;
import org.junit.Before;
import org.junit.Test;
import codesquad.support.AcceptanceTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;


public class ApiUserAcceptanceTest extends AcceptanceTest {

    private static final Logger log = LoggerFactory.getLogger(ApiUserAcceptanceTest.class);

    private User user;

    @Before
    public void setUp() throws Exception {
        user = User.builder()
                .email("a@b.com")
                .name("username")
                .password("password")
                .confirmPassword("password")
                .phone("000-0000-0000")
                .build();
    }

    @Test
    public void create() {
        ResponseEntity<Void> response = template().postForEntity("/users", user, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void create_유효하지않은_인자() {
        user.setEmail("ab"); // sample invalid argument
        ResponseEntity<Void> response = template().postForEntity("/users", user, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST); // 400
    }
}
