package codesquad.atdd;

import codesquad.domain.User;
import codesquad.repository.UserRepository;
import codesquad.support.ErrorResponse;
import codesquad.support.JsonResponse;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Base64;

import static org.assertj.core.api.Assertions.assertThat;


public class ApiUserAcceptanceTest extends AcceptanceTest {
    private static final Logger log = LoggerFactory.getLogger(ApiUserAcceptanceTest.class);

    private final static String BASE_URI = "/api/users";

    @Autowired
    UserRepository userRepository;

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
        User newUser = new User("daaa@cde.com", "PASSWORD123", "PASSWORD123","이", "010-123-1234");
        RequestEntity<User> requestEntity = RequestEntity.post(createURI("")).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).body(newUser);
        ResponseEntity<ErrorResponse> responseEntity = template().exchange(requestEntity, ErrorResponse.class);
        log.debug("response body : {}", responseEntity.getBody());
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
//        assertThat(responseEntity.getBody().getError().get(0))
//                .contains("입력된 비밀번호가 일치하지 않습니다.");
    }
    public User createTestUser(String email){
        User newUser = new User(email, "PASSWORD123", "PASSWORD123","이름", "010-123-1234");
        RequestEntity<User> requestEntity = RequestEntity.post(createURI("")).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).body(newUser);
        template().exchange(requestEntity, JsonResponse.class);
        return newUser;
    }
    @Test
    public void login_성공(){
        User newUser = createTestUser("abc@mmm.com");
        RequestEntity<User> requestEntity = RequestEntity.post(createURI("/login")).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).body(newUser);
        ResponseEntity<JsonResponse> responseEntity = template().exchange(requestEntity, JsonResponse.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().getUrl()).isEqualTo("/");
    }
    @Test
    public void login_실패(){
        User newUser = createTestUser("abc@mmm.com");
        newUser.setPassword("ABCDEFS123");
        RequestEntity<User> requestEntity = RequestEntity.post(createURI("/login")).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).body(newUser);
        ResponseEntity<ErrorResponse> responseEntity = template().exchange(requestEntity, ErrorResponse.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        //assertThat(responseEntity.getBody().getError()).contains("ID / PW 를 확인해주십시오");
    }

    @Test
    public void LoginInterceptor_기능테스트()throws Exception{
        User user = createTestUser("test432111111@test.com");

        HttpHeaders headers = new HttpHeaders();
        String credential = user.getEmail()+ ":" + user.getPassword();
        byte[] basicAuthCredential = Base64.getEncoder().encode(credential.getBytes());
        RequestEntity requestEntity = RequestEntity
                .get(new URI("/test"))
                .header("Authorization", "Basic " + new String(basicAuthCredential))
                .build();
        template().exchange(requestEntity, Void.class);
    }
}
