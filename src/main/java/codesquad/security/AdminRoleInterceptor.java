package codesquad.security;

import codesquad.domain.Member;
import codesquad.exception.ErrorDetails;
import codesquad.exception.UnAuthenticationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class AdminRoleInterceptor extends HandlerInterceptorAdapter {
    private static final Logger log = LoggerFactory.getLogger(BasicAuthInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Member member = (Member) request.getSession().getAttribute(HttpSessionUtils.MEMBER_SESSION_KEY);
        try {
            log.debug("Login Member Account : {}", member.getUsername());
            if (member == null) {
                throw new UnAuthenticationException("로그인 하지 않았습니다.");
            }
            if (!member.isAdmin()) {
                throw new UnAuthenticationException("관리자 권한이 없습니다.");
            }
            return true;
        } catch (UnAuthenticationException e) {
            log.debug("interceptor 걸렸네: " + e.getMessage());
            addErrorDetailToResponse(response, e.getMessage());
            return false;
        }
    }

    private void addErrorDetailToResponse(HttpServletResponse response, String errorMessage) throws IOException {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), errorMessage);
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        String json = new ObjectMapper().writeValueAsString(errorDetails);
        response.getWriter().write(json);
        response.flushBuffer();
    }
}
