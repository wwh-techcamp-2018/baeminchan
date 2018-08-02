package codesquad.util;


import codesquad.domain.User;
import codesquad.support.NotExistException;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class SessionUtil {
    public static final String SESSION_KEY = "EMAIL_SESSION_KEY";

    public static User getSessonUser(HttpServletRequest request) {
        return Optional.ofNullable((User) request.getSession().getAttribute(SESSION_KEY)).orElseThrow(() -> new NotExistException("Session 유지 안되었어요."));
    }

    public static void setUserSesssion(HttpServletRequest request, User user) {
        request.getSession().setAttribute(SESSION_KEY, user);
    }
}
