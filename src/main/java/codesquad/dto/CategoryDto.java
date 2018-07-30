package codesquad.dto;

import codesquad.domain.Category;
import codesquad.domain.User;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {

    private String name;
    private Long parentId;
    private LocalDateTime createdTime;
    private int priority;

    public CategoryDto(String name, Long parentId, int priority) {
        this.name = name;
        this.parentId = parentId;
        this.priority = priority;
        this.createdTime = LocalDateTime.now();
    }

    public Long getParentId() {
        return parentId;
    }

    public Category toCategory(User user, Category parentCategory) {
        System.out.println("User123" + user.toString());
        return new Category(parentCategory, this.name, user, this.createdTime, this.priority);
    }
}
