package codesquad.web;

import codesquad.domain.User;
import codesquad.support.test.AcceptanceTest;
import codesquad.support.test.HtmlFormDataBuilder;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import static org.assertj.core.api.Java6Assertions.assertThat;


public class RedirectControllerTest extends AcceptanceTest {

    HtmlFormDataBuilder builder;

    @Before
    public void setUp() throws Exception {
        builder = HtmlFormDataBuilder.urlEncodedForm();
    }

    @Test
    public void login(){
        requestToRedirectController(UserControllerAcceptanceTest.LOGIN_URL,HttpStatus.OK,"로그인");
    }

    @Test
    public void signup(){
        requestToRedirectController(UserControllerAcceptanceTest.SIGNUP_URL,HttpStatus.OK,"가입 완료");
    }

    @Test
    public void home(){
        requestToRedirectController("/",HttpStatus.OK,"메인반찬 전체보기");
    }


    private void requestToRedirectController(String url, HttpStatus status, String confirm){
        HttpEntity<MultiValueMap<String,Object>> request = builder.build();
        ResponseEntity<String> responseEntity = template().getForEntity(url,String.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(status);
        assertThat(responseEntity.getBody().contains(confirm)).isEqualTo(true);

    }
}