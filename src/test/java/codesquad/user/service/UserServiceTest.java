package codesquad.user.service;

import codesquad.user.domain.Role;
import codesquad.user.domain.User;
import codesquad.user.domain.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Spy
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Test
    public void create() {
        String password = "password";
        User user = User.builder()
                .email("tester@gmail.com")
                .password(password)
                .name("tester")
                .phoneNumber("010-1234-5678")
                .role(Role.USER)
                .build();

        userService.add(spy(user));
        verify(user).encodePassword(passwordEncoder);
        verify(userRepository).save(user);
    }
}
