package codesquad.home.web;

import codesquad.promotion.domain.Promotion;
import codesquad.promotion.domain.PromotionRepository;
import codesquad.support.AcceptanceTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;


public class HomeControllerTest extends AcceptanceTest {

    @Autowired
    private PromotionRepository promotionRepository;

    @Test
    public void home() {
        String url = "https://goodDay/image1.png";
        Promotion promotion = Promotion.builder()
                .imgUrl(url)
                .build();
        promotionRepository.save(promotion);

        ResponseEntity<String> response = template().getForEntity("/", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).contains(url);
    }
}
