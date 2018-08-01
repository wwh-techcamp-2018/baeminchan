package codesquad.security;

import codesquad.domain.Member;
import codesquad.dto.CustomResponse;
import codesquad.support.SessionUtil;
import codesquad.support.exception.UnAuthenticationException;
import codesquad.support.exception.UnAuthorizedException;
import codesquad.validation.ValidationMessageUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminAuthInterceptor extends HandlerInterceptorAdapter {
    private static final Logger log = LoggerFactory.getLogger(AdminAuthInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        try {
            Member member = SessionUtil.getMember(request.getSession())
                    .orElseThrow(() -> new UnAuthenticationException(ValidationMessageUtil.UNAUTHENTICATION));
            if(!member.isAdmin()) {
                throw new UnAuthorizedException(ValidationMessageUtil.UNAUTHORIZATION);
            }
        } catch (Exception e) {
            createErrorResponse(response, e.getMessage());
            return false;
        }
        return true;
    }

    private void createErrorResponse(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        ObjectMapper objectMapper = new ObjectMapper();
        response.getWriter().write(objectMapper.writeValueAsString(
                new CustomResponse(HttpStatus.FORBIDDEN.value(), message)));
    }
}
