package codesquad.security;

import codesquad.exception.UnAuthenticationException;
import codesquad.exception.UnAuthorizedException;
import codesquad.user.domain.User;
import codesquad.user.domain.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Slf4j
public class AdminAuthInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("1");
        Long userId = Optional.ofNullable(HttpSessionUtils.getUserSession(request.getSession()))
                .orElseThrow(UnAuthenticationException::new);
        log.info("2");
        User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        log.info("3");
        if (user.isGuestUser())
            throw new UnAuthorizedException("Email", "관리자 계정으로 로그인 해주세요.");
        if (!user.isAdmin())
            throw new UnAuthorizedException("Role", "관리자 권한이 없습니다.");

        return true;
    }

}