package codesquad.promotion.controller;

import codesquad.promotion.domain.Promotion;
import codesquad.promotion.domain.PromotionRepository;
import codesquad.support.AcceptanceTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class PromotionAcceptanceTest extends AcceptanceTest {
    @Autowired
    private PromotionRepository promotionRepository;

    @Test
    public void getPromotionList() {
        Promotion promotion = Promotion.builder().imgUrl("https://blah.co.kr/static/img/test.jpg").build();
        promotionRepository.save(promotion);

        ResponseEntity<String> responseEntity = template().getForEntity("/", String.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).contains(promotion.getImgUrl());
    }
}
