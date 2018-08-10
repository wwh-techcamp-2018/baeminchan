package codesquad.util;

import codesquad.domain.user.User;
import org.junit.Test;
import org.springframework.mock.web.MockHttpSession;

import static org.assertj.core.api.Assertions.assertThat;


public class SessionUtilTest {


    private MockHttpSession session;

    @Test
    public void getUserSession(){
        session = new MockHttpSession();
        assertThat(SessionUtil.getUserSession(session)).isEqualTo(User.GUEST_USER);
    }

}