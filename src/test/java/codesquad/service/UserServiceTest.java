package codesquad.service;

import codesquad.exception.BadRequestException;
import codesquad.domain.User;
import codesquad.domain.UserRepository;
import codesquad.dto.LoginDto;
import codesquad.dto.SignupDto;
import codesquad.exception.UnAuthenticationException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    private static final Logger log = LoggerFactory.getLogger(UserServiceTest.class);

    private SignupDto signupDto;
    private LoginDto loginDto;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Before
    public void setUp() throws Exception {
        userService.setPasswordEncoder(passwordEncoder);
        signupDto = new SignupDto();
        signupDto.setEmail("email@aa");
        signupDto.setPassword("password");
        signupDto.setPasswordConfirm("password");
        signupDto.setName("name");
        signupDto.setPhoneNumber("00-00-00");

        loginDto = new LoginDto("email@aa", "password");
    }

    @Test(expected = BadRequestException.class)
    public void signupPasswordDifferentException() {
        User user = User.of(signupDto, passwordEncoder);
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        signupDto.setPassword("passworddiff");
        userService.signup(signupDto);
    }

    @Test(expected = BadRequestException.class)
    public void signupEmailExistException() {
        User user = User.of(signupDto, passwordEncoder);
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        userService.signup(signupDto);
    }

    @Test
    public void login() {
        User user = User.of(signupDto, passwordEncoder);
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        userService.login(loginDto);
    }

    @Test(expected = UnAuthenticationException.class)
    public void loginEmailNotExist() {
        User user = User.of(signupDto, passwordEncoder);
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.empty());
        userService.login(loginDto);
    }

    @Test(expected = BadRequestException.class)
    public void loginPasswordNotMatch() {
        User user = User.of(signupDto, passwordEncoder);
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        loginDto.setPassword("passworddiff");
        userService.login(loginDto);
    }

}