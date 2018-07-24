package support.test;

import codesquad.domain.UserRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class AcceptanceTest {
    private static final String DEFAULT_LOGIN_USER = "javajigi";

    @Autowired
    private TestRestTemplate template;

    @Autowired
    private UserRepository userRepository;

    public TestRestTemplate template() {
        return template;
    }

//    public TestRestTemplate basicAuthTemplate() {
//        return basicAuthTemplate(defaultUser());
//    }
//
//    public TestRestTemplate basicAuthTemplate(User loginUser) {
//        return template.withBasicAuth(loginUser.getUserId(), loginUser.getPassword());
//    }
//
//    protected User defaultUser() {
//        return findByUserId(DEFAULT_LOGIN_USER);
//    }
}