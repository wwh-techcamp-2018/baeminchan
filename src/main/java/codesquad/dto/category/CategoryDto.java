package codesquad.dto.category;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    @ApiModelProperty(value = "parent_id", dataType = "Long", required = true)
    private Long parentId;

    @ApiModelProperty(value = "category_id", dataType = "Long", required = true)
    private Long categoryId;

    @ApiModelProperty(value = "title", dataType = "string", required = true)
    private String title;

    @ApiModelProperty(hidden = true, readOnly = true)
    public boolean isRoot() {
        return parentId == null;
    }
}