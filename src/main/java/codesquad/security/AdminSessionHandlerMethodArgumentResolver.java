package codesquad.security;

import codesquad.dto.user.UserSessionDto;
import codesquad.exception.AuthenticationException;
import codesquad.util.SessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Slf4j
public class AdminSessionHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(AdminSession.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        UserSessionDto adminUserSessionDto = SessionUtil.getUserSessionDtoFromSession(webRequest);
        if (adminUserSessionDto.isAdmin()) {
            return adminUserSessionDto;
        }
        throw new AuthenticationException();
    }
}
