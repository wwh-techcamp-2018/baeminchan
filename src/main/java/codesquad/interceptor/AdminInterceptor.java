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
        User user = SessionUtil.getSessonUser(request);
        if (user == null || !user.isAdmin()) {
            response.sendRedirect("/users/login");
            return false;
        }
        return true;
    }

}