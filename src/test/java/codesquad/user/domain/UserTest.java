package codesquad.user.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserTest {
    public static final String RAW_PASSWORD = "123456qwerA";
    public static final String ENCODED_PASSWORD = "$2a$10$ClF9s9fpqOrGkmXWZ.awRuB0P.U0bCYjhVWrfiXGQElkERN7Rkoh6";

    public static User.UserBuilder userBuilder() {
        return User.builder()
                .email("tester@gmail.com")
                .password(ENCODED_PASSWORD)
                .phoneNumber("010-1234-5678")
                .name("김김김");
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
