package codesquad.dto;

import lombok.Data;

@Data
public class TestCategoryDto {
    private Long parentId;
    private Long categoryId;
    private String title;
}
