package codesquad.dto;

import lombok.Getter;

import javax.validation.constraints.Size;

@Getter
public class UpdateCategoryDTO {
    Long id;
    @Size(min=1)
    String title;
}
