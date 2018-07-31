package codesquad.security;

import codesquad.exception.ForbiddenException;
import codesquad.exception.UnauthenticatedException;
import codesquad.support.util.SessionUtil;
import codesquad.user.domain.Role;
import codesquad.user.domain.User;
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

@RunWith(MockitoJUnitRunner.Silent.class)
public class LoginUserHandlerMethodArgumentResolverTest {

    @Mock
    private MethodParameter parameter;

    @Mock
    private NativeWebRequest request;

    @Mock
    private LoginUser loginUserAnnotation;

    private LoginUserHandlerMethodArgumentResolver resolver;
    private User user;
    private User adminUser;

    @Before
    public void setup() {
        resolver = new LoginUserHandlerMethodArgumentResolver();
        user = User.builder().name("javajigi").role(Role.USER).build();
        adminUser = User.builder().name("javajigi").role(Role.ADMIN).build();
        when(parameter.getParameterAnnotation(LoginUser.class)).thenReturn(loginUserAnnotation);
    }

    @Test(expected = UnauthenticatedException.class)
    public void not_login_user() throws Exception {
        when(request.getAttribute(SessionUtil.USER_SESSION_KEY, WebRequest.SCOPE_SESSION)).thenReturn(null);

        resolver.resolveArgument(parameter, null, request, null);
    }

    @Test
    public void login_user() throws Exception {
        when(loginUserAnnotation.adminRequired()).thenReturn(false);
        when(request.getAttribute(SessionUtil.USER_SESSION_KEY, WebRequest.SCOPE_SESSION)).thenReturn(user);

        assertThat(((User) resolver.resolveArgument(parameter, null, request, null)).getName())
                .isEqualTo(user.getName());
    }

    @Test(expected = ForbiddenException.class)
    public void login_user_admin_required() throws Exception {
        when(loginUserAnnotation.adminRequired()).thenReturn(true);
        when(request.getAttribute(SessionUtil.USER_SESSION_KEY, WebRequest.SCOPE_SESSION)).thenReturn(user);

        resolver.resolveArgument(parameter, null, request, null);
    }


    @Test
    public void login_admin() throws Exception {
        when(loginUserAnnotation.adminRequired()).thenReturn(true);
        when(request.getAttribute(SessionUtil.USER_SESSION_KEY, WebRequest.SCOPE_SESSION)).thenReturn(adminUser);

        assertThat(((User) resolver.resolveArgument(parameter, null, request, null)).getRole())
                .isEqualTo(Role.ADMIN);
    }

    @Test
    public void login_admin_not_required() throws Exception {
        when(loginUserAnnotation.adminRequired()).thenReturn(false);
        when(request.getAttribute(SessionUtil.USER_SESSION_KEY, WebRequest.SCOPE_SESSION)).thenReturn(adminUser);

        assertThat(((User) resolver.resolveArgument(parameter, null, request, null)).getRole())
                .isEqualTo(Role.ADMIN);
    }
}