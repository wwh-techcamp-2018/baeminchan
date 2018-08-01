package codesquad.promotion.service;

import codesquad.promotion.domain.Promotion;
import codesquad.promotion.domain.PromotionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PromotionServiceTest {
    @InjectMocks
    private PromotionService promotionService;

    @Mock
    private PromotionRepository promotionRepository;

    @Test
    public void create() {
        Promotion promotion = Promotion.builder().id(1L).imgUrl("https://blah.kr/static/img/test.jpg").build();
        when(promotionRepository.save(promotion)).thenReturn(promotion);

        Promotion savedPromotion = promotionService.create(promotion);

        assertThat(savedPromotion).isEqualTo(promotion);
    }
}