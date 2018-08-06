package codesquad.dto;

import codesquad.domain.Promotion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PromotionDto implements Serializable {

    private String bannerUrl;
    private String description;

    public static PromotionDto fromEntity(Promotion promotion) {
        return new PromotionDto(promotion.getBannerUrl(), promotion.getDescription());
    }

    public Promotion toEntity() {
        return new Promotion(this.bannerUrl, this.description);
    }
}
