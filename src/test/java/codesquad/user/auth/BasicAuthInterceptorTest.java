package codesquad.user.auth;

import codesquad.user.domain.User;
import codesquad.user.service.UserService;
import codesquad.util.SessionUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.Base64;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BasicAuthInterceptorTest {
    @Mock
    private UserService userService;

    @InjectMocks
    private BasicAuthInterceptor basicAuthInterceptor;

    @Test
    public void preHandle_Login_Success() throws Exception {
        String email = "email@test.com";
        String password = "password123";
        MockHttpServletRequest request = basicAuthHttpRequest(email, password);
        User loginUser = User.builder().email(email).password(password).build();
        when(userService.login(loginUser)).thenReturn(loginUser);

        basicAuthInterceptor.preHandle(request, null, null);
        assertThat(request.getSession().getAttribute(SessionUtil.USER_SESSION_KEY), is(loginUser));
    }

    private MockHttpServletRequest basicAuthHttpRequest(String email, String password) {
        String encodedBasicAuth = Base64.getEncoder()
                .encodeToString(String.format("%s:%s", email, password).getBytes());
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("Authorization", "Basic " + encodedBasicAuth);
        return request;
    }
}
