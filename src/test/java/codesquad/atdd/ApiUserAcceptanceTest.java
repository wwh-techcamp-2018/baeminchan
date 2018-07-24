package codesquad.atdd;

import codesquad.domain.User;
import codesquad.support.ErrorResponse;
import codesquad.support.JsonResponse;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;


public class ApiUserAcceptanceTest extends AcceptanceTest {
    private static final Logger log = LoggerFactory.getLogger(ApiUserAcceptanceTest.class);

    private final static String BASE_URI = "/api/users";

    private URI createURI(String uri) {
        log.debug("Create URI {}", BASE_URI);

        try {
            return new URI(BASE_URI + uri);
        } catch (URISyntaxException e) {
            log.debug("Create URI Error! : {}", e.getMessage());
        }
        return null;
    }

    private HttpEntity createHttpEntity(Object body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity(body, headers);
    }

    @Test
    public void createUser() {
        User newUser = new User("ID@abcde.com", "PASSWORD123", "PASSWORD123","이름", "010-123-1234");
        RequestEntity<User> requestEntity = RequestEntity.post(createURI("")).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).body(newUser);
        ResponseEntity<JsonResponse> responseEntity = template().exchange(requestEntity, JsonResponse.class);

        log.debug("response body : {}", responseEntity.getBody());
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);

    }


    @Test
    public void createUser_실패(){
        User newUser = new User("IDabcde.com", "P", "PASSWORD123","이름", "123456-123-1234");
        RequestEntity<User> requestEntity = RequestEntity.post(createURI("")).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).body(newUser);
        ResponseEntity<ErrorResponse> responseEntity = template().exchange(requestEntity, ErrorResponse.class);
        log.debug("response body : {}", responseEntity.getBody());
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }


}
