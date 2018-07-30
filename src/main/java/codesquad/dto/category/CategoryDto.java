package codesquad.dto.category;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CategoryDto {
    private Long parentId;
    private String title;

    public CategoryDto() {
    }
}