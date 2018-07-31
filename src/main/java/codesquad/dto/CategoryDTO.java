package codesquad.dto;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class CategoryDTO {
    private Long parentId;
    @Size(min=1)
    private String title;
}
