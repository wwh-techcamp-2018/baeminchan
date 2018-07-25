package codesquad.dto;

import codesquad.domain.Member;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

public class MemberDtoTest {
    public static final MemberDto DOY = new MemberDto("doy@woowahan.com", "1234password", "doy",
            "01012345678");
    public static final MemberDto DOOHO = new MemberDto("dooho@woowahan.com", "5678password", "dooho",
            "01012345678");

    public static MemberDto newMemberDto(String email) {
        return newMemberDto(email, "1234password");
    }

    public static MemberDto newMemberDto(String email, String password) {
        return new MemberDto(email, password, "name", "01012345678");
    }

    private PasswordEncoder bcryptPasswordEncoder;

    @Before
    public void setUp() throws Exception {
        bcryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    @Test
    public void toEntity() {
        Member encodedMember = DOOHO.toEntity(bcryptPasswordEncoder);
        assertThat(encodedMember.matchPassword("5678password", bcryptPasswordEncoder)).isTrue();
    }
}