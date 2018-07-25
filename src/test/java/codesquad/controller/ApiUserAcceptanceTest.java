package codesquad.controller;

import codesquad.domain.UserTest;
import codesquad.dto.JoinUserDto;
import codesquad.dto.LoginUserDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import support.test.AcceptanceTest;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class ApiUserAcceptanceTest extends AcceptanceTest {

    @Test
    public void create() {
        JoinUserDto joinUserDto = UserTest.CYS;
        ResponseEntity<Void> response = template().postForEntity("/api/users", joinUserDto, Void.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        assertThat(findByEmail(joinUserDto.getEmail()).getEmail()).isEqualTo("chldbtjd2272@naver.com");
    }

    @Test
    public void login() {
        JoinUserDto joinUserDto = UserTest.CYS;
        ResponseEntity<Void> response = template().postForEntity("/api/users", joinUserDto, Void.class);

        LoginUserDto loginUserDto = new LoginUserDto("chldbtjd2272@naver.com","password!2");
        response = template().postForEntity("/api/users/login", loginUserDto, Void.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
