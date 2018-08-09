package codesquad.category.web;

import codesquad.RestResponse;
import codesquad.category.domain.BestCategory;
import codesquad.category.domain.BestCategoryRepository;
import codesquad.product.domain.Product;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import support.test.AcceptanceTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class ApiBestCategoryAcceptanceTest extends AcceptanceTest {

    @Autowired
    private BestCategoryRepository bestCategoryRepository;


    @Test
    public void get_특정베스트카테고리_리스트() {
        BestCategory bestCategory = new BestCategory(7L, "석윤스", null);
        bestCategory = bestCategoryRepository.save(bestCategory);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<RestResponse<List<BestCategory>>> response = template.exchange(
                "/category/best",
                HttpMethod.GET,
                new HttpEntity<>(headers),
                new ParameterizedTypeReference<RestResponse<List<BestCategory>>>() {
                });


        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getData()).contains(bestCategory);
    }

}
