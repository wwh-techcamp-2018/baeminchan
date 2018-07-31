package codesquad.domain;

import java.util.List;

public class CategoryDTO {

    private Long id;

    private String title;

    private List<Category> categories;

    public CategoryDTO(Category parent, List<Category> categories) {
        this.id = parent.getId();
        this.title = parent.getTitle();
        this.categories = categories;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<Category> getCategories() {
        return categories;
    }
}
