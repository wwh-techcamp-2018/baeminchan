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
    /**
     * Todo
     * 1. parentCategory를 String으로 받았을 때
     *  - 뎁스가 늘어날 때마다 코드가 추가된다.
     * 2. parentCategory를 Category로 받았을 때
     *  - 카테고리로 파라미터를 받으면 뎁스가 늘어나도 코드 수정이 필요없다.
     */

    private String name;

    public CategoryDto(String name) {
        this.name = name;
    }

    public Category toCategory(Category parentCategory) {
        Category newCategory = new Category(parentCategory, this.name);
        return newCategory;
    }
}



