package codesquad.service;

import codesquad.domain.RoleRepository;
import codesquad.domain.User;
import codesquad.domain.UserRepository;
import codesquad.dto.UserDto;
import codesquad.exception.NotMatchException;
import codesquad.exception.UnAuthenticationException;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Test
    public void create_비밀번호_불일치() throws NotMatchException {
        newUser.setRePassword("1234qwer!!!");

        ResponseEntity<Void> response = template().postForEntity("/api/users", newUser, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }

    @Test
    public void create_정규식_예외() throws NotMatchException {
        newUser.setUserId("gusdk");
        newUser.setPassword("zzzzzz");

        ResponseEntity<Void> response = template().postForEntity("/api/users", newUser, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }
}