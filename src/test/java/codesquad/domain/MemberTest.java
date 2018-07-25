package codesquad.domain;

public class MemberTest {

    public static Member newMember(String email) {
        return newMember(email, "1234password");
    }

    public static Member newMember(String email, String password) {
        return new Member(1L, email, password, "dooho", "01012345678");
    }

}