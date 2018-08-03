package codesquad.security;

import codesquad.domain.Authority;
import codesquad.domain.Role;
import codesquad.domain.User;
import codesquad.exception.UnAuthenticationException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;

import java.time.LocalDateTime;

import static codesquad.security.HttpSessionUtils.SESSIONED_USER;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class AdminAuthInterceptorTest {
    private AdminAuthInterceptor adminAuthInterceptor = new AdminAuthInterceptor();

    private User normalUser;
    private User adminUser;
    private MockHttpServletRequest request;
    private MockHttpSession session;
    private MockHttpServletResponse response;

    @Before
    public void setUp() throws Exception {
        normalUser = new User("serverwizard@naver.com", "test33##", "홍종완", "010-4681-5431", new Role(Authority.NORMAL), LocalDateTime.now());
        adminUser = new User("adminwizard@naver.com", "test33##", "홍징완", "010-4681-5431", new Role(Authority.ADMIN), LocalDateTime.now());
        request = new MockHttpServletRequest();
        session = new MockHttpSession();
        response = new MockHttpServletResponse();
    }

    @Test(expected = UnAuthenticationException.class)
    public void preHandle_관리자_권한인지_판단_일반사용자_접근_테스트() {
        session.setAttribute(SESSIONED_USER, normalUser);
        request.setSession(session);

        adminAuthInterceptor.preHandle(request, response, new Object());
    }

    @Test
    public void preHandle_관리자_권한인지_판단_관리자_접근_테스트() {
        session.setAttribute(SESSIONED_USER, adminUser);
        request.setSession(session);

        assertThat(adminAuthInterceptor.preHandle(request, response, new Object())).isTrue();
    }
}
