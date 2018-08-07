package codesquad.product.controller;

import codesquad.RestResponse;
import codesquad.product.domain.BestProduct;
import codesquad.product.domain.BestProductRepository;
import codesquad.product.domain.Product;
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

public class ApiBestProductAcceptanceTest extends AcceptanceTest {
    private static final String BEST_PRODUCTS_API = "/api/bestproducts";
    private static final int PRODUCTS_SIZE = 3;
    private Long bestProductId;

    @Autowired
    private BestProductRepository bestProductRepository;

    @Before
    public void setUp() throws Exception {
        bestProductRepository.deleteAll();

        List<BestProduct> bestProducts = FixtureFactory.bestProductList(2, PRODUCTS_SIZE);

        bestProductId = bestProductRepository.saveAll(bestProducts).get(0).getId();
    }

    @Test
    public void getList() {
        ResponseEntity<RestResponse<List<BestProduct>>> response = getResponseEntityList(BEST_PRODUCTS_API, new ParameterizedTypeReference<RestResponse<List<BestProduct>>>() {
        });

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getData().size()).isEqualTo(2);
    }

    @Test
    public void getProducts() {
        ResponseEntity<RestResponse<List<Product>>> response = getResponseEntityList(BEST_PRODUCTS_API + "/" + bestProductId, new ParameterizedTypeReference<RestResponse<List<Product>>>() {
        });

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getData()).hasSize(PRODUCTS_SIZE);
    }

    @Test
    public void getProducts_non_exist() {
        ResponseEntity<RestResponse<List<Product>>> response = getResponseEntityList(BEST_PRODUCTS_API + "/" + Long.MAX_VALUE, new ParameterizedTypeReference<RestResponse<List<Product>>>() {
        });

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}
