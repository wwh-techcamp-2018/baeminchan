package codesquad.dto;

import codesquad.domain.Member;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

public class MemberJoinDtoTest {
    public static final MemberJoinDto DOY = new MemberJoinDto("doy@woowahan.com", "1234password", "doy",
            "01012345678");
    public static final MemberJoinDto DOOHO = new MemberJoinDto("dooho@woowahan.com", "5678password", "dooho",
            "01012345678");

    public static MemberJoinDto newMemberDto(String email) {
        return newMemberDto(email, "1234password");
    }

    public static MemberJoinDto newMemberDto(String email, String password) {
        return new MemberJoinDto(email, password, "name", "01012345678");
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