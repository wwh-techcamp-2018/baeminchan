package codesquad.security;

import codesquad.domain.Member;
import codesquad.dto.MemberLoginDto;
import codesquad.service.MemberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.util.Base64;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AdminRoleInterceptorTest {

    @Mock
    private MemberService memberService;

    @InjectMocks
    private BasicAuthInterceptor basicAuthInterceptor;

    private AdminRoleInterceptor adminRoleInterceptor = new AdminRoleInterceptor();

    @Test
    public void preHandle_is_admin()throws Exception {
        String email = "admin@woowahan.com";
        String password = "admin123";
        MockHttpServletRequest request = basicAuthHttpRequest(email, password);
        Member admin = new Member(email, "admin321", "admin", "01012345678");
        admin.giveAdmin();
        when(memberService.login(new MemberLoginDto(email, password))).thenReturn(admin);

        basicAuthInterceptor.preHandle(request, null, null);
        assertThat(adminRoleInterceptor.preHandle(request, null, null)).isTrue();
    }

    @Test
    public void preHandle_is_not_admin() throws Exception{
        String email = "admin@woowahan.com";
        String password = "admin123";
        MockHttpServletRequest request = basicAuthHttpRequest(email, password);
        Member hacker = new Member(email, "admin321", "admin", "01012345678");
        when(memberService.login(new MemberLoginDto(email, password))).thenReturn(hacker);
        MockHttpServletResponse response = new MockHttpServletResponse();

        basicAuthInterceptor.preHandle(request, response, null);
        adminRoleInterceptor.preHandle(request, response, null);

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    private MockHttpServletRequest basicAuthHttpRequest(String userId, String password) {
        String encodedBasicAuth = Base64.getEncoder()
                .encodeToString(String.format("%s:%s", userId, password).getBytes());
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("Authorization", "Basic " + encodedBasicAuth);
        return request;
    }
}