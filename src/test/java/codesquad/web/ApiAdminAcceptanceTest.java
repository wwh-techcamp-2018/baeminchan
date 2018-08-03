package codesquad.web;


import codesquad.dto.CategoryDTO;
import codesquad.dto.UpdateCategoryDTO;
import codesquad.support.ApiAcceptanceTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class ApiAdminAcceptanceTest extends ApiAcceptanceTest {

    private static final String API_URL = "/api/admin";
    private CategoryDTO categoryDTO;
    private UpdateCategoryDTO updateCategoryDTO;

    @Before
    public void setUp() throws Exception {
        updateCategoryDTO = new UpdateCategoryDTO(10L, "나물변경");

        categoryDTO = new CategoryDTO();
        categoryDTO.setParentId(1L);
        categoryDTO.setTitle("생성메뉴");
    }

    @Test
    public void 메뉴생성_성공() {
        ResponseEntity<String> response = templateWithAdminUser()
                .postForEntity(API_URL + "/category", categoryDTO, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        response = template().exchange("/api/categories", HttpMethod.GET, createHttpEntity(), String.class);
        assertThat(response.getBody().contains("생성메뉴")).isTrue();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void 메뉴생성_실패_권한없음() {
        ResponseEntity<String> response = templateWithNormalUser()
                .postForEntity(API_URL + "/category", categoryDTO, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }

//    @Test
//    public void 메뉴생성_실패_세션없음() {
//        ResponseEntity<String> response = template().postForEntity(API_URL + "/category", categoryDTO, String.class);
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
//    }

    @Test
    public void 메뉴삭제_성공() {
        ResponseEntity<String> response = templateWithAdminUser()
                .exchange(API_URL + "/category/11", HttpMethod.DELETE, createHttpEntity(), String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        response = template().exchange("/api/categories", HttpMethod.GET, createHttpEntity(), String.class);
        assertThat(response.getBody().contains("볶음")).isFalse();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void 메뉴삭제_실패_없는메뉴() {
        ResponseEntity<String> response = templateWithAdminUser()
                .exchange(API_URL + "/category/100", HttpMethod.DELETE, createHttpEntity(), String.class);
        log.debug(response.getBody());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void 메뉴삭제_실패_권한없음() {
        ResponseEntity<String> response = templateWithNormalUser()
                .exchange(API_URL + "/category/100", HttpMethod.DELETE, createHttpEntity(), String.class);
        log.debug(response.getBody());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }

    @Test
    public void 메뉴삭제_실패_세션없음() {
        ResponseEntity<String> response = template().exchange(API_URL + "/category/100", HttpMethod.DELETE, createHttpEntity(), String.class);
        log.debug(response.getBody());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }

    @Test
    public void 메뉴수정_성공() {
        ResponseEntity<String> response = templateWithAdminUser()
                .exchange(API_URL + "/category/10", HttpMethod.PUT, createHttpEntity(updateCategoryDTO), String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        response = template().exchange("/api/categories", HttpMethod.GET, createHttpEntity(), String.class);
        assertThat(response.getBody().contains("나물무침")).isFalse();
        assertThat(response.getBody().contains("나물변경")).isTrue();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void 메뉴수정_실패_없는메뉴() {
        updateCategoryDTO.setId(100L);
        ResponseEntity<String> response = templateWithAdminUser()
                .exchange(API_URL + "/category/100", HttpMethod.PUT, createHttpEntity(updateCategoryDTO), String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void 메뉴수정_실패_권한없음() {
        ResponseEntity<String> response = templateWithNormalUser()
                .exchange(API_URL + "/category/10", HttpMethod.PUT, createHttpEntity(updateCategoryDTO), String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }

//    @Test
//    public void 메뉴수정_실패_세션없음() {
//        ResponseEntity<String> response = template().exchange(API_URL + "/category/10", HttpMethod.PUT, createHttpEntity(updateCategoryDTO), String.class);
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
//    }
}
