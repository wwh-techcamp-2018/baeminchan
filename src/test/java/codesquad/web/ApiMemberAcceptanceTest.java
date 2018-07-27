package codesquad.web;

import codesquad.dto.MemberJoinDto;
import codesquad.dto.MemberJoinDtoTest;
import codesquad.exception.ErrorDetails;
import codesquad.exception.UnAuthenticationException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import support.test.AcceptanceTest;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiMemberAcceptanceTest extends AcceptanceTest {

    private MemberJoinDto dooho;
    private MemberJoinDto doy;

    @Before
    public void setUp() throws Exception {
        dooho = MemberJoinDtoTest.DOOHO;
        template().postForEntity("/api/members", dooho, Void.class);
        doy = MemberJoinDtoTest.DOY;
        template().postForEntity("/api/members", doy, Void.class);
    }

    @Test
    public void create() {
        MemberJoinDto testMemberDto = MemberJoinDtoTest.newMemberDto("test@woowahan.com");
        ResponseEntity<Void> response = template().postForEntity("/api/members", testMemberDto, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void login() {
        ResponseEntity<Void> response = template().postForEntity("/api/members/login", dooho, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FOUND);
        assertThat(response.getHeaders().getLocation().getPath()).startsWith("/");
    }

    @Test
    public void login_fail_when_not_match_password() {
        MemberJoinDto testMemberDto = MemberJoinDtoTest.newMemberDto("doy@woowahan.com", "password1234567");
        ResponseEntity<ErrorDetails> response = template().postForEntity("/api/members/login", testMemberDto, ErrorDetails.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody().getMessage()).isEqualTo(UnAuthenticationException.NOT_MATCH_PASSWORD);
    }

}
