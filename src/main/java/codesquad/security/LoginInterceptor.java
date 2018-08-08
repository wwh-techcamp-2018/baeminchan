package codesquad.security;

import codesquad.domain.User;
import codesquad.service.UserService;
import codesquad.support.NotExistException;
import codesquad.util.SessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.Charset;
import java.util.Base64;

public class LoginInterceptor extends HandlerInterceptorAdapter {
    private static final Logger log = LoggerFactory.getLogger(LoginInterceptor.class);

    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.debug("LoginInterceptor preHandle() call");

        // request Header 로 부터 Athorization 에 대한 정보 가져오기
        String authorization = request.getHeader("Authorization");

        // Athorization 에 대한 정보가 유효한지 확인
        if(authorization == null || !authorization.startsWith("Basic")){
            return true;
        }

        String base64Credentials = authorization.substring("Basic".length()).trim();
        String credentials = new String(Base64.getDecoder().decode(base64Credentials), Charset.forName("UTF-8"));
        final String[] userInfo = credentials.split(":");

        try{
            User user = userService.login(userInfo[0], userInfo[1]);
            log.debug("loginUser : {}", user);
            request.getSession().setAttribute(SessionUtil.SESSION_KEY, user);
            return true;
        }catch(NotExistException exception){
            // Authentification
            return true;
        }
    }
}
