package codesquad.domain;

import codesquad.dto.PromotionDto;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import static org.assertj.core.api.Assertions.assertThat;

public class PromotionTest {
    private User defaultUser;
    private Role defaultRole;

    @Autowired
    private PromotionDto promotionDto;
    private Promotion promotion;
    private PromotionDto updatedPromotionDto;

    @Before
    public void setUp() {
        defaultRole = new Role(Authority.NORMAL);
        defaultUser = new User("test@test.com", "1234qwer!", "자바지기", "010-4090-8370", defaultRole);
        promotionDto = new PromotionDto("첫번째 타이틀", 1, LocalDate.of(2018, 1, 2), LocalDate.of(2019, 1, 2), "/img/01");
        promotion = promotionDto.toPromotion(defaultUser);
        updatedPromotionDto = new PromotionDto("수정 된 첫번째 타이틀", 1, LocalDate.of(2018, 1, 2), LocalDate.of(2019, 1, 2), "/img/02");
    }

    @Test
    public void 프로모션_수정() {
        promotion.update(updatedPromotionDto, defaultUser);
        assertThat(promotion.getTitle()).isEqualTo("수정 된 첫번째 타이틀");
        assertThat(promotion.getImageUrl()).isEqualTo("/img/02");
    }

    @Test
    public void 프로모션_삭제() {
        promotion.delete();
        assertThat(promotion.isDeleted()).isTrue();
    }
}
