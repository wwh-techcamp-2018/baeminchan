package codesquad.service;

import codesquad.domain.LoginDTO;
import codesquad.domain.User;
import codesquad.exception.UserVerificationException;
import codesquad.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private User user;

    private LoginDTO loginDTO;

    @InjectMocks
    private UserService userService;

    @Before
    public void setUp() throws Exception {
        loginDTO = new LoginDTO();
        user = new User(1L, "javajigi@tech.com", "12345678", "javajigi", "010-1234-5678");
    }

    @Test(expected = UserVerificationException.class)
    public void isUniqueUser() {
        when(userRepository.findByEmail("javajigi@tech.com")).thenReturn(Optional.ofNullable(user));
        userService.isUniqueUser(user.getEmail());
    }


    @Test(expected = UserVerificationException.class)
    public void loginNotSignup() {
        when(userRepository.findByEmail(any())).thenReturn(Optional.empty());

        loginDTO.setEmail(user.getEmail());
        loginDTO.setPassword("123456");
        userService.login(loginDTO);
    }

    @Test(expected = UserVerificationException.class)
    public void notMatchPassword() {
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.ofNullable(user));
        when(passwordEncoder.matches("123456", user.getPassword())).thenReturn(false);

        loginDTO.setEmail(user.getEmail());
        loginDTO.setPassword("123456");
        userService.login(loginDTO);
    }

}