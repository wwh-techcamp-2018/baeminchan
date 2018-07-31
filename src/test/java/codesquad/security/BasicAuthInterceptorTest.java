package codesquad.security;


import codesquad.domain.Member;
import codesquad.dto.MemberLoginDto;
import codesquad.service.MemberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.Base64;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BasicAuthInterceptorTest {

    @Mock
    private MemberService memberService;

    @InjectMocks
    private BasicAuthInterceptor basicAuthInterceptor;

    @Test
    public void preHandle_login_success() throws Exception {
        String email = "dain@woowahan.com";
        String password = "dain123";
        MockHttpServletRequest request = basicAuthHttpRequest(email, password);
        Member member = new Member(email, "dain123", "dain", "01012345678");
        when(memberService.login(new MemberLoginDto(email, password))).thenReturn(member);

        basicAuthInterceptor.preHandle(request, null, null);
        assertThat(request.getSession().getAttribute(HttpSessionUtils.MEMBER_SESSION_KEY)).isEqualTo(member);
    }

    private MockHttpServletRequest basicAuthHttpRequest(String userId, String password) {
        String encodedBasicAuth = Base64.getEncoder()
                .encodeToString(String.format("%s:%s", userId, password).getBytes());
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("Authorization", "Basic " + encodedBasicAuth);
        return request;
    }

}
