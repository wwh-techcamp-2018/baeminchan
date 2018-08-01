package support.test;

import codesquad.user.domain.User;
import codesquad.user.domain.UserRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public abstract class AcceptanceTest {

    private static final String DEFAULT_USER = "sample@woowahan.com";
    private static final String ADMIN_USER = "sample2@woowahan.com";

    @Autowired
    protected TestRestTemplate template;

    @Autowired
    private UserRepository userRepository;


    public TestRestTemplate basicAuthTemplate() {
        return basicAuthTemplate(defaultAdmin());
    }

    public TestRestTemplate basicAuthTemplate(User loginUser) {
        return template.withBasicAuth(loginUser.getEmail(), "123456a!");
    }

    protected User defaultUser() {
        return findByEmail(DEFAULT_USER);
    }

    protected User defaultAdmin() {
        return findByEmail(ADMIN_USER);
    }

    protected User findByEmail(String userId) {
        return userRepository.findByEmail(userId).orElseThrow(EntityNotFoundException::new);
    }

    protected String createResource(String path, Object bodyPayload, User loginUser) {
        ResponseEntity<Void> response = basicAuthTemplate(loginUser).postForEntity(path, bodyPayload, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        return response.getHeaders().getLocation().getPath();
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
