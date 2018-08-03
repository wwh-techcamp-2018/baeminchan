package support.test;

import codesquad.domain.user.User;
import codesquad.domain.user.UserRepository;
import codesquad.dto.user.UserSessionDto;
import codesquad.util.SessionUtil;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
public abstract class AcceptanceTest {
    private static final String DEFAULT_LOGIN_USER = "javajigi";

    @Autowired
    private TestRestTemplate template;

    @Autowired
    private UserRepository userRepository;

    public TestRestTemplate template() {
        return template;
    }

    public TestRestTemplate basicAuthTemplate(String email, String password) {
        return template.withBasicAuth(email, password);
    }

    public TestRestTemplate basicAuthTemplate() {
        return basicAuthTemplate("admin", "qwer1234!!");
    }

    protected User findByEmail(String email) {
        return userRepository.findByEmail(email).get();
    }
}
