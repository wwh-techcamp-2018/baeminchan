package codesquad.security;

import codesquad.domain.Member;
import codesquad.dto.MemberDto;
import codesquad.support.Role;
import codesquad.support.SessionUtil;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;


public class AdminAuthInterceptorTest {
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private MockHttpSession session;
    private Member defaultMember;
    private Member adminMember;

    @Before
    public void setUp() {
        response = new MockHttpServletResponse();
        defaultMember = MemberDto.defaultMemberDto().toEntity();
        Member member = MemberDto.defaultMemberDto().toEntity();
        member.addRole(Role.ADMIN);
        adminMember = member;
    }

    @Test
    public void adminAuthInterceptorSuccessTest() throws IOException {
        request = new MockHttpServletRequest();
        session = new MockHttpSession();

        session.setAttribute(SessionUtil.USER_SESSION_KEY, adminMember);
        request.setSession(session);

        AdminAuthInterceptor adminAuthInterceptor = new AdminAuthInterceptor();
        Boolean result = adminAuthInterceptor.preHandle(request, response, null);
        assertThat(result).isEqualTo(true);
    }

    @Test
    public void adminAuthInterceptorFailTest() throws IOException {
        request = new MockHttpServletRequest();
        session = new MockHttpSession();

        session.setAttribute(SessionUtil.USER_SESSION_KEY, defaultMember);
        request.setSession(session);

        AdminAuthInterceptor adminAuthInterceptor = new AdminAuthInterceptor();
        Boolean result = adminAuthInterceptor.preHandle(request, response, null);
        assertThat(result).isEqualTo(false);

    }
}