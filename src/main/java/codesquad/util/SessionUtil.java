package codesquad.util;

import codesquad.dto.user.UserSessionDto;
import codesquad.exception.SessionException;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;

public class SessionUtil {
    public static final String SESSION_KEY = "sessionKey";

    public static UserSessionDto getUserSessionDtoFromSession(NativeWebRequest webRequest) {
        Object maybeUserSessionDto = webRequest.getAttribute(SESSION_KEY, WebRequest.SCOPE_SESSION);
        if (maybeUserSessionDto == null) {
            throw new SessionException();
        }
        return (UserSessionDto) maybeUserSessionDto;
    }
}
