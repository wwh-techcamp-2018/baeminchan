package codesquad.web;

import codesquad.domain.Member;
import codesquad.domain.MemberRepository;
import codesquad.dto.MemberDto;
import codesquad.support.builder.HtmlFormDataBuilder;
import codesquad.support.builder.HtmlFormDataBuilderForObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.MultiValueMap;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MemberAcceptanceTest {
    @Autowired
    private TestRestTemplate template;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void create() throws Exception {
        String email = "pobi@naver.com";

        ResponseEntity<Member> response = template.postForEntity("/members", new MemberDto(email, "1234", "pobi","01012341234"), Member.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(memberRepository.findByEmail(email).isPresent()).isTrue();
    }
}