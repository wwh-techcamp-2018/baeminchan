package codesquad.user.security;

import codesquad.exception.UnAuthenticationException;
import codesquad.exception.UnAuthorizedException;
import codesquad.user.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Slf4j
public class LoginUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(LoginUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        User user = HttpSessionUtils.getUserFromSession(webRequest);
        LoginUser loginUser = parameter.getParameterAnnotation(LoginUser.class);
        if (!user.isGuestUser()) {
            log.info("logined user: {}", user);
            if (!user.hasRole(loginUser.role())) {
                throw new UnAuthorizedException();
            }

            return user;
        }

        if (loginUser.required()) {
            throw UnAuthenticationException.logInRequired();
        }

        return user;
    }
}
