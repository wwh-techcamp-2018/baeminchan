package codesquad.security;

import codesquad.dto.SessionUserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminInterceptor extends SessionInterceptor {
    @Override
    public void responseForbidden(HttpServletResponse response) throws IOException {
        response.sendRedirect("/");
    }

    @Override
    public void responseUnauth(HttpServletResponse response) throws IOException {
        response.sendRedirect("/login.html");
    }
}
