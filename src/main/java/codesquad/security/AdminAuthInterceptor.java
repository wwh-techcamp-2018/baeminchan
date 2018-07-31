package codesquad.security;

import codesquad.domain.User;
import codesquad.exception.UnAuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminAuthInterceptor extends HandlerInterceptorAdapter {

    private static final Logger log = LoggerFactory.getLogger(AdminAuthInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        HttpSession session = request.getSession();
        User sessionUser = HttpSessionUtils.getUserFromSession(session);

        log.info("user, {}", sessionUser);
        if(sessionUser == null || !sessionUser.isAdmin()) {
            throw new UnAuthenticationException("관리자만 접근할 수 있습니다.");
        }
        return true;
    }
}
