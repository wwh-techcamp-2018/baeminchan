package codesquad.user.service;

import codesquad.exception.ConflictException;
import codesquad.exception.UnauthenticatedException;
import codesquad.user.domain.User;
import codesquad.user.domain.UserRepository;
import codesquad.user.dto.LoginDto;
import codesquad.user.dto.SignupDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    private SignupDto signupDto;
    private LoginDto loginDto;

    @Before
    public void setUp() {
        signupDto = new SignupDto("email@email.com", "password1234", "password1234", "윤찬명", "010-5114-6224");
        loginDto = new LoginDto("sanjigi@slipp.net", "password2");
    }

    @Test
    public void 회원가입_성공() {
        when(passwordEncoder.encode(anyString())).thenReturn("testPassword");
        User user = signupDto.toEntity(passwordEncoder);

        when(userRepository.save(user)).thenReturn(user);
        assertThat(userService.signup(signupDto)).isEqualTo(user);
    }

    @Test(expected = ConflictException.class)
    public void 회원가입_이메일_중복() {
        when(userRepository.existsByEmail(anyString())).thenReturn(true);

        signupDto.setEmail("sanjigi@slipp.net");
        userService.signup(signupDto);
    }

    @Test(expected = ConflictException.class)
    public void 회원가입_전화번호_중복() {
        when(userRepository.existsByPhoneNumber(anyString())).thenReturn(true);

        signupDto.setPhoneNumber("010-1111-1111");
        userService.signup(signupDto);
    }

    @Test(expected = UnauthenticatedException.class)
    public void 로그인_존재하지_않는_이메일() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        userService.login(loginDto);
    }

    @Test(expected = UnauthenticatedException.class)
    public void 로그인_비밀번호_불일치() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(new User()));
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(false);
        userService.login(loginDto);
    }
}
