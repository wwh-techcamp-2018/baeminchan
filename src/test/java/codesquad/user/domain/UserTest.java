package codesquad.user.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserTest {
    public static final String PASSWORD = "123456qwerA";

    public static User.UserBuilder userBuilder() {
        return User.builder()
                .email("tester@gmail.com")
                .password(PASSWORD);
    }

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    public void matchPassword() {
        User dbUser = userBuilder().build();
        User loginUser = userBuilder().build();
        dbUser.matchPassword(loginUser, passwordEncoder);
        verify(passwordEncoder).matches(dbUser.getPassword(), loginUser.getPassword());
    }
}
