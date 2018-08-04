package codesquad.security;

import codesquad.domain.User;
import codesquad.exception.UnAuthenticationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class AdminAuthInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        HttpSession session = request.getSession();
        User sessionUser = HttpSessionUtils.getUserFromSession(session);

        if (sessionUser == null || !sessionUser.isAdmin()) {
            throw new UnAuthenticationException("관리자만 접근할 수 있습니다.");
        }
        return true;
    }
}
