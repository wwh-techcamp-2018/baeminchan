package codesquad.util;

import codesquad.domain.User;
import codesquad.domain.UserPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;

public class SessionUtil {
    public static final String USER_SESSION_KEY = "loginedUser";
    private static final Logger log = LoggerFactory.getLogger(SessionUtil.class);

    public static void setUserInSession(HttpSession session, User loginUser) {
        session.setAttribute(USER_SESSION_KEY, loginUser);
    }

    public static boolean isLoginUser(HttpSession session) {
        return session.getAttribute(USER_SESSION_KEY) != null;
    }

    public static boolean isAdminUser(HttpSession session) {
        User loginUser = (User) session.getAttribute(USER_SESSION_KEY);
        log.debug("isAdmin : {}", loginUser.getPermissions());
        return loginUser.getPermissions() == UserPermissions.ADMIN;
    }

    public static void removeUserInSession(HttpSession session){
        session.removeAttribute(USER_SESSION_KEY);
    }
}
