package codesquad.dto;

import codesquad.domain.Category;
import codesquad.domain.User;
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

    public String getName() {
        return name;
    }

    public CategoryDto setName(String name) {
        this.name = name;
        return this;
    }

    public Category toNewCategory(User user, Category parentCategory) {
        this.createdTime = LocalDateTime.now();
        return toCategory(user, parentCategory);
    }

    public Category toCategory(User user, Category parentCategory) {
        return new Category(parentCategory, this.name, user, this.createdTime, this.priority);
    }

    @Override
    public String toString() {
        return "CategoryDto{" +
                "name='" + name + '\'' +
                ", parentId=" + parentId +
                ", createdTime=" + createdTime +
                ", priority=" + priority +
                '}';
    }
}
