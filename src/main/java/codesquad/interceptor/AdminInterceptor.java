package codesquad.interceptor;

import codesquad.domain.User;
import codesquad.domain.UserAuthority;
import codesquad.util.SessionUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //# 관리자 권한을 가진 사용자만 접근하도록 구현한다.
        //1. 로그인 세션이 유지되었는지 확인 -> 가지고 있지 않다면?
        User user = (User) request.getSession().getAttribute(SessionUtil.SESSION_KEY);
        if(user == null || user.getUserAuthority() != UserAuthority.ADMIN) {
            response.sendRedirect("/users/login");
            return false;
        }

        //2. 로그인 한 사람이 Admin 권한을 가지고 있는지 확인 -> 가지고 있지 않다면?

        return false;
    }

}