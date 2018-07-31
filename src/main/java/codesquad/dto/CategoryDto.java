package codesquad.dto;

import codesquad.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    private String name;

    public Category toCategory(Category parent) {
        return new Category(this.name, parent);
    }

}
