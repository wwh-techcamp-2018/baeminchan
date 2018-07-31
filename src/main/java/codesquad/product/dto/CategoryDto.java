package codesquad.product.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@Builder
@NoArgsConstructor
public class CategoryDto {
    @NotEmpty
    private String title;
    private Long parentCategoryId;

    public CategoryDto(String title, Long parentCategoryId) {
        this.title = title;
        this.parentCategoryId = parentCategoryId;
    }

    public boolean isMainCategory() {
        return parentCategoryId == null;
    }
}
