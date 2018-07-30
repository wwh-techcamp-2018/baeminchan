package codesquad.dto;

import codesquad.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
public class CategoryDto {
    private String name;
    private List<CategoryDto> children;

    public CategoryDto() {
    }

    public CategoryDto(String name) {
        this.children = new ArrayList<>();
        this.name = name;
    }

    public CategoryDto setName(String name) {
        this.name = name;
        return this;
    }

    public void setChildren(List<CategoryDto> children) {
        this.children = children;
    }

    public CategoryDto addChild(CategoryDto child) {
        this.children.add(child);
        return this;
    }

    public Category toEntity() {
        List<Category> children = new ArrayList<>();
        for (CategoryDto categoryDto : this.children) {
            children.add(categoryDto.toEntity());
        }
        return new Category(this.name, children);
    }

    public static CategoryDto defaultCategoryDto() {
        return new CategoryDto("기본");
    }
}
