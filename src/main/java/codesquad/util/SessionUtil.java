package codesquad.util;

import codesquad.domain.User;

import javax.servlet.http.HttpSession;

public class SessionUtil {
    public static final String USER_SESSION_KEY = "loginedUser";

    public static void setUserInSession(HttpSession session, User loginUser) {
        session.setAttribute(USER_SESSION_KEY, loginUser);
    }
}
