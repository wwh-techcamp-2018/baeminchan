package codesquad.security;

import codesquad.support.util.SessionUtil;
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

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class BasicAuthInterceptorTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private BasicAuthInterceptor basicAuthInterceptor;


    @Test
    public void preHandle_로그인_성공() throws Exception {
        //Given
        User user = User.builder().name("javajigi").build();
//        LoginDto dto = new LoginDto();
        when(userService.login(new LoginDto("javajigi@slipp.net", "password1"))).thenReturn(user);

        //When
        String encodedBasicAuth = Base64.getEncoder()
                .encodeToString(String.format("%s:%s", "javajigi@slipp.net", "password1").getBytes());
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("Authorization", "Basic " + encodedBasicAuth);
        basicAuthInterceptor.preHandle(request, null, null);

        //Then
        assertTrue(SessionUtil.isLoginUser(request.getSession()));
    }
}