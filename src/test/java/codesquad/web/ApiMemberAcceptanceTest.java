package codesquad.web;

import codesquad.domain.Member;
import codesquad.dto.MemberDto;
import codesquad.dto.MemberDtoTest;
import codesquad.exception.ErrorDetails;
import codesquad.exception.UnAuthenticationException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import support.test.AcceptanceTest;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiMemberAcceptanceTest extends AcceptanceTest {

    private MemberDto dooho;
    private MemberDto doy;

    @Before
    public void setUp() throws Exception {
        dooho = MemberDtoTest.DOOHO;
        template().postForEntity("/api/members", dooho, Void.class);
        doy = MemberDtoTest.DOY;
        template().postForEntity("/api/members", doy, Void.class);
    }

    @Test
    public void create() {
        MemberDto testMemberDto = MemberDtoTest.newMemberDto("test@woowahan.com");
        ResponseEntity<Void> response = template().postForEntity("/api/members", testMemberDto, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        String location = response.getHeaders().getLocation().getPath();

        Member dbMember = basicAuthTemplate(findByEmail(testMemberDto.getEmail())).getForObject(location, Member.class);
        assertThat(dbMember).isNotNull();
    }

    @Test
    public void create_fail_when_no_email() {
        MemberDto testMemberDto = MemberDtoTest.newMemberDto("");
        ResponseEntity<ErrorDetails> response = template().postForEntity("/api/members", testMemberDto, ErrorDetails.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody().getMessage()).contains("please input email");
        assertThat(response.getBody().getMessage()).contains("email should be longer than 3 and shorter than 100");
    }

    @Test
    public void create_fail_when_wrong_format_email() {
        MemberDto testMemberDto = MemberDtoTest.newMemberDto("dooho");
        ResponseEntity<ErrorDetails> response = template().postForEntity("/api/members", testMemberDto, ErrorDetails.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody().getMessage()).isEqualTo("wrong email format");
    }

    @Test
    public void create_fail_when_number_only_password() {
        MemberDto testMemberDto = MemberDtoTest.newMemberDto("test2@woowahan.com", "11111111");
        ResponseEntity<ErrorDetails> response = template().postForEntity("/api/members", testMemberDto, ErrorDetails.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody().getMessage()).isEqualTo("Invalid Password");
    }

    @Test
    public void login() {
        ResponseEntity<Void> response = template().postForEntity("/api/members/login", dooho, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FOUND);
        assertThat(response.getHeaders().getLocation().getPath()).startsWith("/");
    }

    @Test
    public void login_fail_when_not_match_password() {
        MemberDto testMemberDto = MemberDtoTest.newMemberDto("doy@woowahan.com", "password1234567");
        ResponseEntity<ErrorDetails> response = template().postForEntity("/api/members/login", testMemberDto, ErrorDetails.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody().getMessage()).isEqualTo(UnAuthenticationException.NOT_MATCH_PASSWORD);
    }

}
