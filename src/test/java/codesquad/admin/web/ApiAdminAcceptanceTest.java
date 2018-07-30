package codesquad.admin.web;

import codesquad.category.domain.Category;
import codesquad.exception.UnAuthenticationException;
import codesquad.security.ErrorsResponse;
import codesquad.user.dto.LoginDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import support.test.AcceptanceTest;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class ApiAdminAcceptanceTest extends AcceptanceTest {

    @Test
    public void login() {
        LoginDto loginDto = LoginDto.builder()
                .email("sample2@woowahan.com")
                .password("123456a!")
                .build();

        ResponseEntity<Void> response = template.postForEntity("/admin/login", loginDto, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void loginFail_일반유저() {
        LoginDto loginDto = LoginDto.builder()
                .email("sample@woowahan.com")
                .password("123456a!")
                .build();

        ResponseEntity<ErrorsResponse> response = template.postForEntity("/admin/login", loginDto, ErrorsResponse.class);

        log.info("response body; {}", response.getBody().getErrors());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }

}
