package codesquad.web;

import codesquad.domain.Authority;
import codesquad.domain.Promotion;
import codesquad.domain.Role;
import codesquad.domain.User;
import codesquad.dto.PromotionDto;
import codesquad.service.PromotionService;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import support.test.AcceptanceTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PromotionControllerTest extends AcceptanceTest {
    private static final Logger log = LoggerFactory.getLogger(PromotionControllerTest.class);

    private User defaultUser;
    private Role defaultRole;

    @Autowired
    private PromotionService promotionService;

    @Before
    public void setUp() {
        defaultRole = new Role(Authority.NORMAL);
        defaultUser = new User("test@test.com", "1234qwer!", "자바지기", "010-4090-8370", defaultRole);
    }

    @Test
    public void 리스트_가져오기() {
        List<Promotion> promotions = promotionService.getShowList();
        log.debug("promotions : {}", promotions);
    }

    @Test
    public void 프로모션_생성() {
        PromotionDto promotionDto = new PromotionDto("첫번째 타이틀", 1, LocalDate.of(2018, 1, 2), LocalDate.of(2019, 1, 2), "/img/01");
        Promotion promotion = promotionService.create(promotionDto, defaultUser);
        log.debug("promotion : {}", promotion.getTitle());
        assertThat(promotion.getTitle()).isEqualTo("첫번째 타이틀");
    }
}


