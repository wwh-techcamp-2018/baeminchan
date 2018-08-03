package codesquad.security;

import codesquad.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import support.test.AcceptanceTest;

import java.util.Base64;

import static org.assertj.core.api.Assertions.assertThat;

//TODO
@Slf4j
public class BasicAuthInterceptorTest extends AcceptanceTest {
//    @Mock
//    private UserService userService;

    // 가짜도 진짜가
    @Autowired
    private BasicAuthInterceptor basicAuthInterceptor;

    @Test
    public void preHandle_테스트() throws Exception {
        String encodedBasicAuth = Base64.getEncoder()
                .encodeToString(String.format("%s:%s", "serverwizard@naver.com", "test33##").getBytes());
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("Authorization", String.format("Basic %s", encodedBasicAuth));

        assertThat(basicAuthInterceptor.preHandle(request, null, null)).isTrue();
    }
}
