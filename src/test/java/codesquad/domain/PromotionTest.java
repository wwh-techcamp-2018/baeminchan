package codesquad.domain;

import org.junit.Test;

import javax.validation.ValidationException;
import java.time.LocalDate;

public class PromotionTest {
    @Test(expected = ValidationException.class)
    public void 프로모션_노출_기간_부적절() {
        Promotion promotion = new Promotion("대박 이벤트", 1, LocalDate.now().plusDays(1), LocalDate.now());
    }
}
