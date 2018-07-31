package codesquad.user.security;

import codesquad.exception.UnAuthorizedException;
import codesquad.user.domain.User;
import codesquad.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class AdminAuthInterceptor extends HandlerInterceptorAdapter {
    private static final Logger log = LoggerFactory.getLogger(AdminAuthInterceptor.class);

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        User user = HttpSessionUtils.getUserFromSession(request.getSession());

        if (user == null) {
            throw new UnAuthorizedException();
        }

        if (!user.isAdmin()) {
            throw new UnAuthorizedException();
        }
        return true;
    }
}
