package codesquad.support;

import codesquad.user.domain.User;
import codesquad.user.domain.UserRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("development")
public abstract class AcceptanceTest {

    String ADMIN = "admin@gmail.com";
    String DEFAULT = "javajigi@gmail.com";

    @Autowired
    private TestRestTemplate template;

    @Autowired
    private UserRepository userRepository;


    public TestRestTemplate template() {
        return template;
    }

    public TestRestTemplate basicAuthTemplate() {
        return basicAuthTemplate(defaultUser());
    }

    public TestRestTemplate adminAuthTemplate() {
        return basicAuthTemplate(adminUser());
    }

    public TestRestTemplate basicAuthTemplate(User loginUser) {
        return template.withBasicAuth(loginUser.getEmail(), "haha123!");
    }

    protected User defaultUser() {
        return findByEmail(DEFAULT);
    }

    protected User adminUser() {
        return findByEmail(ADMIN);
    }

    protected User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(RuntimeException::new);
    }

    protected HttpEntity createHttpEntity() {
        return createHttpEntityWithBody(null);
    }

    protected HttpEntity createHttpEntityWithBody(Object body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity(body, headers);
    }


}