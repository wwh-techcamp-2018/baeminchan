package codesquad.promotion.web;

import codesquad.promotion.domain.Promotion;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotNull;
import java.util.Objects;

@NoArgsConstructor
@Getter
public class PromotionDto {
    @NotNull
    @URL
    private String imgUrl;

    public PromotionDto(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PromotionDto that = (PromotionDto) o;
        return Objects.equals(imgUrl, that.imgUrl);
    }

    @Override
    public int hashCode() {

        return Objects.hash(imgUrl);
    }

    public Promotion toEntity() {
        return Promotion.builder().imgUrl(imgUrl).build();
    }
}
