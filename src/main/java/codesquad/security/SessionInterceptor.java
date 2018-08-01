package codesquad.security;

import codesquad.dto.SessionUserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class SessionInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        SessionUserDTO sessionUserDTO = (SessionUserDTO) request.getSession().getAttribute(HttpSessionUtils.USER_SESSION_KEY);
        if(sessionUserDTO == null){
            responseUnauth(response);
            return false;
        }
        if(!sessionUserDTO.isAdmin()){
            responseForbidden(response);
            return false;
        }
        return true;
    }

    public abstract void responseForbidden(HttpServletResponse response) throws IOException;
    public abstract void responseUnauth(HttpServletResponse response) throws IOException;
}
