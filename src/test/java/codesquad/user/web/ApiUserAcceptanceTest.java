package codesquad.user.web;

import codesquad.RestResponse;
import codesquad.user.domain.UserRepository;
import codesquad.user.domain.UserTest;
import codesquad.user.dto.UserLoginDto;
import codesquad.user.dto.UserSignupDto;
import codesquad.user.dto.UserSignupDtoTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApiUserAcceptanceTest {
    private static final Logger log = LoggerFactory.getLogger(ApiUserAcceptanceTest.class);

    @Autowired
    private TestRestTemplate template;

    @Autowired
    private UserRepository repository;

    @Before
    public void setUp() throws Exception {
        repository.deleteAll();
    }

    @Test
    public void create() {
        UserSignupDto signupDto = UserSignupDtoTest.validDtoBuilder().build();
        assertCreateUser(signupDto);
    }

    @Test
    public void create_fail_not_match_password() {
        UserSignupDto dto = UserSignupDtoTest.validDtoBuilder()
                .passwordCheck(UserTest.PASSWORD + 1)
                .build();


        ResponseEntity<RestResponse<?>> responseEntity = createPostResponseEntity("/api/users", dto);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(responseEntity.getBody().getError().get(0).getField()).isEqualTo("matchPassword");
    }

    @Test
    public void login() {
        UserSignupDto signupDto = UserSignupDtoTest.validDtoBuilder().build();
        assertCreateUser(signupDto);

        UserLoginDto dto = UserLoginDto.builder()
                .email(signupDto.getEmail())
                .password(UserTest.PASSWORD)
                .build();

        ResponseEntity<RestResponse<?>> responseEntity = createPostResponseEntity("/api/users/login", dto);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void login_unauthenticated() {
        UserLoginDto dto = UserLoginDto.builder()
                .email("tester@gmail.com")
                .password(UserTest.PASSWORD)
                .build();

        ResponseEntity<RestResponse<?>> responseEntity = createPostResponseEntity("/api/users/login", dto);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(responseEntity.getBody().getError()).hasSize(1);
    }

    @Test
    public void login_wrong_password() {
        UserSignupDto signupDto = UserSignupDtoTest.validDtoBuilder().build();
        assertCreateUser(signupDto);

        UserLoginDto dto = UserLoginDto.builder()
                .email(signupDto.getEmail())
                .password(UserTest.PASSWORD + 1)
                .build();

        ResponseEntity<RestResponse<?>> responseEntity = createPostResponseEntity("/api/users/login", dto);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(responseEntity.getBody().getError()).hasSize(1);
    }

    private void assertCreateUser(UserSignupDto dto) {

        ResponseEntity<RestResponse<?>> responseEntity = createPostResponseEntity("/api/users", dto);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    private <T> ResponseEntity<RestResponse<?>> createPostResponseEntity(String path, T dto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<T> request = new HttpEntity<>(dto, headers);


        return template
                .exchange(path,
                        HttpMethod.POST,
                        request,
                        new ParameterizedTypeReference<RestResponse<?>>() {
                        });
    }

}
