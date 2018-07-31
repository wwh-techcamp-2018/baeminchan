package codesquad.security;

import codesquad.exception.ErrorDetails;
import codesquad.exception.UnAuthenticationException;

import codesquad.domain.Member;
import codesquad.dto.MemberLoginDto;
import codesquad.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.Date;

public class BasicAuthInterceptor extends HandlerInterceptorAdapter {
    private static final Logger log = LoggerFactory.getLogger(BasicAuthInterceptor.class);

    @Autowired
    private MemberService memberService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String authorization = request.getHeader("Authorization");
        Member member = null;

        log.debug("Authorization : {}, null means not using basicAuth", authorization);

        if (authorization != null && authorization.startsWith("Basic")) {
            member = getMemberBasicAuth(request, authorization);
        }
        if (member == null) {
            member = (Member) request.getSession().getAttribute(HttpSessionUtils.MEMBER_SESSION_KEY);
        }

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

    private Member getMemberBasicAuth(HttpServletRequest request, String authorization) {
        String base64Credentials = authorization.substring("Basic".length()).trim();
        String credentials = new String(Base64.getDecoder().decode(base64Credentials), Charset.forName("UTF-8"));
        final String[] values = credentials.split(":", 2);
        log.debug("username : {}", values[0]);
        log.debug("password : {}", values[1]);
        Member member = memberService.login(new MemberLoginDto(values[0], values[1]));
        request.getSession().setAttribute(HttpSessionUtils.MEMBER_SESSION_KEY, member);
        return member;
    }

    private void addErrorDetailToResponse(HttpServletResponse response, String errorMessage) throws IOException {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), errorMessage);
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        String json = new ObjectMapper().writeValueAsString(errorDetails);
        response.getWriter().write(json);
        response.flushBuffer();
    }


}

