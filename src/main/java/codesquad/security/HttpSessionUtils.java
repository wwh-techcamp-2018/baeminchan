package codesquad.security;

import codesquad.domain.User;

import javax.servlet.http.HttpSession;

public class HttpSessionUtils {

    public static final String USER_SESSION_KEY = "userId";

    public static void setUserSessionKey(HttpSession session, User user) {
        session.setAttribute(USER_SESSION_KEY, user.getId());
    }
}
