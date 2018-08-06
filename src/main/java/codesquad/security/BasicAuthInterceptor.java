package codesquad.security;

import codesquad.exception.UnAuthenticationException;

import codesquad.domain.Member;
import codesquad.dto.MemberLoginDto;
import codesquad.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.Charset;
import java.util.Base64;

public class BasicAuthInterceptor extends HandlerInterceptorAdapter {
    private static final Logger log = LoggerFactory.getLogger(BasicAuthInterceptor.class);

    @Autowired
    private MemberService memberService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String authorization = request.getHeader("Authorization");
        Member member;

        log.debug("Authorization : {}, null means not using basicAuth", authorization);
        if (authorization == null || !authorization.startsWith("Basic")) {
            return true;
        }

        try {
            member = getMemberBasicAuth(request, authorization);
            log.debug("Login Member Account : {}", member.getUsername());
        } catch (UnAuthenticationException e) {
            log.debug("interceptor 걸렸네: " + e.getMessage());
        } finally {
            return true;
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
}

