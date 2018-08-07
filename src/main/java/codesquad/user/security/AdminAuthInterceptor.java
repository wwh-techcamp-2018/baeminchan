package codesquad.user.security;

import codesquad.exception.UnAuthorizedException;
import codesquad.user.domain.User;
import codesquad.user.domain.UserRepository;
import codesquad.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Slf4j
public class AdminAuthInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        Long id = Optional.ofNullable((Long) request.getSession().getAttribute(UserService.USER_SESSION_KEY)).orElseThrow(UnAuthorizedException::new);
        User user = userRepository.findById(id).orElseThrow(UnAuthorizedException::new);
        log.info("admin User : {}", user);

        if (!user.isAdmin()) {
            throw new UnAuthorizedException();
        }
        return true;
    }
}
