package codesquad.category.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Category parent;

    @OneToMany(mappedBy = "parent")
    @OrderBy("id ASC")
    private Set<Category> children;

    public Category(String name) {
        this.name = name;
    }

    public Category(Long id, String name) {
        this(id, name, null);
    }


    public Category(Long id, String name, Category parent) {
        this.id = id;
        this.name = name;
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name +
                "', children size=" + children.size() +
                " }";
    }
}
