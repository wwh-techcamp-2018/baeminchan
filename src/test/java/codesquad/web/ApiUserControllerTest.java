package codesquad.web;

import codesquad.dto.JoinUserDTO;
import codesquad.dto.LoginUserDTO;
import codesquad.support.ApiAcceptanceTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiUserControllerTest extends ApiAcceptanceTest {
    private LoginUserDTO loginUserDTO;
    private JoinUserDTO joinUserDTO;

    @Before
    public void setUp() throws Exception {
        loginUserDTO = new LoginUserDTO();
        loginUserDTO.setEmail("a@naver.com");
        loginUserDTO.setPassword("test1234");

        joinUserDTO = new JoinUserDTO();
        joinUserDTO.setEmail("c@naver.com");
        joinUserDTO.setPassword("test12345");
        joinUserDTO.setPasswordConfirm("test12345");
        joinUserDTO.setName("포비사랑");
        joinUserDTO.setPhoneNo("010-000-0000");
    }

    @Test
    public void 로그인_성공() {
        ResponseEntity<Void> response = template().postForEntity("/api/users/login", loginUserDTO, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void 로그인_실패_존재하지않는_사용자() {
        loginUserDTO.setEmail("ab@naver.com");
        ResponseEntity<Void> response = template().postForEntity("/api/users/login", loginUserDTO, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void 로그인_실패_잘못된_비밀번호() {
        loginUserDTO.setPassword("test12345");
        ResponseEntity<Void> response = template().postForEntity("/api/users/login", loginUserDTO, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void 회원가입_성공() {
        ResponseEntity<Void> response = template().postForEntity("/api/users", joinUserDTO, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void 회원가입_이메일_형식() {
        joinUserDTO.setEmail("a@naver");
        ResponseEntity<Void> response = template().postForEntity("/api/users", joinUserDTO, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void 회원가입_이미가입된_이메일() {
        joinUserDTO.setEmail("a@naver.com");
        ResponseEntity<Void> response = template().postForEntity("/api/users", joinUserDTO, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void 회원가입_비밀번호_형식_길이짧음() {
        joinUserDTO.setPassword("test145");
        ResponseEntity<Void> response = template().postForEntity("/api/users", joinUserDTO, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void 회원가입_비밀번호_형식_숫자만() {
        joinUserDTO.setPassword("11111111111");
        ResponseEntity<Void> response = template().postForEntity("/api/users", joinUserDTO, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void 회원가입_비밀번호_형식_문자만() {
        joinUserDTO.setPassword("ttttttttttt");
        ResponseEntity<Void> response = template().postForEntity("/api/users", joinUserDTO, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void 회원가입_비밀번호_확인_다름() {
        joinUserDTO.setPassword("test1451");
        joinUserDTO.setPasswordConfirm("test14513");
        ResponseEntity<Void> response = template().postForEntity("/api/users", joinUserDTO, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void 회원가입_전화번호_형식_앞자리() {
        joinUserDTO.setPhoneNo("02-000-0000");
        ResponseEntity<Void> response = template().postForEntity("/api/users", joinUserDTO, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void 회원가입_전화번호_형식_숫자아닌_값() {
        joinUserDTO.setPhoneNo("010-a00-0000");
        ResponseEntity<Void> response = template().postForEntity("/api/users", joinUserDTO, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
}