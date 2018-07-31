package codesquad.security;

import codesquad.controller.ApiCategoryController;
import codesquad.domain.User;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static codesquad.security.HttpSessionUtils.SESSIONED_USER;

public class AdminAuthInterceptor extends HandlerInterceptorAdapter {

    private static final Logger log = LoggerFactory.getLogger(AdminAuthInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        HttpSession session = request.getSession();
        User object = (User)session.getAttribute(SESSIONED_USER);

        log.info("Hello, {}", session);
        log.info("user, {}", object);
        return false;
    }
}
