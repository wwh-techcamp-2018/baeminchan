package codesquad.web;

import codesquad.domain.Member;
import codesquad.dto.MemberDto;
import codesquad.dto.MemberDtoTest;
import codesquad.exception.ErrorDetails;
import codesquad.exception.UnAuthenticationException;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import support.test.AcceptanceTest;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiMemberAcceptanceTest extends AcceptanceTest {

    @Test
    public void create() {
        MemberDto testMemberDto = MemberDtoTest.newMemberDto("Dooho");
        ResponseEntity<Void> response = template().postForEntity("/api/members", testMemberDto, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        String location = response.getHeaders().getLocation().getPath();

        Member dbMember = basicAuthTemplate(findByEmail(testMemberDto.getEmail())).getForObject(location, Member.class);
        assertThat(dbMember).isNotNull();
    }

    @Test
    public void login() {
        MemberDto testMemberDto = MemberDtoTest.newMemberDto("dooho@woowahan.com", "1234password");
        ResponseEntity<Void> response = template().postForEntity("/api/members/login", testMemberDto, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FOUND);
        assertThat(response.getHeaders().getLocation().getPath()).startsWith("/");
    }

    @Test
    public void login_fail() {
        MemberDto testMemberDto = MemberDtoTest.newMemberDto("dooho@woowahan.com", "password1234");
        ResponseEntity<ErrorDetails> response = template().postForEntity("/api/members/login", testMemberDto, ErrorDetails.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody().getMessage()).isEqualTo(UnAuthenticationException.NOT_MATCH_PASSWORD);
    }


//    @Test(expected = MethodArgumentNotValidException.class)
//    public void invalidPassword() {
//        MemberDto testMemberDto = MemberDtoTest.newMemberDto("dooho@woowahan.com", "p");
//        ResponseEntity<Void> response = template().postForEntity("/api/members", testMemberDto, Void.class);
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
//    }

}
