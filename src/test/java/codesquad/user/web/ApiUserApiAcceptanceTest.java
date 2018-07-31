package codesquad.user.web;

import codesquad.RestResponse;
import codesquad.support.ApiAcceptanceTest;
import codesquad.user.domain.UserRepository;
import codesquad.user.domain.UserTest;
import codesquad.user.dto.UserLoginDto;
import codesquad.user.dto.UserSignupDto;
import codesquad.user.dto.UserSignupDtoTest;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiUserApiAcceptanceTest extends ApiAcceptanceTest {
    private static final Logger log = LoggerFactory.getLogger(ApiUserApiAcceptanceTest.class);

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
                .passwordCheck(UserTest.RAW_PASSWORD + 1)
                .build();


        ResponseEntity<RestResponse<Void>> responseEntity = createPostResponseEntity("/api/users", dto, getVoidType());
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(responseEntity.getBody().getError().get(0).getField()).isEqualTo("matchPassword");
    }

    @Test
    public void login() {
        UserSignupDto signupDto = UserSignupDtoTest.validDtoBuilder().build();
        assertCreateUser(signupDto);

        UserLoginDto dto = UserLoginDto.builder()
                .email(signupDto.getEmail())
                .password(UserTest.RAW_PASSWORD)
                .build();

        ResponseEntity<RestResponse<Void>> responseEntity = createPostResponseEntity("/api/users/login", dto, getVoidType());
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void login_unauthenticated() {
        UserLoginDto dto = UserLoginDto.builder()
                .email("tester@gmail.com")
                .password(UserTest.RAW_PASSWORD)
                .build();

        ResponseEntity<RestResponse<Void>> responseEntity = createPostResponseEntity("/api/users/login", dto, getVoidType());
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
        assertThat(responseEntity.getBody().getError()).hasSize(1);
    }

    @Test
    public void login_wrong_password() {
        UserSignupDto signupDto = UserSignupDtoTest.validDtoBuilder().build();
        assertCreateUser(signupDto);

        UserLoginDto dto = UserLoginDto.builder()
                .email(signupDto.getEmail())
                .password(UserTest.RAW_PASSWORD + 1)
                .build();

        ResponseEntity<RestResponse<Void>> responseEntity = createPostResponseEntity("/api/users/login", dto, getVoidType());
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
        assertThat(responseEntity.getBody().getError()).hasSize(1);
    }

    private void assertCreateUser(UserSignupDto dto) {

        ResponseEntity<RestResponse<Void>> responseEntity = createPostResponseEntity("/api/users", dto, getVoidType());
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    private ParameterizedTypeReference<RestResponse<Void>> getVoidType() {
        return new ParameterizedTypeReference<RestResponse<Void>>() {
        };
    }
}
