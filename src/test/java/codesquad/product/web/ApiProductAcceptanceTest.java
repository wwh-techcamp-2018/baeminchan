package codesquad.product.web;

import codesquad.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import support.test.AcceptanceTest;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class ApiProductAcceptanceTest extends AcceptanceTest {

    private static final String KEYWORD = "이름";

    @Test
    public void searchRecommendations() {
        ResponseEntity<RestResponse> response = template.getForEntity(String.format("/products/search?keyword=%s", KEYWORD), RestResponse.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        log.debug("keyword: {}, result: {}", KEYWORD, response.getBody().getData());
    }

    @Test
    public void searchRecommendationsWithoutKeyword() {
        ResponseEntity<RestResponse> response = template.getForEntity("/products/search", RestResponse.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        log.debug("result: {}", response.getBody().getData());
    }
}
