package codesquad.dto.category;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChildCategoryDto {
    private Long parentId;
    private String childTitle;
}