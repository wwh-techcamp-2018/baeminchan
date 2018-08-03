package codesquad.dto;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class CategoryDTO {
    private Long parentId;
    @Size(min = 1)
    private String title;

    public CategoryDTO() {
    }

    public CategoryDTO(Long parentId, @Size(min = 1) String title) {
        this.parentId = parentId;
        this.title = title;
    }
}
