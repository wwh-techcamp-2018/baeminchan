package codesquad.util;

import codesquad.user.domain.User;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;

public class SessionUtil {
    public static final String USER_SESSION_KEY = "loginedUser";

    public static User getUserFromSession(NativeWebRequest session) {
        return (User) session.getAttribute(USER_SESSION_KEY, WebRequest.SCOPE_SESSION);
    }


    public static User getUserFromSession(HttpSession session) {
        return (User) session.getAttribute(USER_SESSION_KEY);
    }

}
