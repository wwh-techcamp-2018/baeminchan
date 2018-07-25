package codesquad.web;

import codesquad.domain.Member;
import codesquad.dto.LoginDto;
import codesquad.dto.MemberDto;
import codesquad.service.MemberService;
import codesquad.support.MemberDtoBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MemberAcceptanceTest {
    public static final Logger log = LoggerFactory.getLogger(MemberAcceptanceTest.class);
    @Autowired
    private TestRestTemplate template;

    @Autowired
    private MemberService memberService;

    @Test
    public void memberCreateTest() {
        String email = "pobi@naver.com";

        ResponseEntity<Member> response = template.postForEntity("/members", MemberDtoBuilder.builder().email(email).build(), Member.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(memberService.findByEmail(email)).isNotNull();
    }

    @Test
    public void memberCreateFailTest() throws JSONException {
        ResponseEntity<String> response = template.postForEntity("/members",
                MemberDtoBuilder.builder().email("123").build(), String.class);
        String errorMessage = getErrorMessageFromJsonString(response.getBody());

        assertThat(errorMessage).isEqualTo("메일의 양식을 지켜주세요.");
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void loginTest() {
        String email = "javajigi@naver.com";
        String password = "123123";

        ResponseEntity<Member> response = template.postForEntity("/members",
                MemberDtoBuilder.builder().email(email).password(password).build(), Member.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(memberService.findByEmail(email)).isNotNull();

        LoginDto loginDto = new LoginDto(email, password);
        ResponseEntity<String> loginResponse = template.postForEntity("/members/login", loginDto, String.class);
        assertThat(loginResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void loginFailTest() {
        LoginDto loginDto = new LoginDto("javajigi@kakao.com", "123123");
        ResponseEntity<String> response = template.postForEntity("/members/login", loginDto, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
        assertThat(response.getBody()).isEqualTo("유저 정보를 찾을 수 없습니다.");
    }

    private String getErrorMessageFromJsonString(String jsonText) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsonText);
        JSONArray myResponse = jsonObject.getJSONArray("errors");
        return ((JSONObject) myResponse.get(0)).getString("defaultMessage");
    }


}