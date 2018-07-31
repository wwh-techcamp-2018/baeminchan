package support.test;

import codesquad.dto.LoginDto;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(value = "dev")
public abstract class AcceptanceTest {
    private static final Logger log = LoggerFactory.getLogger(AcceptanceTest.class);

    protected LoginDto DEFAULT_USER = new LoginDto("aa@aa.com", "password1");
    protected LoginDto ADMIN_USER = new LoginDto("admin@admin.com", "password1");

    @Autowired
    private TestRestTemplate template;

    public TestRestTemplate template() {
        return template;
    }

    protected ResponseEntity basicAuthRequest(RequestEntity requestEntity, LoginDto user) {

        return request(
                template.withBasicAuth(user.getEmail(), user.getPassword()), requestEntity
        );
    }

    protected ResponseEntity request(TestRestTemplate template, RequestEntity requestEntity) {
        return template.exchange(
                requestEntity.getUrl(),
                requestEntity.getMethod(),
                requestEntity.getBody(),
                requestEntity.getReturnType()
        );
    }
}