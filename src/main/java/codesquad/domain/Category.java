package codesquad.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;


@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 1, max = 50)
    @Column(nullable = false)
    private String title;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private Set<Category> children;

    public Category() {

    }

    private Category(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public void setChildren(Set<Category> children) {
        this.children = children;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category getParent() {
        return parent;
    }

    public Set<Category> getChildren() {
        return children;
    }


    public static Category valueOf(String title){
        return valueOf(null,title);
    }

    public static Category valueOf(Long id, String title){
        return new Category(id,title);
    }

    public void update(Category updateCategory) {
        title = updateCategory.title;
    }
}
