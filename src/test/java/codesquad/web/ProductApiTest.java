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

    @Test
    public void read() {
        ResponseEntity<Product> responseEntity = template().getForEntity("/api/product/1", Product.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }


    @Test
    public void readAuthentic() {
        ResponseEntity<Product> responseEntity = template().getForEntity("/api/product/1", Product.class);
        Product readProduct = responseEntity.getBody();
        Product actualProduct = new Product();
        actualProduct.setTitle("고기 반찬");
        actualProduct.setContents("아침에 먹기 좋은 반찬~~!");
        actualProduct.setPrice(4000);
        actualProduct.setThumbnailLink("https://cdn.bmf.kr/_data/product/IE15B/f4577c1459679650d0554635d921b536.jpg");
        assertThat(actualProduct).isEqualTo(readProduct);
    }
}
