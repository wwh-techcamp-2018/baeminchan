package codesquad.security;

import codesquad.domain.User;
import codesquad.dto.SessionUserDTO;

import javax.servlet.http.HttpSession;

public class HttpSessionUtils {
    public static final String USER_SESSION_KEY = "loginedUser";

    public static void saveSession(HttpSession session, User user) {
        session.setAttribute(USER_SESSION_KEY, SessionUserDTO.fromUser(user));
    }
}
