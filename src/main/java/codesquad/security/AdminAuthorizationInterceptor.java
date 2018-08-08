package codesquad.security;

import codesquad.domain.User;
import codesquad.service.UserService;
import codesquad.util.SessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminAuthorizationInterceptor extends HandlerInterceptorAdapter {
    private static final Logger log = LoggerFactory.getLogger(AdminAuthorizationInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.debug("Admin Interceptor preHandle() call");

        User admin = (User)request.getSession().getAttribute(SessionUtil.SESSION_KEY);
        if(admin.isAdmin()){
            return true;
        }
        throw new AuthorizationServiceException("권한이 없습니다.");
    }


}
