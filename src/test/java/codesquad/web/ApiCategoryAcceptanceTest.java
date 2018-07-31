package codesquad.web;

import codesquad.domain.Category;
import codesquad.support.ApiAcceptanceTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class ApiCategoryAcceptanceTest extends ApiAcceptanceTest {

    private static final String API_URL = "/api/categories";

    @Test
    public void 메뉴레이어생성_성공() {
        ResponseEntity<Category> response = template().exchange(API_URL, HttpMethod.GET, createHttpEntity(), Category.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
