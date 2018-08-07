package codesquad.interceptor;

import codesquad.enums.Authority;
import codesquad.domain.Role;
import codesquad.domain.User;
import codesquad.security.HttpSessionUtils;
import codesquad.security.AdminInterceptor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class AdminInterceptorTest {
    private static final Logger log = LoggerFactory.getLogger(AdminInterceptorTest.class);

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @InjectMocks
    private AdminInterceptor adminInterceptor;

    @Before
    public void setUp() throws Exception {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    @Test
    public void 비로그인_사용자_admin_접근() throws Exception {
        assertThat(adminInterceptor.preHandle(request, response, null)).isFalse();
        assertThat(request.getSession().getAttribute(HttpSessionUtils.USER_SESSION_KEY)).isNull();
        assertThat(response.getHeader("Location")).isEqualTo("/login");
    }

    @Test
    public void 로그인_일반_사용자_admin_접근() throws Exception {
        User normalUser = new User("javajigi@naver.com", "1234qwer!", "자바지기", "010-0101-0101", new Role(Authority.NORMAL));

        MockHttpSession httpSession = new MockHttpSession();
        httpSession.setAttribute(HttpSessionUtils.USER_SESSION_KEY, normalUser);
        request.setSession(httpSession);

        assertThat(adminInterceptor.preHandle(request, response, null)).isFalse();
        assertThat(request.getSession().getAttribute(HttpSessionUtils.USER_SESSION_KEY)).isEqualTo(normalUser);
        assertThat(response.getForwardedUrl()).isEqualTo("/errors");
    }
}
