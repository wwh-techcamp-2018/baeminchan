package codesquad.security;

import codesquad.exception.UnauthorizedException;
import codesquad.util.SessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminAuthInterceptor extends HandlerInterceptorAdapter {

    private static final Logger log = LoggerFactory.getLogger(AdminAuthInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        log.debug("Request url : {}", request.getRequestURL());

        HttpSession session = request.getSession();

        if(!(SessionUtil.isLoginUser(session) && SessionUtil.isAdminUser(session))) throw new UnauthorizedException();

        return true;
    }
}
