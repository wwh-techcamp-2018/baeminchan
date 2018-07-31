package codesquad.security;

import codesquad.domain.User;

import javax.servlet.http.HttpSession;

public class HttpSessionUtils {
    public static final String SESSIONED_USER = "sessionedUser";

    public static User getUserFromSession(HttpSession session) {
        return (User) session.getAttribute(HttpSessionUtils.SESSIONED_USER);
    }
}
