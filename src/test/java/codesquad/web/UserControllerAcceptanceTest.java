package codesquad.web;

import codesquad.domain.LoginDTO;
import codesquad.domain.UserDTO;
import codesquad.domain.ValidationError;
import codesquad.domain.ValidationErrorResponse;
import codesquad.service.UserService;
import codesquad.support.test.AcceptanceTest;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class UserControllerAcceptanceTest extends AcceptanceTest {
    
    private static final Logger log = LoggerFactory.getLogger(UserControllerAcceptanceTest.class);

    private final String SIGNUP_URL = "/users/signup";
    private final String LOGIN_URL = "/users/login";
    @Autowired
    PasswordEncoder passwordEncoder;

    private UserDTO user;


    @Test
    public void signup() throws Exception {
        user = new UserDTO("javajigi@tech.com", "12345678","12345678","javajigi","010-1234-5678");
        requestSuccessProcess(SIGNUP_URL,user);
    }

    @Test
    public void signupAssertPassword(){
        user = new UserDTO("javajigi@tech.com", "12345678","12345679","javajigi","010-1234-5678");
        requestFailProcess(SIGNUP_URL, user, Arrays.asList(
                UserService.FIELD_NAME_PASSWORD
        ));
    }

    @Test
    public void signupInvalidUserDTO() {
        user = new UserDTO("javajigitech.com", "123456","12345679","javajigi","010-1234-5678");
        ResponseEntity<ValidationErrorResponse> responseEntity = template().postForEntity("/users/signup", user,ValidationErrorResponse.class);
        requestFailProcess(SIGNUP_URL, user, Arrays.asList(
                UserService.FIELD_NAME_PASSWORD,
                UserService.FIELD_NAME_EMAIL
        ));
    }

    @Test
    public void duplicateSignup(){

        user = new UserDTO("intae@tech.com", "12345678","12345678","intae","010-1234-5678");
        requestFailProcess(SIGNUP_URL, user, Arrays.asList(
                UserService.FIELD_NAME_EMAIL
        ));
    }

    @Test
    public void login(){
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmail("intae@tech.com");
        loginDTO.setPassword("12345678");

        requestSuccessProcess(LOGIN_URL,loginDTO);
    }

    @Test
    public void loginValidationEmail() {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmail("something");
        loginDTO.setPassword("12345678");
        requestFailProcess(LOGIN_URL, loginDTO, Arrays.asList(
                UserService.FIELD_NAME_EMAIL
        ));

    }

    @Test
    public void loginValidationPassword() {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmail("intae@tech.com");
        loginDTO.setPassword("somethingwrong");
        requestFailProcess(LOGIN_URL, loginDTO, Arrays.asList(
                UserService.FIELD_NAME_PASSWORD
        ));
    }


    private void requestFailProcess(String url, Object body, List<String> fieldNames) {
        ResponseEntity<ValidationErrorResponse> responseEntity = template().postForEntity(url, body,ValidationErrorResponse.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(responseEntity.getBody().getErrors().size()).isEqualTo(fieldNames.size());

        Set<String> fieldNameSet = new HashSet<>();
        for(ValidationError validationError : responseEntity.getBody().getErrors()) {
            fieldNameSet.add(validationError.getFieldName());
            log.debug("errorMessage : {}", validationError.getErrorMessage());
        }
        for(String fieldName : fieldNames){
            assertThat(fieldNameSet.contains(fieldName)).isEqualTo(true);
        }
    }

    private void requestSuccessProcess(String url, Object body){
        ResponseEntity<Void> responseEntity = template().postForEntity(url, body, Void.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    private void logErrorsInfo(List<ValidationError> errors){
        for(ValidationError error : errors) {
            log.debug("field name : {}", error.getFieldName());
            log.debug("error message : {}", error.getErrorMessage());
        }
    }

}