package codesquad.web;

import codesquad.domain.UserDto;
import codesquad.validate.ValidationErrorsResponse;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import support.test.AcceptanceTest;

import static org.assertj.core.api.Assertions.assertThat;


public class ApiUserAcceptanceTest extends AcceptanceTest {

    private static final Logger log = LoggerFactory.getLogger(ApiUserAcceptanceTest.class);

    private UserDto userDto;

    @Before
    public void setUp() throws Exception {
        userDto = UserDto.builder()
                .email("baeminchan@woowa.com")
                .name("woowahan")
                .password("password1!2")
                .confirmPassword("password1!2")
                .phone("010-1234-5678")
                .build();
    }

    @Test
    public void create() {
        ResponseEntity<Void> response = template.postForEntity("/users", userDto, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void creat_invalid_argument() {
        userDto.setEmail("emailFail");
        userDto.setPhone("phoneFail");

        ResponseEntity<ValidationErrorsResponse> response = template.postForEntity("/users", userDto, ValidationErrorsResponse.class);

        log.info("response header; {}", response.getHeaders());
        log.info("response body; {}", response.getBody().getErrors());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
}
