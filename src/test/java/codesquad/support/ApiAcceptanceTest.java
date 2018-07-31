package codesquad.support;

import codesquad.RestResponse;
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

    protected <T> ResponseEntity<RestResponse<List<T>>> getResponseEntityList(String path, ParameterizedTypeReference<RestResponse<List<T>>> typeReference) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Void> request = new HttpEntity<>(headers);

        return template()
                .exchange(path,
                        HttpMethod.GET,
                        request,
                        typeReference);
    }

    protected <T> ResponseEntity<RestResponse<T>> getResponseEntity(String path) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Void> request = new HttpEntity<>(headers);

        return template()
                .exchange(path,
                        HttpMethod.GET,
                        request,
                        new ParameterizedTypeReference<RestResponse<T>>() {
                        });
    }

    protected <T, R> ResponseEntity<RestResponse<R>> createPostResponseEntity(String path, T dto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<T> request = new HttpEntity<>(dto, headers);


        return template()
                .exchange(path,
                        HttpMethod.POST,
                        request,
                        new ParameterizedTypeReference<RestResponse<R>>() {
                        });
    }
}
