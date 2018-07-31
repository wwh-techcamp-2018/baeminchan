package codesquad.support;

import codesquad.RestResponse;
import codesquad.user.domain.User;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class ApiAcceptanceTest {
    @Autowired
    private TestRestTemplate template;

    public TestRestTemplate template() {
        return template;
    }

    protected <T> ResponseEntity<RestResponse<List<T>>> getResponseEntityList(String path,
                                                                              ParameterizedTypeReference<RestResponse<List<T>>> typeReference) {
        HttpHeaders headers = getHeaders();

        HttpEntity<Void> request = new HttpEntity<>(headers);

        return template()
                .exchange(path,
                        HttpMethod.GET,
                        request,
                        typeReference);
    }

    protected <T> ResponseEntity<RestResponse<T>> getResponseEntity(String path,
                                                                    ParameterizedTypeReference<RestResponse<T>> typeReference) {
        HttpHeaders headers = getHeaders();

        HttpEntity<Void> request = new HttpEntity<>(headers);

        return template()
                .exchange(path,
                        HttpMethod.GET,
                        request,
                        typeReference);
    }

    protected <T, R> ResponseEntity<RestResponse<R>> createPostResponseEntity(String path,
                                                                              T dto,
                                                                              ParameterizedTypeReference<RestResponse<R>> typeReference) {
        HttpHeaders headers = getHeaders();

        HttpEntity<T> request = new HttpEntity<>(dto, headers);


        return template()
                .exchange(path,
                        HttpMethod.POST,
                        request,
                        typeReference);
    }


    protected <T, R> ResponseEntity<RestResponse<R>> createPostResponseEntityWithUser(User user,
                                                                                      String path,
                                                                                      T dto,
                                                                                      ParameterizedTypeReference<RestResponse<R>> typeReference) {
        HttpHeaders headers = getHeaders();

        HttpEntity<T> request = new HttpEntity<>(dto, headers);


        return template()
                .withBasicAuth(user.getEmail(), user.getPassword())
                .exchange(path,
                        HttpMethod.POST,
                        request,
                        typeReference);
    }

    protected <T> ResponseEntity<RestResponse<T>> deleteEntityWithUser(User user,
                                                                       String path,
                                                                       ParameterizedTypeReference<RestResponse<T>> typeReference) {

        HttpEntity<T> request = new HttpEntity<>(getHeaders());

        return template()
                .withBasicAuth(user.getEmail(), user.getPassword())
                .exchange(path,
                        HttpMethod.DELETE,
                        request,
                        typeReference);
    }


    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
