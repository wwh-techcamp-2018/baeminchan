package codesquad.atdd;

import codesquad.domain.User;
import codesquad.support.ErrorResponse;
import codesquad.support.JsonResponse;
import codesquad.support.ValidationError;
import org.assertj.core.api.SoftAssertions;
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
        User newUser = new User("daaa@cde.com", "PPASSWORD123", "PASSWORD123","이름", "010-123-1234");
        RequestEntity<User> requestEntity = RequestEntity.post(createURI("")).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).body(newUser);
        ResponseEntity<ErrorResponse> responseEntity = template().exchange(requestEntity, ErrorResponse.class);
        log.debug("response body : {}", responseEntity.getBody());
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(responseEntity.getBody().getError().get(0).getMessage())
                .isEqualTo("입력된 비밀번호가 일치하지 않습니다.");
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
        assertThat(responseEntity.getBody().getError().get(0).getMessage()).isEqualTo("ID / PW 를 확인해주십시오");
    }

    @Test
    public void createUser_2개이상실패() {
        SoftAssertions softly = new SoftAssertions();

        User newUser = new User("daaa@cde.com", "PPASSWORD123", "PASSWORD123","abcd", "110-123-1234");
        RequestEntity<User> requestEntity = RequestEntity.post(createURI("")).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).body(newUser);
        ResponseEntity<ErrorResponse> responseEntity = template().exchange(requestEntity, ErrorResponse.class);
        log.debug("response body : {}", responseEntity.getBody());
        softly.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        softly.assertThat(responseEntity.getBody().getError())
                .containsExactlyInAnyOrder( new ValidationError("정규 표현식 \"^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$\" 패턴과 일치해야 합니다.","phoneNumber"),
                        new ValidationError("정규 표현식 \"^[가-힣]*$\" 패턴과 일치해야 합니다.", "name"));
    }
}