package codesquad.user.web;

import codesquad.RestResponse;
import codesquad.user.domain.UserRepository;
import codesquad.user.dto.UserSignupDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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
    private static final String PASSWORD = "password12345";
    @Autowired
    private TestRestTemplate template;

    public TestRestTemplate template() {
        return template;
    }

    @Autowired
    private UserRepository repository;

    @Before
    public void setUp() throws Exception {
        repository.deleteAll();
    }

    @Test
    public void create() {
        UserSignupDto dto = createUserSignupDto(PASSWORD);

        ResponseEntity<RestResponse<?>> responseEntity = createPostResponseEntity("/api/users", dto);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void create_fail_not_match_password() {
        UserSignupDto dto = createUserSignupDto(PASSWORD + 1);

        ResponseEntity<RestResponse<?>> responseEntity = createPostResponseEntity("/api/users", dto);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(responseEntity.getBody().getError().get(0).getField()).isEqualTo("password");
        assertThat(responseEntity.getBody().getError().get(0).getMessage()).isEqualTo("not match password");
    }

    private UserSignupDto createUserSignupDto(String passwordCheck) {
        return UserSignupDto.builder()
                .email("tester@gmail.com")
                .password(PASSWORD)
                .passwordCheck(passwordCheck)
                .name("tester")
                .phoneNumber("010-1234-5678")
                .build();
    }

    private <T> ResponseEntity<RestResponse<?>> createPostResponseEntity(String path, T dto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<T> request = new HttpEntity<>(dto, headers);


        return template()
                .exchange(path,
                        HttpMethod.POST,
                        request,
                        new ParameterizedTypeReference<RestResponse<?>>() {});
    }
}
