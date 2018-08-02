package codesquad.user.security;

import codesquad.user.domain.User;
import codesquad.user.dto.LoginDto;
import codesquad.user.service.UserService;
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
    public void preHandle_로그인_성공() throws Exception {
        String email = "javajigi@gmail.com";
        String password = "haha123!";
        MockHttpServletRequest request = basicAuthHttpRequest(email, password);

        LoginDto mayBeUser = LoginDto.builder()
                .email(email)
                .password(password)
                .build();

        User loginUser = User.builder()
                .id(2L)
                .email("javajigi@gmail.com")
                .password("$2a$10$mz6I2CR3WFud4wYpjdGoIeizrttQPlvO5wb0B/9N0A7kgj8SWNpMa")
                .name("자바지기")
                .phone("010-8545-6858")
                .deleted(false)
                .build();

        when(userService.login(mayBeUser)).thenReturn(loginUser);

        basicAuthInterceptor.preHandle(request, null, null);
        assertThat(request.getSession().getAttribute(HttpSessionUtils.USER_SESSION_KEY), is(loginUser));
    }

    private MockHttpServletRequest basicAuthHttpRequest(String email, String password) {
        String encodedBasicAuth = Base64.getEncoder()
                .encodeToString(String.format("%s:%s", email, password).getBytes());
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("Authorization", "Basic " + encodedBasicAuth);
        return request;
    }
}