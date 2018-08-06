package codesquad.product.controller;

import codesquad.product.domain.BestProduct;
import codesquad.product.domain.Product;
import codesquad.product.dto.BestProductDto;
import codesquad.support.dto.response.ResponseModel;
import codesquad.support.test.AcceptanceTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BestProductAcceptanceTest extends AcceptanceTest {

    private final static String bestProductUrl = "/api/bestProducts/";
    private ParameterizedTypeReference<ResponseModel<BestProduct>> reference;
    private ParameterizedTypeReference<ResponseModel<List<BestProduct>>> listReference;

    @Before
    public void setUp() throws Exception {
        reference = new ParameterizedTypeReference<ResponseModel<BestProduct>>() {
        };
        listReference = new ParameterizedTypeReference<ResponseModel<List<BestProduct>>>() {
        };
    }

    @Test
    public void create() {
        List<Product> products = new ArrayList<>();
        BestProductDto dto = new BestProductDto("서울 맛집 탐방", products);
        ResponseEntity<ResponseModel<BestProduct>> resp = basicAdminRequest(bestProductUrl, HttpMethod.POST, dto, reference);
        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void create_not_admin() {
        List<Product> products = new ArrayList<>();
        BestProductDto dto = new BestProductDto("서울 맛집 탐방", products);
        ResponseEntity<ResponseModel<BestProduct>> resp = basicUserRequest(bestProductUrl, HttpMethod.POST, dto, reference);
        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }

    @Test
    public void read() {
        ResponseEntity<ResponseModel<List<BestProduct>>> resp = basicUserRequest(bestProductUrl, HttpMethod.GET, null, listReference);
        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void read_admin() {
        ResponseEntity<ResponseModel<List<BestProduct>>> resp = basicAdminRequest(bestProductUrl, HttpMethod.GET, null, listReference);
        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void delete() {
        ResponseEntity<ResponseModel<BestProduct>> resp = basicAdminRequest(bestProductUrl + 1, HttpMethod.DELETE, null, reference);
        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void delete_not_admin() {
        ResponseEntity<ResponseModel<BestProduct>> resp = basicUserRequest(bestProductUrl + 2, HttpMethod.DELETE, null, reference);
        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }

    @Test
    public void delete_not_found() {
        ResponseEntity<ResponseModel<BestProduct>> resp = basicAdminRequest(bestProductUrl + Long.MAX_VALUE, HttpMethod.DELETE, null, reference);
        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

}
