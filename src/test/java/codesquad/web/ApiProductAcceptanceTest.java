package codesquad.web;

import codesquad.domain.Product;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import support.test.AcceptanceTest;
import support.test.RequestEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiProductAcceptanceTest extends AcceptanceTest {

    private final String ADMIN_PRODUCT = "/admin/product";

    private RequestEntity.Builder builder;

    @Before
    public void setUp() throws Exception {
        builder = new RequestEntity.Builder();
    }

    @Test
    public void allAcceptanceTest() {
        Product newProduct = new Product("신상품", "맛있는 고기반찬", 7000, "url/wefw");
        RequestEntity createRequestEntity = builder
                .withUrl(ADMIN_PRODUCT)
                .withMethod(HttpMethod.POST)
                .withBody(newProduct)
                .withReturnType(Product.class)
                .build();
        ResponseEntity<Product> createResponseEntity = basicAuthRequest(createRequestEntity, ADMIN_USER);
        assertResponseProduct(createResponseEntity, newProduct, HttpStatus.CREATED);

        Product updateProduct = new Product(createResponseEntity.getBody().getId(), "update title", "update content", 8000, "url/wefw");
        RequestEntity updateRequestEntity = builder
                .withUrl(ADMIN_PRODUCT)
                .withMethod(HttpMethod.PUT)
                .withBody(updateProduct)
                .withReturnType(Product.class)
                .build();
        ResponseEntity<Product> updateResponseEntity = basicAuthRequest(updateRequestEntity, ADMIN_USER);
        assertResponseProduct(updateResponseEntity, updateProduct, HttpStatus.OK);

        RequestEntity readRequestEntity = builder
                .withUrl(updateProduct.generateUrl())
                .withMethod(HttpMethod.GET)
                .withReturnType(Product.class)
                .build();
        ResponseEntity<Product> readResponseEntity = basicAuthRequest(readRequestEntity, DEFAULT_USER);
        assertResponseProduct(readResponseEntity, updateProduct, HttpStatus.OK);

        RequestEntity deleteRequestEntity = builder
                .withUrl(ADMIN_PRODUCT)
                .withMethod(HttpMethod.DELETE)
                .withBody(updateProduct)
                .withReturnType(Void.class)
                .build();
        ResponseEntity deleteResponseEntity = basicAuthRequest(deleteRequestEntity, ADMIN_USER);
        assertThat(deleteResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    private void assertResponseProduct(ResponseEntity<Product> responseEntity, Product expectedProduct, HttpStatus expectedStatus) {
        assertThat(responseEntity.getStatusCode()).isEqualTo(expectedStatus);
        assertThat(responseEntity.getBody().getTitle()).isEqualTo(expectedProduct.getTitle());
        assertThat(responseEntity.getBody().getContents()).isEqualTo(expectedProduct.getContents());
        assertThat(responseEntity.getBody().getPrice()).isEqualTo(expectedProduct.getPrice());
        assertThat(responseEntity.getBody().getThumbnailLink()).isEqualTo(expectedProduct.getThumbnailLink());
    }


}