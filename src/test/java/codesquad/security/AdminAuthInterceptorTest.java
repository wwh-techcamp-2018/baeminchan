package codesquad.security;

import codesquad.domain.user.User;
import codesquad.domain.user.UserPermissions;
import codesquad.exception.UnAuthenticationException;
import codesquad.exception.UnAuthorizedException;
import codesquad.util.SessionUtil;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;

import static org.assertj.core.api.Assertions.assertThat;


public class AdminAuthInterceptorTest {

    private AdminAuthInterceptor adminAuthInterceptor;
    private MockHttpServletRequest request;
    private MockHttpSession session;

    @Before
    public void setUp() throws Exception {
        adminAuthInterceptor = new AdminAuthInterceptor();
        request = new MockHttpServletRequest();
        session = new MockHttpSession();
        request.setSession(session);

    }

    @Test
    public void preHandle() throws Exception {
        User user = User.builder()
                .email("admin@admin.com")
                .password("12345678")
                .name("admin")
                .phoneNumber("010-1234-5678")
                .permissions(UserPermissions.ADMIN)
                .build();

        SessionUtil.setUserSession(session, user);
        assertThat(adminAuthInterceptor.preHandle(request,null,null)).isEqualTo(true);
    }

    @Test(expected = UnAuthorizedException.class)
    public void preHandleInValidationByNormalUser() throws Exception {
        User user = User.builder()
                .email("normal@normal.com")
                .password("12345678")
                .name("normal")
                .phoneNumber("010-1234-5678")
                .permissions(UserPermissions.NORMAL)
                .build();

        SessionUtil.setUserSession(session, user);
        assertThat(adminAuthInterceptor.preHandle(request,null,null)).isEqualTo(true);
    }

    @Test(expected = UnAuthenticationException.class)
    public void preHandleInValidationByGuestUser() throws Exception {
        SessionUtil.setUserSession(session, User.GUEST_USER);
        assertThat(adminAuthInterceptor.preHandle(request,null,null)).isEqualTo(true);
    }
}