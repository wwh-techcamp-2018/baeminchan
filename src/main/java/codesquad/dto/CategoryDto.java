package codesquad.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class CategoryDto {

    private String name;
    private List<CategoryDto> children;

    public CategoryDto(String name) {
        this.name = name;
        this.children = new ArrayList<>();
    }

    public CategoryDto(String name, List<CategoryDto> children) {
        this.name = name;
        this.children = children;
    }

    public CategoryDto() {
    }

    @JsonIgnore
    public CategoryDto getFirstChild() {
        if (children.isEmpty()) {
            return null;
        }
        return this.children.get(0);
    }
}
