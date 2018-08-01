package codesquad.promotion.service;

import codesquad.promotion.domain.Promotion;
import codesquad.promotion.domain.PromotionRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PromotionServiceTest {
    @InjectMocks
    private PromotionService promotionService;

    @Mock
    private PromotionRepository promotionRepository;

    private Promotion promotion;

    @Before
    public void setUp() throws Exception {
        promotion = Promotion.builder().id(1L).imgUrl("https://blah.kr/static/img/test.jpg").build();
    }

    @Test
    public void create() {
        when(promotionRepository.save(promotion)).thenReturn(promotion);

        Promotion savedPromotion = promotionService.create(promotion);

        assertThat(savedPromotion).isEqualTo(promotion);
    }

    @Test
    public void delete() {
        promotionService.delete(promotion.getId());
        verify(promotionRepository).deleteById(promotion.getId());
    }

    @Test
    public void findAll() {
        List<Promotion> promotions = promotionService.findAll();
        verify(promotionRepository).findAll();
    }
}