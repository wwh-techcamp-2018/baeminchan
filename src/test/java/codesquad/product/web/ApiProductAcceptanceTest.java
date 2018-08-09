package codesquad.product.web;

import codesquad.RestResponse;
import codesquad.product.domain.Product;
import codesquad.security.ErrorsResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import support.test.AcceptanceTest;

import java.util.List;

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

    @Test
    public void get_특정베스트카테고리_반찬리스트() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<RestResponse<List<Product>>> response = template.exchange(
                "/category/best/1/products",
                HttpMethod.GET,
                new HttpEntity<>(headers),
                new ParameterizedTypeReference<RestResponse<List<Product>>>() {
                });

        log.debug("1 best-category : {}", response.getBody().getData());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getData()).isNotEmpty();
    }

    @Test
    public void get_존재하지않는카테고리_반찬리스트() {
        ResponseEntity<ErrorsResponse> response = template.getForEntity("/category/best/987654321/products", ErrorsResponse.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

}
