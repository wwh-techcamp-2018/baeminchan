package codesquad.dto;

import codesquad.domain.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class CategoryDto {
    private String name;

    public CategoryDto(String name) {
        this.name = name;
    }

    public Category toCategory(Category parentCategory) {
        return new Category(parentCategory, this.name);
    }
}



