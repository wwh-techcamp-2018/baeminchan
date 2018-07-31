package codesquad.dto;

import codesquad.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
public class CategoryDto {

    private String name;
    private List<CategoryDto> children;

    public CategoryDto(String name) {
        this.name = name;
        this.children = new ArrayList<CategoryDto>();
    }

    public CategoryDto(String name, List<CategoryDto> children) {
        this.name = name;
        this.children = children;
    }

    public CategoryDto() {
    }

    public CategoryDto getFirstChild() {
        return this.children.get(0);
    }
}
