package codesquad.user.service;

import codesquad.exception.ConflictException;
import codesquad.user.domain.User;
import codesquad.user.domain.UserRepository;
import codesquad.user.dto.SignupDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    private SignupDto dto;

    @Before
    public void setUp() {
        dto = new SignupDto("email@email.com", "password1234", "password1234", "윤찬명", "010-5114-6224");
    }

    @Test
    public void 회원가입_성공() {
        when(passwordEncoder.encode(anyString())).thenReturn("testPassword");
        User user = dto.toEntity(passwordEncoder);

        when(userRepository.save(user)).thenReturn(user);
        assertThat(userService.signup(dto)).isEqualTo(user);
    }

    @Test(expected = ConflictException.class)
    public void 회원가입_이메일_중복() {
        when(userRepository.existsByEmail(anyString())).thenReturn(true);

        dto.setEmail("sanjigi@slipp.net");
        userService.signup(dto);
    }

    @Test(expected = ConflictException.class)
    public void 회원가입_전화번호_중복() {
        when(userRepository.existsByPhoneNumber(anyString())).thenReturn(true);

        dto.setPhoneNumber("010-1111-1111");
        userService.signup(dto);
    }
}
