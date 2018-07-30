package codesquad.support;

import codesquad.domain.Member;
import codesquad.support.exception.UnAuthenticationException;

import javax.servlet.http.HttpSession;

public class SessionUtil {
    public static final String USER_SESSION_KEY = "sessionMember";

    public static Member getMember(HttpSession session) {
        Member loginMember = (Member) session.getAttribute(USER_SESSION_KEY);
        if (loginMember == null) {
            throw new UnAuthenticationException("로그인을 먼저 하세요.");
        }
        return loginMember;
    }

    public static void setMember(HttpSession session, Member Member) {
        session.setAttribute(USER_SESSION_KEY, Member);
    }

    public static void logout(HttpSession session) {
        session.removeAttribute(USER_SESSION_KEY);
    }

    public static boolean matchMember(HttpSession session, Member Member) {
        Member loginMember = getMember(session);
        return loginMember.equals(Member);
    }
}
