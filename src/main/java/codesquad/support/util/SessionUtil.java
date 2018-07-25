package codesquad.support.util;

import codesquad.user.domain.User;

import javax.servlet.http.HttpSession;

public class SessionUtil {
    private static final String USER_SESSION_KEY = "loginedUser";

    public static void setUserInSession(HttpSession session, User loginUser) {
        session.setAttribute(USER_SESSION_KEY, loginUser);
    }
}
