package codesquad.domain.category;


import codesquad.domain.product.Product;
import codesquad.dto.category.CategoryDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cid;

    @Size(min = 1, max = 32)
    @Column(nullable = false)
    private String title;

    @Column
    private boolean deleted = false;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "categoryId")
    private List<Product> products = new ArrayList<>();

    @ManyToOne()
    @JoinColumn(name = "parentId")
    @JsonIgnore
    private Category parent;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "parentId")
    private List<Category> children = new ArrayList<>();

    public Category() {
    }

    public Category(String title) {
        this.title = title;
    }


    public void addChild(Category childCategory) {
        children.add(childCategory);
    }

    public void update(CategoryDto categoryDto, Category parent) {
        this.title = categoryDto.getTitle();
        this.parent = parent;
    }
}
