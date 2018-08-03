package codesquad.promotion;

import codesquad.promotion.domain.PromotionRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import support.test.AcceptanceTest;

import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class PromotionAcceptanceTest extends AcceptanceTest {

    @Autowired
    private PromotionRepository promotionRepository;

    @Test
    public void promotionList() {
        ResponseEntity<String> response = template.getForEntity("/", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).contains(promotionRepository.findAll().stream().map(promotion -> promotion.getUrl()).collect(Collectors.toList()));
    }
}
