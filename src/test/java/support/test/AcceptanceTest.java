package support.test;

import codesquad.domain.User;
import codesquad.domain.UserRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class AcceptanceTest {
    private static final String DEFAULT_LOGIN_USER = "aa@aa.com";
    private static final String ADMIN_LOGIN_USER = "admin@admin.com";

    @Autowired
    private TestRestTemplate template;

    @Autowired
    private UserRepository userRepository;

    public TestRestTemplate template() {
        return template;
    }

    public ResponseEntity basicAuthRequest(RequestEntity requestEntity, User user) {
        template.withBasicAuth(user.getEmail(), user.getPassword());
        return request(requestEntity);
    }

    public ResponseEntity request(RequestEntity requestEntity) {
        return template.exchange(
                requestEntity.getUrl(),
                requestEntity.getMethod(),
                requestEntity.getBody(),
                requestEntity.getReturnType()
        );
    }

    protected User adminUser() {
        return userRepository.findByEmail(ADMIN_LOGIN_USER).get();
    }

    protected User defaultUser() {
        return userRepository.findByEmail(DEFAULT_LOGIN_USER).get();
    }
}