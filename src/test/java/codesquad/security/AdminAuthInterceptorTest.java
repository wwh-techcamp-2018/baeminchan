package codesquad.security;

import codesquad.domain.User;
import codesquad.domain.UserPermissions;
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
        User user = new User("admin@admin.com"
                ,"12345678"
                ,"admin"
                ,"010-1234-5678"
                ,UserPermissions.ADMIN);
        SessionUtil.setUserSession(session, user);
        assertThat(adminAuthInterceptor.preHandle(request,null,null)).isEqualTo(true);
    }

    @Test(expected = UnAuthorizedException.class)
    public void preHandleInValidationByNormalUser() throws Exception {
        User user = new User("normal@normal.com"
                ,"12345678"
                ,"normal"
                ,"010-1234-5678"
                ,UserPermissions.NORMAL);
        SessionUtil.setUserSession(session, user);
        assertThat(adminAuthInterceptor.preHandle(request,null,null)).isEqualTo(true);
    }

    @Test(expected = UnAuthenticationException.class)
    public void preHandleInValidationByGuestUser() throws Exception {
        SessionUtil.setUserSession(session, User.GUEST_USER);
        assertThat(adminAuthInterceptor.preHandle(request,null,null)).isEqualTo(true);
    }
}