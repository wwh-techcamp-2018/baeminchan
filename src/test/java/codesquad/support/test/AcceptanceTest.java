package codesquad.support.test;

import codesquad.support.dto.ResponseModel;
import codesquad.user.domain.User;
import codesquad.user.domain.UserRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class AcceptanceTest {

    private static final String DEFAULT_ADMIN_UUID = "uuid1";
    private static final String DEFAULT_USER_UUID = "uuid2";

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    private TestRestTemplate template;

    public TestRestTemplate template() {
        return template;
    }

    protected User defaultAdmin() {
        return findUserById(DEFAULT_ADMIN_UUID);
    }

    protected User defaultUser() {
        return findUserById(DEFAULT_USER_UUID);
    }

    private User findUserById(String uuid) {
        return userRepository.findById(uuid).get();
    }

    protected <T> ResponseEntity<ResponseModel<T>> jsonRequest(String url, HttpMethod method, Object body, ParameterizedTypeReference<ResponseModel<T>> parameterizedTypeReference) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return template.exchange(url, method, new HttpEntity<>(body, headers), parameterizedTypeReference);
    }
}
