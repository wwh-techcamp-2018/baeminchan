package codesquad.user.auth;

import codesquad.user.domain.User;
import codesquad.user.dto.UserLoginDto;
import codesquad.user.exception.UnAuthenticationException;
import codesquad.user.service.UserService;
import codesquad.util.SessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.Charset;
import java.util.Base64;

public class BasicAuthInterceptor extends HandlerInterceptorAdapter {
    private static final Logger log = LoggerFactory.getLogger(BasicAuthInterceptor.class);

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String auth = request.getHeader("Authorization");
        if (isBasicAuthHeader(auth)) {
            return true;
        }

        String base64Credentials = auth.substring("Basic".length()).trim();
        String credentials = new String(Base64.getDecoder().decode(base64Credentials), Charset.forName("UTF-8"));
        final String[] values = credentials.split(":", 2);
        UserLoginDto dto = UserLoginDto.builder()
                .email(values[0])
                .password(values[1])
                .build();

        try {
            User user = userService.login(dto.toEntity());
            log.debug("Login Success : {}", user);
            request.getSession().setAttribute(SessionUtil.USER_SESSION_KEY, user);
            return true;
        } catch (UnAuthenticationException e) {
            return true;
        }
    }

    private boolean isBasicAuthHeader(String auth) {
        return auth == null || !auth.startsWith("Basic");
    }
}
