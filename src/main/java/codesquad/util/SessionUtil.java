package codesquad.util;

import codesquad.dto.user.UserSessionDto;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;

public class SessionUtil {
    public static final String SESSTION_KEY = "sessionKey";

    public static UserSessionDto getUserSessionDtoFromSession(NativeWebRequest webRequest) {
        Object maybeUserSessionDto = webRequest.getAttribute(SESSTION_KEY, WebRequest.SCOPE_SESSION);
        if (maybeUserSessionDto == null) {
            // TODO: 2018. 7. 30. 사용자 정의 예외를 만들어야합니다.
            throw new RuntimeException();
        }
        return (UserSessionDto) maybeUserSessionDto;
    }
}
