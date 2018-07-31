package codesquad.web;

import codesquad.domain.Category;
import codesquad.dto.CategoryDTO;
import codesquad.dto.LoginUserDTO;
import codesquad.support.ApiAcceptanceTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class ApiAdminAcceptanceTest extends ApiAcceptanceTest {

    private LoginUserDTO loginUserDTOAdmin;
    private LoginUserDTO loginUserDTO;
    private CategoryDTO categoryDTO;

    private static final String API_URL = "/api/admin";

    @Before
    public void setUp() throws Exception {
        loginUserDTOAdmin = new LoginUserDTO();
        loginUserDTOAdmin.setEmail("a@naver.com");
        loginUserDTOAdmin.setPassword("test1234");

        loginUserDTO = new LoginUserDTO();
        loginUserDTO.setEmail("b@naver.com");
        loginUserDTO.setPassword("test1234");

        categoryDTO = new CategoryDTO();
        categoryDTO.setParentId(1L);
        categoryDTO.setTitle("생성메뉴");
    }

    @Test
    public void 메뉴생성_성공() {
        login(loginUserDTOAdmin);
        TestRestTemplate testRestTemplate = new TestRestTemplate();

        ResponseEntity<String> response = template().postForEntity(API_URL + "/category", categoryDTO, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        response = template().exchange("/api/categories", HttpMethod.GET, createHttpEntity(), String.class);
        assertThat(response.getBody().contains("생성메뉴")).isTrue();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void 메뉴생성_실패_권한없음() {
        login(loginUserDTO);

        ResponseEntity<String> response = template().postForEntity(API_URL + "/category", categoryDTO, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }

    @Test
    public void 메뉴생성_실패_세션없음() {
        ResponseEntity<String> response = template().postForEntity(API_URL + "/category", categoryDTO, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }

    @Test
    public void 메뉴삭제_성공() {
        ResponseEntity<String> response = template().exchange(API_URL + "/category/10", HttpMethod.DELETE, createHttpEntity(), String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        response = template().exchange("/api/categories", HttpMethod.GET, createHttpEntity(), String.class);
        assertThat(response.getBody().contains("나물무침")).isFalse();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void 메뉴삭제_실패() {
        ResponseEntity<String> response = template().exchange(API_URL + "/category/100", HttpMethod.DELETE, createHttpEntity(), String.class);
        log.debug(response.getBody());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void 메뉴수정_성공() {
    }

    @Test
    public void 메뉴수정_실패() {
    }

    private void login(LoginUserDTO loginUserDTO){
        ResponseEntity<Void> response = template().postForEntity("/api/users/login", loginUserDTO, Void.class);
        response.getHeaders().
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
