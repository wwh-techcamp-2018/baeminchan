package codesquad.security;

import codesquad.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminInterceptor extends HandlerInterceptorAdapter {
    private static final Logger log = LoggerFactory.getLogger(AdminInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();

        User loginUser = (User) session.getAttribute(HttpSessionUtils.USER_SESSION_KEY);
        if (loginUser == null) {
            response.sendRedirect("/login");
            return false;
        }
        if (!loginUser.isAdmin()) {
            request.getRequestDispatcher("/errors").forward(request, response);
            return false;
        }

        return true;
    }
}
