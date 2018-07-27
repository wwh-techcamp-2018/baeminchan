package codesquad.security;

import codesquad.domain.Member;

import javax.servlet.http.HttpSession;

public class HttpSessionUtils {
    public static final String MEMBER_SESSION_KEY = "loginedMember";

    public static void setMemberToSession(Member member, HttpSession session) {
        session.setAttribute(MEMBER_SESSION_KEY, member);
    }
}
