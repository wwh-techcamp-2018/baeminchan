package codesquad.domain.category;


import codesquad.dto.category.CategoryDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cid;

    @Size(min = 1, max = 32)
    @Column(nullable = false)
    private String title;

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
}
