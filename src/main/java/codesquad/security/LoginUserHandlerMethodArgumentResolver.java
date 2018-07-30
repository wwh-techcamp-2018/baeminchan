package codesquad.security;

import codesquad.exception.ForbiddenException;
import codesquad.exception.UnauthenticatedException;
import codesquad.support.util.SessionUtil;
import codesquad.user.domain.User;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class LoginUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(LoginUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        User user = SessionUtil.getUserFromSession(webRequest).orElseThrow(UnauthenticatedException::new);

        LoginUser loginUser = parameter.getParameterAnnotation(LoginUser.class);
        if (loginUser.adminRequired() && !user.isAdmin()) {
            throw new ForbiddenException();
        }
        return user;
    }
}
