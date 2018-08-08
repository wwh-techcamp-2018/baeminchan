package codesquad.web;

import codesquad.domain.Product;
import codesquad.support.AcceptanceTestTemplate;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductAcceptanceTest extends AcceptanceTestTemplate {
    @Test
    public void show() {
        ResponseEntity<List<Product>> response =
                getForEntityWithParameterized("/api/products", new ParameterizedTypeReference<List<Product>>(){});

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().size()).isNotZero();
    }
}