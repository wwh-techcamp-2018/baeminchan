package codesquad.domain;

import codesquad.dto.CategoryDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, nullable = false)
    private String name;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "parent_id")
    private Category parent;

    @Column
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Category> children = new ArrayList<Category>();

    public Category(Long id, String name, Category parent, List<Category> children) {
        this.id = id;
        this.name = name;
        this.parent = parent;
        this.children = children;
    }

    public Category(String name, Category parent, List<Category> children) {
        this(null, name, parent, children);
    }

    public Category(String name) {
        this(null, name, null, new ArrayList<Category>());
    }

    public Category() {
    }

    public static CategoryDto toDto(Category category) {
        return new CategoryDto(category.getName(), category.getChildren()
                .stream()
                .map(c -> toDto(c))
                .collect(Collectors.toList()));
    }

    public Category addChild(Category child) {
        this.children.add(child);
        if (child.getParent() != null) {
            child.getParent().getChildren().remove(child);
        }
        child.setParent(this);

        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;
        String name1 = parent == null ? "" : parent.getName();
        String name2 = category.parent == null ? "" : category.parent.getName();
        return Objects.equals(id, category.id) &&
                Objects.equals(name, category.name) &&
                Objects.equals(name1, name2) &&
                Objects.equals(children, category.children);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, parent, children);
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parent=" + (parent == null ? "" : parent.getName()) +
                ", children=" + children +
                '}';
    }

    public void removeReference() {
        //TODO 현재는 소분류만 지우는 것으로 가정 (자식이 있는데 부모를 삭제하려고 하는 경우 에러 메시지 날리게 구현 예정)
        this.parent.getChildren().remove(this);
        setParent(null);
    }

//TODO products 만들어줘야 함
}
