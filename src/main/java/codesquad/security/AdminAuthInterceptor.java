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
        Long userId = Optional.ofNullable(HttpSessionUtils.getUserSession(request.getSession()))
                .orElseThrow(UnAuthenticationException::new);
        User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        if (!user.isAdmin())
            throw new UnAuthorizedException("Role", "관리자 권한이 없습니다.");

        return true;
    }

}