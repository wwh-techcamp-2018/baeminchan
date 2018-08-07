package codesquad.user.security;

import codesquad.exception.UnAuthenticationException;
import codesquad.user.domain.User;
import codesquad.user.dto.LoginDto;
import codesquad.user.service.UserService;
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
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String authorization = request.getHeader("Authorization");
        log.info("Authorization : {}", authorization);
        if (authorization == null || !authorization.startsWith("Basic")) {
            return true;
        }

        String base64Credentials = authorization.substring("Basic".length()).trim();
        String credentials = new String(Base64.getDecoder().decode(base64Credentials), Charset.forName("UTF-8"));
        final String[] values = credentials.split(":", 2);
        log.info("username : {}", values[0]);
        log.info("password : {}", values[1]);

        try {
            LoginDto mayBeUser = LoginDto.builder()
                    .email(values[0])
                    .password(values[1])
                    .build();

            User user = userService.login(mayBeUser);
            log.info("Login Success : {}", user);
            request.getSession().setAttribute(UserService.USER_SESSION_KEY, user.getId());
            return true;
        } catch (UnAuthenticationException e) {
            return true;
        }
    }
}
