package codesquad.web;

import codesquad.domain.Product;
import codesquad.support.test.AcceptanceTest;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class EventCategoryApiTest extends AcceptanceTest {

    @Test
    public void fetchProducts() {
        ResponseEntity<Product[]> responseEntity = template().getForEntity("/api/categories/event/1", Product[].class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().length).isEqualTo(3);
    }
}
