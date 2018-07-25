package codesquad.domain;

import org.junit.Before;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class UserDTOTest {


    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    UserDTO user;

    @Before
    public void setUp() throws Exception {
        user = new UserDTO("javajigi@tech.com", "12345678","12345678","javajigi","010-1234-5678");
    }

    @Test
    public void passwordConfirm() {
        assertThat(user.passwordConfirm(), is(true));
        user.setPasswordConfirm("1234");
        assertThat(user.passwordConfirm(), is(false));
    }
}