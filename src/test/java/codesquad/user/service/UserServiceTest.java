package codesquad.user.service;

import codesquad.user.domain.Role;
import codesquad.user.domain.User;
import codesquad.user.domain.UserRepository;
import codesquad.user.domain.UserTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Mock
    private PasswordEncoder passwordEncoder;

    private User.UserBuilder userBuilder;

    @Before
    public void setUp() throws Exception {
        userBuilder = UserTest.userBuilder();
    }

    @Test
    public void create() {
        User user = userBuilder
                .name("tester")
                .phoneNumber("010-1234-5678")
                .role(Role.USER)
                .build();
        user = spy(user);

        userService.add(user);
        verify(user).encodePassword(passwordEncoder);
        verify(userRepository).save(user);
    }

    @Test
    public void login() {
        User user = userBuilder.build();

        user = spy(user);

        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        when(user.matchPassword(user, passwordEncoder)).thenReturn(true);
        userService.login(user);

        verify(user, times(2)).matchPassword(user, passwordEncoder);
    }
}
