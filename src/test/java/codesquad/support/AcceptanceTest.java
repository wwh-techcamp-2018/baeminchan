package codesquad.support;

import codesquad.domain.User;
import codesquad.repository.UserRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("develop")
public abstract class AcceptanceTest {
    private static final String DEFAULT_LOGIN_USER = "a@naver.com";

    @Autowired
    private TestRestTemplate template;

    @Autowired
    private UserRepository userRepository;

    public TestRestTemplate template() {
        return template;
    }

    public TestRestTemplate templateWithAdminUser() {
        return template().withBasicAuth("a@naver.com", "test1234");
    }

    public TestRestTemplate templateWithNormalUser() {
        return template().withBasicAuth("b@naver.com", "test1234");
    }

    protected User defaultUser() {
        return findByEmail(DEFAULT_LOGIN_USER);
    }

    protected User findByEmail(String email) {
        return userRepository.findByEmail(email).get();
    }
}
