package codesquad.security;

import codesquad.dto.SessionUserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        SessionUserDTO sessionUserDTO = (SessionUserDTO) request.getSession().getAttribute(HttpSessionUtils.USER_SESSION_KEY);
        if(sessionUserDTO == null){
            response.sendRedirect("/login.html");
            return false;
        }
        if(!sessionUserDTO.isAdmin()){
            response.sendError(HttpStatus.FORBIDDEN.value());
            response.sendRedirect("/login.html");
            return false;
        }
        return true;
    }
}
