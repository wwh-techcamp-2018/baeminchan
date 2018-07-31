package codesquad.category.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Setter
@Getter
public class CategoryDto {
    @NotNull
    @Size(min = 1, max = 100)
    private String title;


    private Long parentCategoryId;

    public CategoryDto(String title, Long parentCategoryId) {
        this.title = title;
        this.parentCategoryId = parentCategoryId;
    }

}
