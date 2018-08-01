package codesquad.dto;

import codesquad.domain.Promotion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PromotionDto {

    private String bannerUrl;
    private String description;

    public static PromotionDto fromEntity(Promotion promotion) {
        return new PromotionDto(promotion.getBannerUrl(), promotion.getDescription());
    }
}
