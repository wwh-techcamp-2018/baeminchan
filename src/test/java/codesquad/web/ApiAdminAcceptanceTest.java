package codesquad.web;

import codesquad.domain.Product;
import codesquad.dto.CategoryDto;
import codesquad.dto.CategoryListDto;
import codesquad.dto.CategoryPostDto;
import codesquad.dto.PromotionDto;
import codesquad.exception.ErrorDetails;
import org.junit.Test;
import org.springframework.http.*;
import support.test.AcceptanceTest;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiAdminAcceptanceTest extends AcceptanceTest {

    @Test
    public void addCategory() {
        CategoryPostDto categoryPostDto = new CategoryPostDto("국·찌개", "대따맛있는국");
        ResponseEntity<Void> postResponse = basicAdminAuthTemplate().postForEntity("/api/admin/categories", categoryPostDto, Void.class);
        assertThat(postResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        ResponseEntity<CategoryListDto> getResponse = template().getForEntity("/api/categories", CategoryListDto.class);
        assertThat(getResponse.getBody().getChildren().get(1).getName()).isEqualTo("국·찌개");
        assertThat(getResponse.getBody().getChildren().get(1).getChildren()).contains(new CategoryDto("대따맛있는국"));
    }

    @Test
    public void deleteCategory() {
        CategoryPostDto categoryPostDto = new CategoryPostDto("밑반찬", "나물무침");
        ResponseEntity<Void> deleteResponse =
                basicAdminAuthTemplate().exchange("/api/admin/categories", HttpMethod.DELETE, createHttpEntity(categoryPostDto), Void.class);
        assertThat(deleteResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        ResponseEntity<CategoryListDto> getResponse = template().getForEntity("/api/categories", CategoryListDto.class);
        assertThat(getResponse.getBody().getChildren().get(0).getName()).isEqualTo("밑반찬");
        assertThat(getResponse.getBody().getChildren().get(0).getChildren().size()).isEqualTo(8);
    }

    @Test
    public void addPromotion() {
        PromotionDto promotionDto = new PromotionDto("baeminchan.com", "배민찬입니다");
        ResponseEntity<Void> response = basicAdminAuthTemplate().postForEntity("/api/admin/promotions", promotionDto, Void.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void addPromotion_() {
        PromotionDto promotionDto = new PromotionDto("baeminchan.com", "배민찬입니다");
        ResponseEntity<Void> response = basicAdminAuthTemplate().postForEntity("/api/admin/promotions", promotionDto, Void.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void deletePromotion() {
        ResponseEntity<Void> response = basicAdminAuthTemplate().exchange("/api/admin/promotions/1", HttpMethod.DELETE, null, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void deletePromotion_invalidId() {
        ResponseEntity<ErrorDetails> response = basicAdminAuthTemplate().exchange("/api/admin/promotions/100", HttpMethod.DELETE, null, ErrorDetails.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void addProduct() {
        Product newProduct = Product.defaultProduct();
        ResponseEntity<Void> response = basicAdminAuthTemplate().postForEntity("/api/admin/products", newProduct, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void deleteProduct() {
        ResponseEntity<Void> response = basicAdminAuthTemplate().exchange("/api/admin/products/1", HttpMethod.DELETE, null, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    private HttpEntity createHttpEntity(Object body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity(body, headers);
    }


}
