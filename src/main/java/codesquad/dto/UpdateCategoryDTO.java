package codesquad.dto;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class UpdateCategoryDTO {
    Long id;
    @Size(min=1)
    String title;

    public UpdateCategoryDTO(){}

    public UpdateCategoryDTO(Long id, String title){
        this.id = id;
        this.title = title;
    }
}
