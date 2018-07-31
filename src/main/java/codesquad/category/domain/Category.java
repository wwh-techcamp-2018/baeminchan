package codesquad.category.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {

    public static final Long ROOT_ID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @JsonIgnore
    @ManyToOne
    private Category parent;

    @OneToMany(mappedBy = "parent", cascade = {CascadeType.ALL})
    @OrderBy("id ASC")
    private Set<Category> children;


    public void update(String name, Category parent) {
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
