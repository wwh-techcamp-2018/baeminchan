package codesquad.dto;

import codesquad.exception.ExistedEmailException;
import codesquad.repository.UserRepository;
import codesquad.service.UserService;
import org.hibernate.mapping.Join;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.*;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class JoinUserDTOTest extends DTOTest<JoinUserDTO>{
    private JoinUserDTO joinUserDTO;

    @Before
    public void setUp() throws Exception {
        joinUserDTO = new JoinUserDTO();
        joinUserDTO.setEmail("c@naver.com");
        joinUserDTO.setPassword("test12345");
        joinUserDTO.setPasswordConfirm("test12345");
        joinUserDTO.setName("포비사랑");
        joinUserDTO.setPhoneNo("010-000-0000");
    }

    @Test
    public void 회원가입_성공() {
        assertThat(isValid(joinUserDTO)).isTrue();
    }

    @Test
    public void 회원가입_이메일_형식() {
        joinUserDTO.setEmail("a@naver");
        assertThat(isValid(joinUserDTO)).isFalse();
    }

    @Test
    public void 회원가입_비밀번호_형식_길이짧음() {
        joinUserDTO.setPassword("test145");
        assertThat(isValid(joinUserDTO)).isFalse();
    }

    @Test
    public void 회원가입_비밀번호_형식_숫자만() {
        joinUserDTO.setPassword("11111111111");
        assertThat(isValid(joinUserDTO)).isFalse();
    }

    @Test
    public void 회원가입_비밀번호_형식_문자만() {
        joinUserDTO.setPassword("ttttttttttt");
        assertThat(isValid(joinUserDTO)).isFalse();
    }

    @Test
    public void 회원가입_비밀번호_확인_다름() {
        joinUserDTO.setPassword("test1451");
        joinUserDTO.setPasswordConfirm("test14513");
        assertThat(isValid(joinUserDTO)).isFalse();
    }

    @Test
    public void 회원가입_전화번호_형식_앞자리() {
        joinUserDTO.setPhoneNo("02-000-0000");
        assertThat(isValid(joinUserDTO)).isFalse();
    }

    @Test
    public void 회원가입_전화번호_형식_숫자아닌_값() {
        joinUserDTO.setPhoneNo("010-a00-0000");
        assertThat(isValid(joinUserDTO)).isFalse();
    }
}