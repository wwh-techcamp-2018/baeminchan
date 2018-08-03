package codesquad.security;

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
