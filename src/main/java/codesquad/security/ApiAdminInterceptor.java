package codesquad.security;

import codesquad.dto.SessionUserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class ApiAdminInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        SessionUserDTO sessionUserDTO = (SessionUserDTO) request.getSession().getAttribute(HttpSessionUtils.USER_SESSION_KEY);
        if(sessionUserDTO == null){
            response.sendError(HttpStatus.UNAUTHORIZED.value());
            return false;
        }
        if(!sessionUserDTO.isAdmin()){
            response.sendError(HttpStatus.FORBIDDEN.value());
            return false;
        }
        return true;
    }
}
