package codesquad.support;

import codesquad.RestResponse;
import codesquad.user.domain.Role;
import codesquad.user.domain.User;
import codesquad.user.domain.UserRepository;
import codesquad.user.domain.UserTest;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public abstract class AcceptanceTest {
    @Autowired
    protected UserRepository userRepository;

    @Autowired
    private TestRestTemplate template;

    public TestRestTemplate template() {
        return template;
    }

    protected <T> ResponseEntity<RestResponse<List<T>>> getResponseEntityList(String path,
                                                                              ParameterizedTypeReference<RestResponse<List<T>>> typeReference) {
        return template()
                .exchange(path,
                        HttpMethod.GET,
                        new HttpEntity<>(getHeaders()),
                        typeReference);
    }

    protected <T, R> ResponseEntity<RestResponse<R>> createPostResponseEntity(String path,
                                                                              T dto,
                                                                              ParameterizedTypeReference<RestResponse<R>> typeReference) {
        return template()
                .exchange(path,
                        HttpMethod.POST,
                        new HttpEntity<>(dto, getHeaders()),
                        typeReference);
    }


    protected <T, R> ResponseEntity<RestResponse<R>> createPostResponseEntityWithUser(User user,
                                                                                      String path,
                                                                                      T dto,
                                                                                      ParameterizedTypeReference<RestResponse<R>> typeReference) {
        return template()
                .withBasicAuth(user.getEmail(), user.getPassword())
                .exchange(path,
                        HttpMethod.POST,
                        new HttpEntity<>(dto, getHeaders()),
                        typeReference);
    }

    protected <T> ResponseEntity<RestResponse<T>> deleteEntityWithUser(User user,
                                                                       String path,
                                                                       ParameterizedTypeReference<RestResponse<T>> typeReference) {
        return template()
                .withBasicAuth(user.getEmail(), user.getPassword())
                .exchange(path,
                        HttpMethod.DELETE,
                        new HttpEntity<>(getHeaders()),
                        typeReference);
    }


    protected <T> ResponseEntity<RestResponse<T>> deleteEntity(String path,
                                                               ParameterizedTypeReference<RestResponse<T>> typeReference) {
        return template()
                .exchange(path,
                        HttpMethod.DELETE,
                        new HttpEntity<>(getHeaders()),
                        typeReference);
    }



    protected User makeUser(Role role) {
        User rawUser = UserTest.userBuilder().password(UserTest.RAW_PASSWORD).role(role).build();
        User savedUser = UserTest.userBuilder().role(role).build();
        userRepository.save(savedUser);

        return rawUser;
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
