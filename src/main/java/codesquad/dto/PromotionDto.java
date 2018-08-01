package codesquad.dto;

import codesquad.domain.Promotion;
import codesquad.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

import static codesquad.utils.Utils.PRIORITY_DEFAULT;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class PromotionDto {
    private static final Logger log = LoggerFactory.getLogger(PromotionDto.class);

    @Column
    private String title;

    @Column
    @ColumnDefault(PRIORITY_DEFAULT)
    private int priority;

    @Column
    private LocalDate startDate;

    @Column
    private LocalDate endDate;

    @Column
    @NotNull
    private String imageUrl;

    public Promotion toPromotion(User user) {
        return new Promotion(title, user.getUserId(), priority, startDate, endDate, imageUrl);
    }

    public PromotionDto(String title, int priority, LocalDate startDate, LocalDate endDate, String imageUrl) {
        this.title = title;
        this.priority = priority;
        this.startDate = startDate;
        this.endDate = endDate;
        this.imageUrl = imageUrl;
    }
}
