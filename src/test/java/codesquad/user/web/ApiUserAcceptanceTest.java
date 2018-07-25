package codesquad.user.web;

import codesquad.RestResponse;
import codesquad.user.domain.UserRepository;
import codesquad.user.dto.UserSignupDto;
import codesquad.user.dto.UserSignupDtoTest;
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
        UserSignupDto dto = UserSignupDtoTest.validDtoBuilder().build();

        ResponseEntity<RestResponse<?>> responseEntity = createPostResponseEntity("/api/users", dto);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void create_fail_not_match_password() {
        UserSignupDto dto = UserSignupDtoTest.validDtoBuilder()
                .passwordCheck(UserSignupDtoTest.PASSWORD + 1)
                .build();

        ResponseEntity<RestResponse<?>> responseEntity = createPostResponseEntity("/api/users", dto);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(responseEntity.getBody().getError().get(0).getField()).isEqualTo("password");
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
