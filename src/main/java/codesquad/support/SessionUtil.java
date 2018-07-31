package codesquad.support;

import codesquad.domain.Member;
import codesquad.support.exception.UnAuthenticationException;

import javax.servlet.http.HttpSession;
import java.util.Optional;

public class SessionUtil {
    public static final String USER_SESSION_KEY = "sessionMember";

    public static Optional<Member> getMember(HttpSession session) {
        Member loginMember = (Member) session.getAttribute(USER_SESSION_KEY);
        return Optional.ofNullable(loginMember);
    }

    public static void setMember(HttpSession session, Member Member) {
        session.setAttribute(USER_SESSION_KEY, Member);
    }

    public static void logout(HttpSession session) {
        session.removeAttribute(USER_SESSION_KEY);
    }

    public static boolean matchMember(HttpSession session, Member Member) {
        Member loginMember = getMember(session).orElseThrow(() -> new UnAuthenticationException("로그인이 필요합니다."));
        return loginMember.equals(Member);
    }
}
