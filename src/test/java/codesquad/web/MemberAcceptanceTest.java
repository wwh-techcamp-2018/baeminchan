package codesquad.web;

import codesquad.domain.Member;
import codesquad.domain.MemberRepository;
import codesquad.dto.MemberDto;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.util.MultiValueMap;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MemberAcceptanceTest {
    public static final Logger log =  LoggerFactory.getLogger(MemberAcceptanceTest.class);
    @Autowired
    private TestRestTemplate template;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void memberCreateTest() {
        String email = "pobi@naver.com";

        ResponseEntity<Member> response = template.postForEntity("/members",
                new MemberDto(email, "1234", "pobi","01012341234"), Member.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(memberRepository.findByEmail(email).isPresent()).isTrue();
    }

    @Test
    public void inValidationEmailFormatTest() throws JSONException {
        String email = "email";

        ResponseEntity<String> response = template.postForEntity("/members",
                new MemberDto(email, "1234", "pobi","01012341234"), String.class);
        String errorMessage = getErrorMessageFromJsonString(response.getBody());

        assertThat(errorMessage).isEqualTo("메일의 양식을 지켜주세요.");
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void inValidationPasswordFormatTest() throws JSONException {
        ResponseEntity<String> response = template.postForEntity("/members",
                new MemberDto("email@com", "123", "pobi","01012341234"), String.class);
        String errorMessage = getErrorMessageFromJsonString(response.getBody());

        assertThat(errorMessage).isEqualTo("비밀번호는 4자리 이상, 30자 이하이어야 합니다.");
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void inValidationNameFormatTest() throws JSONException {
        ResponseEntity<String> response = template.postForEntity("/members",
                new MemberDto("email@com", "1234", "1pobi2pobi3pobi4pobi5pobi6pobi7pobi8pobi","01012341234"), String.class);
        String errorMessage = getErrorMessageFromJsonString(response.getBody());

        assertThat(errorMessage).isEqualTo("이름은 30자 이하이어야 합니다.");
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void inValidationPhoneNumberFormatTest() throws JSONException {
        ResponseEntity<String> response = template.postForEntity("/members",
                new MemberDto("email@com", "1234", "pobi","01012a41!34"), String.class);
        String errorMessage = getErrorMessageFromJsonString(response.getBody());

        assertThat(errorMessage).isEqualTo("10~11자리의 숫자만 입력가능합니다");
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

        response = template.postForEntity("/members",
                new MemberDto("email@com", "1234", "pobi","010123412345678"), String.class);
        errorMessage = getErrorMessageFromJsonString(response.getBody());

        assertThat(errorMessage).isEqualTo("10~11자리의 숫자만 입력가능합니다");
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    private String getErrorMessageFromJsonString(String jsonText) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsonText);
        JSONArray myResponse = jsonObject.getJSONArray("errors");
        return ((JSONObject)myResponse.get(0)).getString("defaultMessage");
    }
}