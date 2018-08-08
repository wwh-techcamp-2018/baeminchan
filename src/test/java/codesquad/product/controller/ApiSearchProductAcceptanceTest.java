package codesquad.product.controller;

import codesquad.RestResponse;
import codesquad.product.domain.Product;
import codesquad.product.domain.ProductRepository;
import codesquad.support.AcceptanceTest;
import codesquad.support.FixtureFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiSearchProductAcceptanceTest extends AcceptanceTest {
    private static final String SEARCH_PRODUCT_API = "/api/search";
    private ParameterizedTypeReference<RestResponse<List<Product>>> reference;

    @Autowired
    private ProductRepository productRepository;

    private List<Product> products;

    @Before
    public void setUp() throws Exception {
        productRepository.deleteAll();

        reference = new ParameterizedTypeReference<RestResponse<List<Product>>>() {
        };
        products = FixtureFactory.productList("고기고기", "돼지고기", "소고기", "닭고기", "파전", "메밀묵", "메밀무침",
                "메밀소바", "된장국", "된장찌개", "김치찌개", "피자", "햄버거", "짜장면", "짱뽐", "소고기된장국", "온메밀국수", "매운소고기볶음");

        productRepository.saveAll(products);
    }

    @Test
    public void searchProduct() {
        ResponseEntity<RestResponse<List<Product>>> resp = getResponseEntityList(SEARCH_PRODUCT_API + "/고기", reference);

        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(resp.getBody().getData()).hasSize(6);
    }
}
