package codesquad.web;

import codesquad.dto.LoginUserDTO;
import codesquad.exception.LoginFailedException;
import codesquad.repository.UserRepository;
import codesquad.service.UserService;
import codesquad.support.ApiAcceptanceTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiUserControllerTest extends ApiAcceptanceTest {

    @Test
    public void 로그인_성공() {
        LoginUserDTO loginUserDTO = new LoginUserDTO();
        loginUserDTO.setEmail("a@naver.com");
        loginUserDTO.setPassword("test1234");
        ResponseEntity<Void> response = template().postForEntity("/api/users/login",loginUserDTO,Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void 로그인_실패_존재하지않는_사용자(){
        LoginUserDTO loginUserDTO = new LoginUserDTO();
        loginUserDTO.setEmail("ab@naver.com");
        loginUserDTO.setPassword("test1234");
        ResponseEntity<Void> response = template().postForEntity("/api/users/login",loginUserDTO,Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void 로그인_실패_잘못된_비밀번호() {
        LoginUserDTO loginUserDTO = new LoginUserDTO();
        loginUserDTO.setEmail("a@naver.com");
        loginUserDTO.setPassword("test12345");
        ResponseEntity<Void> response = template().postForEntity("/api/users/login",loginUserDTO,Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
}