package codesquad.web;

import codesquad.domain.Product;
import codesquad.support.test.AcceptanceTest;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductApiTest extends AcceptanceTest {

    @Test
    public void create() {
        Product product = new Product();
        product.setTitle("[집밥의완성]궁중식 소고");
        product.setThumbnailLink("https://cdn.bmf.kr/_data/product/I4DEC/ebab7a5c6f31b59c1d0ffda25f0c82a3.jpg");
        product.setContents("맛있는 궁중식 소고기");
        product.setPrice(3000);
        ResponseEntity<Product> responseEntity = template().withBasicAuth("yeon@tech.com", "12345678").postForEntity("/admin/api/product", product, Product.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }
}
