package codesquad.security;

import codesquad.support.util.SessionUtil;
import codesquad.user.domain.User;
import codesquad.user.dto.LoginDto;
import codesquad.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.Charset;
import java.util.Base64;

@Slf4j
public class BasicAuthInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String authorizationHeader = request.getHeader("Authorization");
        log.debug("Authorization : {}", authorizationHeader);
        if (authorizationHeader == null || !authorizationHeader.startsWith("Basic")) {
            return true;
        }

        String base64Credentials = authorizationHeader.substring("Basic".length()).trim();
        String credentials = new String(Base64.getDecoder().decode(base64Credentials), Charset.forName("UTF-8"));

        final String[] values = credentials.split(":", 2);
        log.debug("email: {}", values[0]);
        log.debug("password: {}", values[1]);

        try {
            User user = userService.login(new LoginDto(values[0], values[1]));
            log.debug("login succeed: {}", user);
            SessionUtil.setUserInSession(request.getSession(), user);
            return true;
        } catch (Exception e) {
            return true;
        }
    }
}
