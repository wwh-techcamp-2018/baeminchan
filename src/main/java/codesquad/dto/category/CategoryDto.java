package codesquad.dto.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    private Long parentId;
    private String title;

    public boolean isRoot() {
        return parentId == null;
    }
}