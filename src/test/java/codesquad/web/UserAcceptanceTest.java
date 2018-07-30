package codesquad.web;

import codesquad.domain.DomainField;
import codesquad.dto.LoginDto;
import codesquad.dto.SignupDto;
import codesquad.validation.Error;
import codesquad.validation.ErrorResult;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import support.test.AcceptanceTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class UserAcceptanceTest extends AcceptanceTest {

    private static final String SIGNUP_URL = "/users/signup";
    private static final String LOGIN_URL = "/users/login";

    @Test
    public void signup() {
        SignupDto signupDto = new SignupDto("aabb@aa.com", "password1", "password1", "name", "00-00-00");
        ResponseEntity<Void> responseEntity = template().postForEntity(SIGNUP_URL, signupDto, Void.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void signupEmailExist() {
        SignupDto signupDto = new SignupDto("aa@aa.com", "password1", "password1", "name", "00-00-00");
        ResponseEntity<ErrorResult> responseEntity = template().postForEntity(SIGNUP_URL, signupDto, ErrorResult.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(responseEntity.getBody().getErrors()).contains(new Error(DomainField.USER_EMAIL.getFieldName(), "이미 존재하는 아이디입니다."));
    }

    @Test
    public void signupPasswordNotMatch() {
        SignupDto signupDto = new SignupDto("aanew@aa.com", "password1", "password12", "name", "00-00-00");
        ResponseEntity<ErrorResult> responseEntity = template().postForEntity(SIGNUP_URL, signupDto, ErrorResult.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(responseEntity.getBody().getErrors()).contains(new Error(DomainField.USER_PASSWORD_CONFIRM.getFieldName(), "비밀번호와 비밀번호 확인이 일치하지 않습니다."));
    }

    @Test
    public void signupPatternNotMatch() {
        SignupDto signupDto = new SignupDto("aanew@aa.com", "password1@", "password1@", "", "00-00-00안녕");
        ResponseEntity<ErrorResult> responseEntity = template().postForEntity(SIGNUP_URL, signupDto, ErrorResult.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

        List<Error> errors = responseEntity.getBody().getErrors();
        assertThat(new Error(DomainField.USER_PASSWORD.getFieldName(), "password 형식에 맞게 입력해주세요.")).isIn(errors);
        assertThat(new Error(DomainField.USER_PHONENUMBER.getFieldName(), "전화번호는 숫자만 입력해주세요.")).isIn(errors);
        assertThat(new Error(DomainField.USER_NAME.getFieldName(), "이름은 1 에서 10 사이의 길이로 입력해주세요.")).isIn(errors);
    }

    @Test
    public void login() {
        LoginDto loginDto = new LoginDto("aa@aa.com", "password1");
        ResponseEntity<Void> responseEntity = template().postForEntity(LOGIN_URL, loginDto, Void.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void loginEmailNotExist() {
        LoginDto loginDto = new LoginDto("aa_new@aa.com", "password1");
        ResponseEntity<ErrorResult> responseEntity = template().postForEntity(LOGIN_URL, loginDto, ErrorResult.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(responseEntity.getBody().getErrors()).contains(new Error(DomainField.USER_EMAIL.getFieldName(), "존재하지 않는 아이디입니다."));
    }

    @Test
    public void loginEmailPasswordNotMatch() {
        LoginDto loginDto = new LoginDto("aa@aa.com", "password122");
        ResponseEntity<ErrorResult> responseEntity = template().postForEntity(LOGIN_URL, loginDto, ErrorResult.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(responseEntity.getBody().getErrors()).contains(new Error(DomainField.USER_PASSWORD.getFieldName(), "아이디와 비밀번호가 일치하지 않습니다."));
    }
}
