package codesquad.util;

import codesquad.domain.User;

import javax.servlet.http.HttpSession;
import java.util.Optional;

public class SessionUtil {

    private static final String SESSION_KEY = "loginedUser";

    public static void setUserSession(HttpSession session, User user) {
        session.setAttribute(SESSION_KEY, user);
    }

    public static User getUserSession(HttpSession session) {
        return Optional.ofNullable((User) session.getAttribute(SESSION_KEY))
                .orElseThrow(RuntimeException::new);
    }

    public static void removeUserSession(HttpSession session) {
        session.removeAttribute(SESSION_KEY);
    }
}
