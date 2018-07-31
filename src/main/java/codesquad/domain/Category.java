package codesquad.domain;

import codesquad.dto.CategoryDTO;
import codesquad.dto.UpdateCategoryDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;

    @Column(nullable = false)
    @Size(max = 30)
    @JsonProperty("title")
    private String title;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "parent")
    @Where(clause = "deleted = false")
    @JsonProperty("children")
    private List<Category> children;

    @ManyToOne
    @JsonIgnoreProperties({"title", "children", "parent", "deleted"})
    @JsonProperty("parent")
    private Category parent;

    @Column
    @JsonProperty("deleted")
    private boolean deleted = false;

    private Category(String title) {
        this.title = title;
    }

    public Category(){}

    public static Category fromDTO(CategoryDTO categoryDTO) {
        return new Category(categoryDTO.getTitle());
    }

    private void setParent(Category parent) {
        this.parent = parent;
    }

    public Category addChild(CategoryDTO categoryDTO) {
        Category child = fromDTO(categoryDTO);
        child.setParent(this);
        this.children.add(child);
        return child;
    }

    public void delete() {
        this.deleted = true;
        if(children!=null){
            deleteChildren();
        }
    }

    private void deleteChildren(){
        for (Category child : children) {
            child.delete();
        }
    }

    public void update(UpdateCategoryDTO updateCategoryDTO) {
        this.title = updateCategoryDTO.getTitle();
    }
}
