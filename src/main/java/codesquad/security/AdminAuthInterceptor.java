package codesquad.security;

import codesquad.exception.UnAuthenticationException;
import codesquad.exception.UnAuthorizedException;
import codesquad.domain.DomainField;
import codesquad.domain.user.User;
import codesquad.util.SessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminAuthInterceptor extends HandlerInterceptorAdapter {
    private static final Logger log = LoggerFactory.getLogger(AdminAuthInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = SessionUtil.getUserSession(request.getSession());
        if (user.isGuestUser())
            throw new UnAuthenticationException(DomainField.USER_EMAIL, "관리자 계정으로 로그인 해주세요.");
        if (!user.isAdmin())
            throw new UnAuthorizedException(DomainField.USER_EMAIL, "관리자 권한이 없습니다.");

        return true;
    }
}
