package codesquad.user.auth;

import codesquad.user.domain.Role;
import codesquad.user.domain.User;
import codesquad.user.exception.UnAuthorizedException;
import codesquad.util.SessionUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AdminUserHandlerMethodArgumentResolverTest {

    @Mock
    private MethodParameter parameter;

    @Mock
    private NativeWebRequest request;

    private AdminUserHandlerMethodArgumentResolver adminUserResolver;

    @Before
    public void setUp() throws Exception {
        adminUserResolver = new AdminUserHandlerMethodArgumentResolver();
    }

    @Test
    public void resolveArgument_User_is_Admin() throws Exception {
        User sessionUser = User.builder().role(Role.ADMIN).build();
        when(request.getAttribute(SessionUtil.USER_SESSION_KEY, WebRequest.SCOPE_SESSION)).thenReturn(sessionUser);

        User adminUser = (User) adminUserResolver.resolveArgument(parameter, null, request, null);

        assertThat(adminUser).isEqualTo(sessionUser);
    }

    @Test(expected = UnAuthorizedException.class)
    public void resolveArgument_User_is_not_Admin() throws Exception {
        User sessionUser = User.builder().role(Role.USER).build();
        when(request.getAttribute(SessionUtil.USER_SESSION_KEY, WebRequest.SCOPE_SESSION)).thenReturn(sessionUser);

        adminUserResolver.resolveArgument(parameter, null, request, null);
    }

    @Test(expected = UnAuthorizedException.class)
    public void resolveArgument_not_login() throws Exception {
        adminUserResolver.resolveArgument(parameter, null, request, null);
    }

    @Test
    public void supportsParameter() {
        when(parameter.hasParameterAnnotation(AdminUser.class)).thenReturn(true);
        assertThat(adminUserResolver.supportsParameter(parameter)).isTrue();
    }

    @Test
    public void supportsParameterFalse() {
        when(parameter.hasParameterAnnotation(AdminUser.class)).thenReturn(false);
        assertThat(adminUserResolver.supportsParameter(parameter)).isFalse();
    }
}