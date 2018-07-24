package codesquad.web;

import codesquad.domain.Member;
import codesquad.dto.MemberDto;
import codesquad.dto.MemberDtoTest;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    @Test(expected = MethodArgumentNotValidException.class)
    public void invalidPassword() {
        MemberDto testMemberDto = MemberDtoTest.newMemberDto("dooho@woowahan.com", "p");
        ResponseEntity<Void> response = template().postForEntity("/api/members", testMemberDto, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

}
