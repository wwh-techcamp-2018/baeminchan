package codesquad.security;

import codesquad.domain.User;

import javax.servlet.http.HttpSession;

public class HttpSessionUtils {
    public static final String USER_SESSION_KEY = "loginedUser";

    public static User getUserFromSession(HttpSession session) {
        return (User) session.getAttribute(USER_SESSION_KEY);
    }
}
