package support.test;

import codesquad.domain.Member;
import codesquad.repository.MemberRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public abstract class AcceptanceTest {
    private static final String DEFAULT_LOGIN_MEMBER = "doy@woowahan.com";
    private static final String ADMIN_LOGIN_MEMBER = "admin@woowahan.com";
    private static final String ADMIN_LOGIN_MEMBER_PWD = "admin123";

    @Autowired
    private TestRestTemplate template;

    @Autowired
    private MemberRepository memberRepository;

    public TestRestTemplate template() {
        return template;
    }

    public TestRestTemplate basicAuthTemplate() {
        return basicAuthTemplate();
    }

    public TestRestTemplate basicAuthTemplate(Member loginMember) {
        return template.withBasicAuth(loginMember.getEmail(), loginMember.getPassword());
    }
    public TestRestTemplate basicAdminAuthTemplate() {
        return template.withBasicAuth(ADMIN_LOGIN_MEMBER, ADMIN_LOGIN_MEMBER_PWD);
    }

    protected Member defaultMember() {
        return findByEmail(DEFAULT_LOGIN_MEMBER);
    }

    protected Member adminMember() {return findByEmail(ADMIN_LOGIN_MEMBER);}

    protected Member findByEmail(String email) {
        return memberRepository.findByEmail(email).get();
    }

    protected <T> ResponseEntity<T> requestForEntityWithParameterized(String url, HttpMethod method, Object body, ParameterizedTypeReference<T> reference) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return template().exchange(url, method, new HttpEntity<>(body, headers), reference);
    }
}
