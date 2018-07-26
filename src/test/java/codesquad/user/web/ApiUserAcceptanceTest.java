package codesquad.user.web;

import codesquad.security.ErrorsResponse;
import codesquad.user.dto.LoginDto;
import codesquad.user.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import support.test.AcceptanceTest;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class ApiUserAcceptanceTest extends AcceptanceTest {

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
    public void create_invalid_argument() {
        userDto.setEmail("emailFail");
        userDto.setPhone("phoneFail");

        ResponseEntity<ErrorsResponse> response = template.postForEntity("/users", userDto, ErrorsResponse.class);

        log.info("response header; {}", response.getHeaders());
        log.info("response body; {}", response.getBody().getErrors());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void create_비밀번호불일치() {
        userDto.setConfirmPassword("password1!3");
        ResponseEntity<ErrorsResponse> response = template.postForEntity("/users", userDto, ErrorsResponse.class);

        log.info("response header; {}", response.getHeaders());
        log.info("response body; {}", response.getBody().getErrors());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);

    }

    @Test
    public void login() {
        LoginDto loginDto = LoginDto.builder()
                .email("sample@woowahan.com")
                .password("123456a!")
                .build();

        ResponseEntity<Void> response = template.postForEntity("/users/login", loginDto, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void loginFail_이메일불일치() {
        LoginDto loginDto = LoginDto.builder()
                .email("sampl@woowahan.com")
                .password("123456a!")
                .build();


        ResponseEntity<ErrorsResponse> response = template.postForEntity("/users/login", loginDto, ErrorsResponse.class);

        log.info("response header; {}", response.getHeaders());
        log.info("response body; {}", response.getBody().getErrors());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }

    @Test
    public void loginFail_비밀번호불일치() {
        LoginDto loginDto = LoginDto.builder()
                .email("sample@woowahan.com")
                .password("123456a!@")
                .build();

        ResponseEntity<ErrorsResponse> response = template.postForEntity("/users/login", loginDto, ErrorsResponse.class);

        log.info("response header; {}", response.getHeaders());
        log.info("response body; {}", response.getBody().getErrors());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }
}
