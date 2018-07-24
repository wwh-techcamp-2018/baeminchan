package codesquad.dto;

public class MemberDtoTest {
    public static final MemberDto DOY = new MemberDto("doy@woowahan.com", "1234password", "doy",
            "01012345678");
    public static final MemberDto DOOHO = new MemberDto("dooho@woowahan.com", "1234password", "dooho",
            "01012345678");

    public static MemberDto newMemberDto(String email) {
        return newMemberDto(email, "1234password");
    }

    public static MemberDto newMemberDto(String email, String password) {
        return new MemberDto(email, password, "name", "01012345678");
    }
}